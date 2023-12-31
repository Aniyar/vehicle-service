package nu.swe.vehicleservice.driver.service.impl;

import lombok.RequiredArgsConstructor;
import nu.swe.vehicleservice.core.dto.PageResponse;
import nu.swe.vehicleservice.driver.dto.request.DriverCreateRequest;
import nu.swe.vehicleservice.driver.dto.request.DriverUpdateRequest;
import nu.swe.vehicleservice.driver.dto.response.DriverResponse;
import nu.swe.vehicleservice.driver.entity.DriverEntity;
import nu.swe.vehicleservice.vehicle.entity.VehicleEntity;
import nu.swe.vehicleservice.driver.exception.DriverException;
import nu.swe.vehicleservice.vehicle.exception.VehicleException;
import nu.swe.vehicleservice.driver.mapper.DriverMapper;
import nu.swe.vehicleservice.driver.repository.DriverRepository;
import nu.swe.vehicleservice.vehicle.mapper.VehicleMapper;
import nu.swe.vehicleservice.vehicle.repository.VehicleLocationRepository;
import nu.swe.vehicleservice.vehicle.repository.VehicleRepository;
import nu.swe.vehicleservice.driver.service.DriverService;
import nu.swe.vehicleservice.security.CurrentUser;
import nu.swe.vehicleservice.user.entity.UserEntity;
import nu.swe.vehicleservice.user.enums.UserErrorCode;
import nu.swe.vehicleservice.user.exception.UserException;
import nu.swe.vehicleservice.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.math.BigDecimal;

import static nu.swe.vehicleservice.driver.enums.DriverErrorCode.DRIVER_ALREADY_EXISTS;
import static nu.swe.vehicleservice.driver.enums.DriverErrorCode.DRIVER_NOT_FOUND;
import static nu.swe.vehicleservice.vehicle.enums.VehicleErrorCode.VEHICLE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;
    private final CurrentUser currentUser;
    private final UserRepository userRepository;
    private final VehicleRepository vehicleRepository;
    private final VehicleLocationRepository locationRepository;

    @Override
    public DriverResponse findById(Long id) {
        DriverEntity driver = driverRepository.findById(id)
                .orElseThrow(() -> new DriverException(DRIVER_NOT_FOUND));
        return DriverMapper.INSTANCE.toResponse(driver);
    }

    @Override
    public DriverResponse findCurrent() {
        DriverEntity driver = driverRepository.findByUserId(currentUser.getId())
                .orElseThrow(() -> new DriverException(DRIVER_NOT_FOUND));
        return mapResponse(driver);
    }

    @Override
    public void create(DriverCreateRequest request) {
        UserEntity user = userRepository.findById(currentUser.getId()).orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));
        if (driverRepository.findByUserId(user.getId()).isPresent()) {
            throw new DriverException(DRIVER_ALREADY_EXISTS);
        }

        DriverEntity driver = DriverEntity.builder()
                .user(user)
                .address(request.getAddress())
                .licenseNumber(request.getLicenseNumber())
                .rating(BigDecimal.valueOf(10))
                .build();
        driverRepository.save(driver);
    }

    @Override
    public PageResponse<DriverResponse> findAll(Pageable pageable) {
        Page<DriverEntity> page = driverRepository.findAll(pageable);
        return PageResponse.fromPage(page.map(this::mapResponse));
    }

    private DriverResponse mapResponse(DriverEntity driverEntity) {

        var response = DriverMapper.INSTANCE.toResponse(driverEntity);

        if (response.getVehicle() != null) {
            var location = locationRepository.findTop1ByVehicleIdOrderByCreatedAtDesc(response.getVehicle().getId());
            location.ifPresent(entity -> response.getVehicle().setLocation(VehicleMapper.INSTANCE.toLocationResponse(entity)));
        }

        return response;
    }

    @Override
    public void assignVehicle(Long id, Long vehicleId) {
        DriverEntity driver = driverRepository.findById(id).orElseThrow(() -> new DriverException(DRIVER_NOT_FOUND));
        VehicleEntity vehicle = vehicleRepository.findById(vehicleId).orElseThrow(() -> new VehicleException(VEHICLE_NOT_FOUND));

        driverRepository.findByVehicleId(vehicleId).ifPresent(
                other -> {
                    other.setVehicle(null);
                    driverRepository.save(other);
                }
        );

        driver.setVehicle(vehicle);
        driverRepository.save(driver);
    }

    @Override
    public void update(DriverUpdateRequest request) {
        DriverEntity driver = driverRepository.findById(request.getId())
                .orElseThrow(() -> new DriverException(DRIVER_NOT_FOUND));

        DriverMapper.INSTANCE.updateEntityFromRequest(request, driver);
        driverRepository.save(driver);
    }

    @Override
    public void delete(Long id) {
        driverRepository.deleteById(id);
    }
}
