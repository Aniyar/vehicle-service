package nu.swe.vehicleservice.user.mapper;

import nu.swe.vehicleservice.user.dto.request.UserCreateRequest;
import nu.swe.vehicleservice.user.dto.response.UserResponse;
import nu.swe.vehicleservice.user.dto.request.UserUpdateRequest;
import nu.swe.vehicleservice.user.entity.UserEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * This interface is describing methods that maps User related DTO's, entities and other representations.
 */
@Mapper(builder = @Builder(disableBuilder = true))
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    /**
     * Maps create request to user representation in keycloak.
     *
     * @param request {@link UserCreateRequest}
     * @return {@link UserEntity}
     */
    UserEntity toRepresentation(UserCreateRequest request);

    /**
     * Maps update request to existing user representation in keycloak.
     *
     * @param request        {@link UserUpdateRequest}
     * @param representation {@link UserEntity} keycloak's user representation to update
     * @return {@link UserEntity} keycloak's user representation
     */
    UserEntity toRepresentation(UserUpdateRequest request, @MappingTarget UserEntity representation);

    /**
     * Maps user representation in keycloak to update request.
     *
     * @param userRepresentation {@link UserEntity}
     * @return {@link UserUpdateRequest}
     */
    UserUpdateRequest toUpdateRequest(UserEntity userRepresentation);

    /**
     * Maps user representation in keycloak to structured user response class.
     *
     * @param representation {@link UserEntity}
     * @return {@link UserResponse}
     */
    UserResponse toResponse(UserEntity representation);

}
