package nu.swe.vehicleservice.user.service;

import nu.swe.vehicleservice.core.dto.PageResponse;
import nu.swe.vehicleservice.user.dto.request.UserCreateRequest;
import nu.swe.vehicleservice.user.dto.request.UserGetRequest;
import nu.swe.vehicleservice.user.dto.request.UserPasswordResetRequest;
import nu.swe.vehicleservice.user.dto.response.UserResponse;
import nu.swe.vehicleservice.user.dto.request.UserUpdateRequest;
import org.springframework.data.domain.Pageable;

/**
 * This interface is containing methods for managing users.
 */
public interface UserService {

    /**
     * This method is creating or updating user's password.
     *
     * @param id      {@link Long} user's id in system
     * @param request {@link UserPasswordResetRequest}
     */
    void resetPassword(Long id, UserPasswordResetRequest request);

    /**
     * This method is creating or updating current's user password.
     *
     * @param request {@link UserPasswordResetRequest}
     */
    void resetCurrentUserPassword(UserPasswordResetRequest request);

    /**
     * This method is creating a new user.
     *
     * @param request {@link UserCreateRequest}
     */
    void create(UserCreateRequest request);

    /**
     * This method updates user's info.
     *
     * @param id      {@link Long} user's id in system
     * @param request {@link UserUpdateRequest}
     */
    void update(Long id, UserUpdateRequest request);

    /**
     * This method updates current user's info. Id is taken from auth token.
     *
     * @param request {@link UserUpdateRequest}
     */
    void update(UserUpdateRequest request);

    /**
     * This method finds existing user by id in system.
     *
     * @param id {@link String} user's id in system
     * @return {@link UserResponse}
     */
    UserResponse findById(Long id);

    /**
     * This method finds current user in system. Id is taken from auth token.
     *
     * @return {@link UserResponse}
     */
    UserResponse findCurrentUser();

    /**
     * This method finds all users in system by search parameters.
     *
     * @param request {@link UserGetRequest} search parameters
     * @return {@link PageResponse} page with {@link UserResponse}
     */
    PageResponse<UserResponse> findAllPageable(UserGetRequest request, Pageable pageable);

    /**
     * This method deletes user from system.
     *
     * @param id {@link String} user's id
     */
    void deleteById(Long id);

}
