package nu.swe.vehicleservice.maintenance.service.impl;

import lombok.RequiredArgsConstructor;
import nu.swe.vehicleservice.core.dto.PageResponse;
import nu.swe.vehicleservice.maintenance.dto.request.MainenanceCreateRequest;
import nu.swe.vehicleservice.maintenance.dto.request.MaintenanceGetRequest;
import nu.swe.vehicleservice.maintenance.dto.request.MaintenanceUpdateRequest;
import nu.swe.vehicleservice.maintenance.dto.response.MaintenanceResponse;
import nu.swe.vehicleservice.maintenance.entity.MaintenanceEntity;
import nu.swe.vehicleservice.maintenance.exception.MaintenanceException;
import nu.swe.vehicleservice.maintenance.mapper.MaintenanceMapper;
import nu.swe.vehicleservice.maintenance.repository.MaintenanceRepository;
import nu.swe.vehicleservice.maintenance.service.MaintenanceService;
import nu.swe.vehicleservice.security.CurrentUser;
import nu.swe.vehicleservice.user.entity.UserEntity;
import nu.swe.vehicleservice.user.enums.UserErrorCode;
import nu.swe.vehicleservice.user.exception.UserException;
import nu.swe.vehicleservice.user.repository.UserRepository;
import nu.swe.vehicleservice.vehicle.exception.VehicleException;
import nu.swe.vehicleservice.vehicle.repository.VehicleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import static nu.swe.vehicleservice.core.specification.CommonSpecification.alwaysTrue;
import static nu.swe.vehicleservice.core.specification.CommonSpecification.attributeEquals;
import static nu.swe.vehicleservice.maintenance.enums.MaintenanceErrorCode.MAINTENANCE_NOT_FOUND;
import static nu.swe.vehicleservice.vehicle.enums.VehicleErrorCode.VEHICLE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class MaintenanceServiceImpl implements MaintenanceService {

    private final CurrentUser currentUser;
    private final UserRepository userRepository;
    private final MaintenanceRepository maintenanceRepository;
    private final VehicleRepository vehicleRepository;

    @Override
    public MaintenanceResponse findById(Long id) {
        MaintenanceEntity maintenance = maintenanceRepository.findById(id)
                .orElseThrow(() -> new MaintenanceException(MAINTENANCE_NOT_FOUND));
        return MaintenanceMapper.INSTANCE.toResponse(maintenance);
    }

    @Override
    public void create(MainenanceCreateRequest request) {
        var vehicle = vehicleRepository.findById(request.getVehicleId())
                .orElseThrow(() -> new VehicleException((VEHICLE_NOT_FOUND)));

        UserEntity maintenancePersonnel = userRepository.findById(currentUser.getId())
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));


        MaintenanceEntity maintenance = MaintenanceEntity.builder()
                .vehicle(vehicle)
                .maintenanceType(request.getMaintenanceType())
                .details(request.getDetails())
                .maintenancePersonnel(maintenancePersonnel)
                .build();
        maintenanceRepository.save(maintenance);
    }

    @Override
    public PageResponse<MaintenanceResponse> findAll(MaintenanceGetRequest request, Pageable pageable) {
        Specification<MaintenanceEntity> where = alwaysTrue();
        if (request.getMaintenancePersonnelId() != null) {
            where = where.and(attributeEquals("maintenancePersonnel", "id", request.getMaintenancePersonnelId()));
        }
        if (request.getVehicleId() != null) {
            where = where.and(attributeEquals("vehicle", "id", request.getVehicleId()));
        }
        if (request.getMaintenanceType() != null) {
            where = where.and(attributeEquals("maintenanceType", request.getMaintenanceType()));
        }
        Page<MaintenanceEntity> page = maintenanceRepository.findAll(where, pageable);
        return PageResponse.fromPage(page.map(MaintenanceMapper.INSTANCE::toResponse));
    }

    @Override
    public void update(MaintenanceUpdateRequest request) {
        MaintenanceEntity maintenance = maintenanceRepository.findById(request.getId())
                .orElseThrow(() -> new MaintenanceException(MAINTENANCE_NOT_FOUND));

        MaintenanceMapper.INSTANCE.updateEntityFromRequest(request, maintenance);
        maintenanceRepository.save(maintenance);
    }
}
