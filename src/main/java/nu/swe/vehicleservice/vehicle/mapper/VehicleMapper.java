package nu.swe.vehicleservice.vehicle.mapper;

import nu.swe.vehicleservice.vehicle.dto.request.VehicleUpdateRequest;
import nu.swe.vehicleservice.vehicle.dto.response.VehicleLocationResponse;
import nu.swe.vehicleservice.vehicle.dto.response.VehicleResponse;
import nu.swe.vehicleservice.vehicle.entity.VehicleEntity;
import nu.swe.vehicleservice.vehicle.entity.VehicleLocationEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(builder = @Builder(disableBuilder = true))
public interface VehicleMapper {

    VehicleMapper INSTANCE = Mappers.getMapper(VehicleMapper.class);

    VehicleResponse toResponse(VehicleEntity entity);

    void updateEntityFromRequest(VehicleUpdateRequest request, @MappingTarget VehicleEntity entity);

    VehicleLocationResponse toLocationResponse(VehicleLocationEntity entity);
}
