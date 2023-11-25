package nu.swe.vehicleservice.maintenance.mapper;

import nu.swe.vehicleservice.maintenance.dto.request.MaintenanceUpdateRequest;
import nu.swe.vehicleservice.maintenance.dto.response.MaintenanceResponse;
import nu.swe.vehicleservice.maintenance.entity.MaintenanceEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(builder = @Builder(disableBuilder = true))
public interface MaintenanceMapper {

    MaintenanceMapper INSTANCE = Mappers.getMapper(MaintenanceMapper.class);

    MaintenanceResponse toResponse(MaintenanceEntity entity);

    void updateEntityFromRequest(MaintenanceUpdateRequest request, @MappingTarget MaintenanceEntity entity);

}
