package nu.swe.vehicleservice.corefeatures.file.mapper;

import nu.swe.vehicleservice.corefeatures.file.dto.response.FileLinkResponse;
import nu.swe.vehicleservice.corefeatures.file.entity.FileLinkEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * An interface for mapping {@link FileLinkEntity}.
 */
@Mapper(builder = @Builder(disableBuilder = true))
public interface FileMapper {

    FileMapper INSTANCE = Mappers.getMapper(FileMapper.class);

    /**
     * Maps {@link FileLinkEntity} to {@link FileLinkResponse}.
     *
     * @param entity to be converted.
     * @return response dto.
     */
    FileLinkResponse toResponse(FileLinkEntity entity);
}
