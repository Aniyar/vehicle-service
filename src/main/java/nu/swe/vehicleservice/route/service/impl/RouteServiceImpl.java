package nu.swe.vehicleservice.route.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nu.swe.vehicleservice.core.dto.PageResponse;
import nu.swe.vehicleservice.corefeatures.docgenerator.client.DocGeneratorClient;
import nu.swe.vehicleservice.corefeatures.docgenerator.dto.DocGenerateRequest;
import nu.swe.vehicleservice.corefeatures.docgenerator.dto.RouteDocData;
import nu.swe.vehicleservice.driver.entity.DriverEntity;
import nu.swe.vehicleservice.driver.exception.DriverException;
import nu.swe.vehicleservice.driver.repository.DriverRepository;
import nu.swe.vehicleservice.filestorage.service.FileStorage;
import nu.swe.vehicleservice.route.dto.request.RouteCreateRequest;
import nu.swe.vehicleservice.route.dto.request.RouteGenerateReportRequest;
import nu.swe.vehicleservice.route.dto.request.RouteGetRequest;
import nu.swe.vehicleservice.route.dto.request.RouteUpdateRequest;
import nu.swe.vehicleservice.route.dto.response.RouteReportResponse;
import nu.swe.vehicleservice.route.dto.response.RouteResponse;
import nu.swe.vehicleservice.route.entity.RouteEntity;
import nu.swe.vehicleservice.route.enums.RouteStatus;
import nu.swe.vehicleservice.route.exception.RouteException;
import nu.swe.vehicleservice.route.mapper.RouteMapper;
import nu.swe.vehicleservice.route.repository.RouteRepository;
import nu.swe.vehicleservice.route.service.RouteService;
import nu.swe.vehicleservice.security.CurrentUser;
import nu.swe.vehicleservice.user.entity.UserEntity;
import nu.swe.vehicleservice.user.enums.UserErrorCode;
import nu.swe.vehicleservice.user.enums.UserRole;
import nu.swe.vehicleservice.user.exception.UserException;
import nu.swe.vehicleservice.user.repository.UserRepository;
import nu.swe.vehicleservice.vehicle.entity.VehicleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

import static nu.swe.vehicleservice.core.specification.CommonSpecification.*;
import static nu.swe.vehicleservice.driver.enums.DriverErrorCode.DRIVER_NOT_FOUND;
import static nu.swe.vehicleservice.route.enums.RouteErrorCode.*;


@Slf4j
@Service
@RequiredArgsConstructor
public class RouteServiceImpl implements RouteService {

    private final CurrentUser currentUser;
    private final UserRepository userRepository;
    private final RouteRepository routeRepository;
    private final DriverRepository driverRepository;
    private final FileStorage fileStorage;
    private final DocGeneratorClient docGeneratorClient;

    @Override
    public RouteResponse findById(Long id) {
        RouteEntity route = routeRepository.findById(id)
                .orElseThrow(() -> new RouteException(ROUTE_NOT_FOUND));
        return RouteMapper.INSTANCE.toResponse(route);
    }

    @Override
    public void create(RouteCreateRequest request) {
        UserEntity staff = userRepository.findById(currentUser.getId())
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));


        RouteEntity route = RouteEntity.builder()
                .staff(staff)
                .startPoint(request.getStartPoint())
                .startLon(request.getStartLon())
                .startLat(request.getStartLat())
                .endPoint(request.getEndPoint())
                .endLat(request.getEndLat())
                .endLon(request.getEndLon())
                .status(RouteStatus.NEW)
                .build();
        routeRepository.save(route);
    }

    @Override
    public PageResponse<RouteResponse> findAll(RouteGetRequest request, Pageable pageable) {
        Specification<RouteEntity> where = alwaysTrue();
        if (request.getDriverId() != null) {
            where = where.and(attributeEquals("driver", "id", request.getDriverId()));
        }
        if (request.getVehicleId() != null) {
            where = where.and(attributeEquals("vehicle", "id", request.getVehicleId()));
        }
        if (request.getStaffId() != null) {
            where = where.and(attributeEquals("staff", "id", request.getStaffId()));
        }
        if (request.getStatus() != null) {
            where = where.and(attributeEquals("status", request.getStatus()));
        }
        Page<RouteEntity> page = routeRepository.findAll(where, pageable);
        return PageResponse.fromPage(page.map(RouteMapper.INSTANCE::toResponse));
    }

    @Override
    public void update(RouteUpdateRequest request) {
        RouteEntity route = routeRepository.findById(request.getId())
                .orElseThrow(() -> new RouteException(ROUTE_NOT_FOUND));

        RouteMapper.INSTANCE.updateEntityFromRequest(request, route);
        routeRepository.save(route);
    }

    @Override
    public void changeStatus(Long id, RouteStatus status) {
        RouteEntity route = routeRepository.findById(id)
                .orElseThrow(() -> new RouteException(ROUTE_NOT_FOUND));

        var currentStatus = route.getStatus();
        var currentRole = currentUser.getRole();

        if ((currentStatus == status)
            || (currentStatus == RouteStatus.NEW && (!status.in(RouteStatus.WAITING, RouteStatus.CANCELLED) || !currentRole.in(UserRole.admin, UserRole.staff)))
            || (currentStatus == RouteStatus.IN_PROGRESS && (status != RouteStatus.COMPLETED || currentRole != UserRole.driver))
            || (currentStatus == RouteStatus.WAITING && (!status.in(RouteStatus.IN_PROGRESS, RouteStatus.NEW) || currentRole != UserRole.driver))
            || (currentStatus == RouteStatus.COMPLETED))
        {
            throw new RouteException(ROUTE_INVALID_STATUS);
        }

        if (status == RouteStatus.NEW) {
            if (!route.getDriver().getUser().getId().equals(currentUser.getId())) {
                route.setDriver(null);
            }
        }

        if (route.getStartTime() == null && status == RouteStatus.IN_PROGRESS) {
            route.setStartTime(LocalDateTime.now());
        }
        if (route.getEndTime() != null && status == RouteStatus.COMPLETED) {
            route.setEndTime(LocalDateTime.now());
        }

        route.setStatus(status);
        routeRepository.save(route);
    }

    @Override
    public void assignDriver(Long id, Long driverId) {
        RouteEntity route = routeRepository.findById(id)
                .orElseThrow(() -> new RouteException(ROUTE_NOT_FOUND));

        DriverEntity driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new DriverException(DRIVER_NOT_FOUND));

        VehicleEntity vehicle = driver.getVehicle();

        if (vehicle == null) {
            throw new RouteException(ROUTE_NO_VEHICLE_ASSIGNED_TO_DRIVER);
        }

        route.setDriver(driver);
        route.setVehicle(vehicle);
        route.setStatus(RouteStatus.WAITING);

        routeRepository.save(route);
    }

    @Override
    public void delete(Long id) {
        routeRepository.deleteById(id);
    }

    @Override
    public RouteReportResponse generateReport(RouteGenerateReportRequest filter) {
        RouteDocData routeDocData = new RouteDocData();
        routeDocData.setGeneratedAt(OffsetDateTime.now().toString());

        var allRoutes = getRoutesData(filter);
        routeDocData.setRoutes(allRoutes);

        DocGenerateRequest request = new DocGenerateRequest();
        request.setTemplate("routes.xlsm");
        request.setData(routeDocData);

        var fileName = generateFileName(filter);
        var fileBytes = generateExportFile(request);
        var fileId = fileStorage.uploadFile(fileBytes, fileName);
        var fileLink = fileStorage.getTemporaryDownloadUrl(fileId);
        return new RouteReportResponse(fileLink, fileName);
    }

    private String generateFileName(RouteGenerateReportRequest filter) {

        return "Routes_Vehicle_Service_%s_%s.xlsx".formatted(
                filter.getFromDate(),
                filter.getToDate()
        );
    }

    private byte[] generateExportFile(DocGenerateRequest request) {
        log.info("Trying to generate export file");
        var generatedDoc = docGeneratorClient.generate(request);
        log.info("Export file generated successfully");
        return generatedDoc;
    }

    private List<RouteDocData.Route> getRoutesData(RouteGenerateReportRequest filter) {
        Specification<RouteEntity> where = alwaysTrue();

        where = where.and(safeEquals("startTime", filter.getFromDate()))
                .and(safeEquals("endTime", filter.getToDate()));

        if (filter.getVehicleId() != null) {
            where = where.and(attributeEquals("vehicle", "id", filter.getVehicleId()));
        }

        if (filter.getDriverId() != null) {
            where = where.and(attributeEquals("driver", "id", filter.getDriverId()));
        }

        var routes = routeRepository.findAll(where);
        var routeDocData = routes.stream()
                .map(RouteMapper.INSTANCE::toDocResponse)
                .toList();

        for (int i = 0; i < routeDocData.size(); i++) {
            var route = routeDocData.get(i);
            route.setIndex(i + 1);
            route.setDriverName("%s %s".formatted(routes.get(i).getDriver().getUser().getFirstName(), routes.get(i).getDriver().getUser().getLastName()));
            route.setStaffName("%s %s".formatted(routes.get(i).getStaff().getFirstName(), routes.get(i).getStaff().getLastName()));
        }

        return routeDocData;
    }
}
