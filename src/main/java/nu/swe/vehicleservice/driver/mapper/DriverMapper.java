package nu.swe.vehicleservice.driver.mapper;

import nu.swe.vehicleservice.driver.dto.request.DriverUpdateRequest;
import nu.swe.vehicleservice.driver.dto.response.DriverResponse;
import nu.swe.vehicleservice.driver.entity.DriverEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(builder = @Builder(disableBuilder = true))
public interface DriverMapper {

    DriverMapper INSTANCE = Mappers.getMapper(DriverMapper.class);

    DriverResponse toResponse(DriverEntity entity);

    void updateEntityFromRequest(DriverUpdateRequest request, @MappingTarget DriverEntity entity);

}
