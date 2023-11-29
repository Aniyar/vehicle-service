package nu.swe.vehicleservice.fuel.service.impl;

import lombok.RequiredArgsConstructor;
import nu.swe.vehicleservice.core.dto.PageResponse;
import nu.swe.vehicleservice.fuel.dto.request.FuelCreateRequest;
import nu.swe.vehicleservice.fuel.dto.request.FuelGetRequest;
import nu.swe.vehicleservice.fuel.dto.request.FuelUpdateRequest;
import nu.swe.vehicleservice.fuel.dto.response.FuelResponse;
import nu.swe.vehicleservice.fuel.entity.FuelEntity;
import nu.swe.vehicleservice.fuel.exception.FuelException;
import nu.swe.vehicleservice.fuel.mapper.FuelMapper;
import nu.swe.vehicleservice.fuel.repository.FuelRepository;
import nu.swe.vehicleservice.fuel.service.FuelService;
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

import java.math.BigDecimal;

import static nu.swe.vehicleservice.core.specification.CommonSpecification.*;
import static nu.swe.vehicleservice.fuel.enums.FuelErrorCode.FUEL_NOT_FOUND;
import static nu.swe.vehicleservice.vehicle.enums.VehicleErrorCode.VEHICLE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class FuelServiceImpl implements FuelService {

    private final CurrentUser currentUser;
    private final UserRepository userRepository;
    private final FuelRepository fuelRepository;
    private final VehicleRepository vehicleRepository;

    @Override
    public FuelResponse findById(Long id) {
        FuelEntity fuel = fuelRepository.findById(id).orElseThrow(() -> new FuelException(FUEL_NOT_FOUND));

        return FuelMapper.INSTANCE.toResponse(fuel);
    }

    @Override
    public void create(FuelCreateRequest request) {
        BigDecimal numberLitersDecimal = new BigDecimal(request.getNumberLiters());
        BigDecimal price = request.getPricePerLiter().multiply(numberLitersDecimal);
        var vehicle = vehicleRepository.findById(request.getVehicleId())
                .orElseThrow(() -> new VehicleException((VEHICLE_NOT_FOUND)));

        UserEntity fuelPersonnel = userRepository.findById(currentUser.getId())
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));


        FuelEntity fuel = FuelEntity.builder()
                .fuelType(request.getFuelType())
                .numberLiters(request.getNumberLiters())
                .pricePerLiter(request.getPricePerLiter())
                .price(price)
                .vehicle(vehicle)
                .fuelPersonnel(fuelPersonnel)
                .build();
        fuelRepository.save(fuel);
    }

    @Override
    public PageResponse<FuelResponse> findAll(FuelGetRequest request, Pageable pageable) {
        Specification<FuelEntity> where = alwaysTrue();
        if (request.getFuelPersonnelId() != null) {
            where = where.and(attributeEquals("fuelPersonnel", "id", request.getFuelPersonnelId()));
        }
        if (request.getVehicleId() != null) {
            where = where.and(attributeEquals("vehicle", "id", request.getVehicleId()));
        }
        if (request.getFuelType() != null) {
            where = where.and(attributeEquals("fuelType", request.getFuelType()));
        }
        if (request.getVehiclePlate() != null) {
            where = where.and(attributeEquals("vehicle", "licencePlate", request.getVehiclePlate()));
        }
        Page<FuelEntity> page = fuelRepository.findAll(where, pageable);
        return PageResponse.fromPage(page.map(FuelMapper.INSTANCE::toResponse));
    }

    @Override
    public void update(FuelUpdateRequest request) {
        FuelEntity fuel = fuelRepository.findById(request.getId())
                .orElseThrow(() -> new FuelException(FUEL_NOT_FOUND));

        FuelMapper.INSTANCE.updateEntityFromRequest(request, fuel);
        fuelRepository.save(fuel);
    }
}
