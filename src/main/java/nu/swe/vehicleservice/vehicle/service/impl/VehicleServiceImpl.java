package nu.swe.vehicleservice.vehicle.service.impl;

import lombok.RequiredArgsConstructor;
import nu.swe.vehicleservice.core.dto.PageResponse;
import nu.swe.vehicleservice.driver.mapper.DriverMapper;
import nu.swe.vehicleservice.driver.repository.DriverRepository;
import nu.swe.vehicleservice.security.CurrentUser;
import nu.swe.vehicleservice.vehicle.dto.request.VehicleCreateRequest;
import nu.swe.vehicleservice.vehicle.dto.request.VehicleUpdateRequest;
import nu.swe.vehicleservice.vehicle.dto.response.VehicleResponse;
import nu.swe.vehicleservice.vehicle.entity.VehicleEntity;
import nu.swe.vehicleservice.vehicle.enums.VehicleErrorCode;
import nu.swe.vehicleservice.vehicle.exception.VehicleException;
import nu.swe.vehicleservice.vehicle.mapper.VehicleMapper;
import nu.swe.vehicleservice.vehicle.repository.VehicleRepository;
import nu.swe.vehicleservice.vehicle.service.VehicleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final DriverRepository driverRepository;

    @Override
    public void create(VehicleCreateRequest request) {
        VehicleEntity vehicle = VehicleEntity.builder()
                .licencePlate(request.getLicencePlate())
                .capacity(request.getCapacity())
                .year(request.getYear())
                .model(request.getModel())
                .vin(request.getVin())
                .fuelType(request.getFuelType())
                .build();

        vehicleRepository.save(vehicle);
    }

    @Override
    public PageResponse<VehicleResponse> findAll(Pageable pageable) {
        Page<VehicleEntity> page = vehicleRepository.findAll(pageable);
        return PageResponse.fromPage(page.map(this::mapResponse));
    }

    @Override
    public VehicleResponse findById(Long id) {
        VehicleEntity vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new VehicleException(VehicleErrorCode.VEHICLE_NOT_FOUND));
        return mapResponse(vehicle);
    }

    private VehicleResponse mapResponse(VehicleEntity vehicle) {
        VehicleResponse response = VehicleMapper.INSTANCE.toResponse(vehicle);
        var driver = driverRepository.findByVehicleId(vehicle.getId());
        if (driver.isPresent()) {
            var driverResponse = DriverMapper.INSTANCE.toResponse(driver.get());
            driverResponse.setVehicle(null);
            response.setDriver(driverResponse);
        }
        return response;
    }

    @Override
    public void update(VehicleUpdateRequest request) {
        VehicleEntity vehicle = vehicleRepository.findById(request.getId())
                .orElseThrow(() -> new VehicleException(VehicleErrorCode.VEHICLE_NOT_FOUND));

        VehicleMapper.INSTANCE.updateEntityFromRequest(request, vehicle);
        vehicleRepository.save(vehicle);
    }
}
