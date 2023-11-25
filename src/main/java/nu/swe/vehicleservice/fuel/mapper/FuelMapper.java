package nu.swe.vehicleservice.fuel.mapper;

import nu.swe.vehicleservice.fuel.dto.request.FuelUpdateRequest;
import nu.swe.vehicleservice.fuel.dto.response.FuelResponse;
import nu.swe.vehicleservice.fuel.entity.FuelEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(builder = @Builder(disableBuilder = true))
public interface FuelMapper {

    FuelMapper INSTANCE = Mappers.getMapper(FuelMapper.class);

    FuelResponse toResponse(FuelEntity entity);

    void updateEntityFromRequest(FuelUpdateRequest request, @MappingTarget FuelEntity entity);

}
