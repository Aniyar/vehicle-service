package nu.swe.vehicleservice.route.mapper;

import nu.swe.vehicleservice.corefeatures.docgenerator.dto.RouteDocData;
import nu.swe.vehicleservice.route.dto.request.RouteUpdateRequest;
import nu.swe.vehicleservice.route.dto.response.RouteResponse;
import nu.swe.vehicleservice.route.entity.RouteEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(builder = @Builder(disableBuilder = true))
public interface RouteMapper {

    RouteMapper INSTANCE = Mappers.getMapper(RouteMapper.class);

    RouteResponse toResponse(RouteEntity entity);

    void updateEntityFromRequest(RouteUpdateRequest request, @MappingTarget RouteEntity entity);

    @Mapping(source = "vehicle.id", target = "vehicleId")
    @Mapping(source = "driver.id", target = "driverId")
    @Mapping(source = "staff.id", target = "staffId")
    RouteDocData.Route toDocResponse(RouteEntity routeEntity);
}
