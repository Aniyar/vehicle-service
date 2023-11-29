package nu.swe.vehicleservice.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import nu.swe.vehicleservice.core.dto.PageResponse;
import nu.swe.vehicleservice.core.dto.response.ErrorResponse;
import nu.swe.vehicleservice.security.authorities.Authority;
import nu.swe.vehicleservice.user.dto.request.UserCreateRequest;
import nu.swe.vehicleservice.user.dto.request.UserGetRequest;
import nu.swe.vehicleservice.user.dto.request.UserPasswordResetRequest;
import nu.swe.vehicleservice.user.dto.response.UserResponse;
import nu.swe.vehicleservice.user.dto.request.UserUpdateRequest;
import nu.swe.vehicleservice.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;



/**
 * This class is a controller for managing users.
 */
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "Users API controller", description = "Managing system users.")
public class UserController {

    private final UserService userService;

    /**
     * Update or create password for user.
     *
     * @param id      {@link Long} user's id
     * @param request {@link UserPasswordResetRequest}
     */
    @Operation(summary = "Update or create password", description = "Updates or creates password for user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Authorization error",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "403", description = "Access denied. Available only for admin",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500", description = "Unknown error (Keycloak error)",
                    content = @Content(schema = @Schema()))})
    @PreAuthorize(Authority.AUTHENTICATED)
    @PutMapping("/{id}/password")
    public void resetPassword(@PathVariable("id") Long id, @Valid @RequestBody UserPasswordResetRequest request) {
        userService.resetPassword(id, request);
    }

    /**
     * Update or create password for current user.
     *
     * @param request {@link UserPasswordResetRequest}
     */
    @Operation(summary = "Update or create current user's password", description = "Updates or creates password "
            + "for current user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Authorization error",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "403", description = "Access denied. Available for admin or manager",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500", description = "Unknown error (Keycloak error)",
                    content = @Content(schema = @Schema()))})
    @PreAuthorize(Authority.AUTHENTICATED)
    @PutMapping("/password")
    public void resetCurrentUserPassword(@Valid @RequestBody UserPasswordResetRequest request) {
        userService.resetCurrentUserPassword(request);
    }

    /**
     * Create new user in system.
     *
     * @param request {@link UserCreateRequest}
     */
    @Operation(summary = "Create new user", description = "Creates new users in system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "Conflict",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Authorization error",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "403", description = "Access denied. Available only for admin",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500", description = "Unknown error (Keycloak error)",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))})
    @PreAuthorize(Authority.ADMIN)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody UserCreateRequest request) {
        userService.create(request);
    }

    /**
     * Find existing user by id.
     *
     * @param id {@link String} user's uuid
     * @return {@link UserResponse}
     */
    @Operation(summary = "Find user by id", description = "Find existing user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(schema = @Schema(implementation = UserResponse.class),
                            mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Authorization error",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "403", description = "Access denied. Available for admin",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500", description = "Unknown error (Keycloak error)",
                    content = @Content(schema = @Schema()))})
    @PreAuthorize(Authority.ADMIN_OR_MANAGER)
    @GetMapping("/{id}")
    public UserResponse findById(@PathVariable("id") Long id) {
        return userService.findById(id);
    }

    /**
     * Find current user by id from auth token.
     *
     * @return {@link UserResponse}
     */
    @Operation(summary = "Find current user", description = "Find existing user by id from auth token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(schema = @Schema(implementation = UserResponse.class),
                            mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Authorization error",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500", description = "Unknown error (Keycloak error)",
                    content = @Content(schema = @Schema()))})
    @PreAuthorize(Authority.AUTHENTICATED)
    @GetMapping("/my")
    public UserResponse findCurrentUser() {
        return userService.findCurrentUser();
    }

    /**
     * Find all users pageable by search parameters.
     *
     * @param request {@link UserGetRequest} search parameters
     * @return {@link PageResponse} page with {@link UserResponse}
     */
    @Operation(summary = "Find all users", description = "Find all users pageable by search parameters")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Authorization error",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "403", description = "Access denied. Available only for admin",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500", description = "Unknown error (Keycloak error)",
                    content = @Content(schema = @Schema()))})
    @PreAuthorize(Authority.ADMIN_OR_MANAGER)
    @GetMapping
    public PageResponse<UserResponse> findAllPageable(@Valid @ParameterObject UserGetRequest request,
                                                      @ParameterObject Pageable pageable) {
        return userService.findAllPageable(request, pageable);
    }

    /**
     * Update existing user's info in system.
     *
     * @param id      {@link Long} user's unique id
     * @param request {@link UserUpdateRequest}
     */
    @Operation(summary = "Update user", description = "Update existing user' info by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Success",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Authorization error",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "403", description = "Access denied. Available only for admin",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500", description = "Unknown error (Keycloak error)",
                    content = @Content(schema = @Schema()))})
    @PreAuthorize(Authority.AUTHENTICATED)
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") Long id, @Valid @RequestBody UserUpdateRequest request) {
        userService.update(id, request);
    }

    /**
     * Update current user's info in system.
     *
     * @param request {@link UserUpdateRequest}
     */
    @Operation(summary = "Update current user", description = "Update current user's info by id from token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Success",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Authorization error",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "403", description = "Access denied. Available for admin and manager",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500", description = "Unknown error (Keycloak error)",
                    content = @Content(schema = @Schema()))})
    @PreAuthorize(Authority.AUTHENTICATED)
    @PutMapping("/my")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody UserUpdateRequest request) {
        userService.update(request);
    }

    /**
     * Delete existing user by id.
     *
     * @param id {@link Long} user's id
     */
    @Operation(summary = "Delete user", description = "Delete existing user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Success",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Authorization error",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "403", description = "Access denied. Available only for admin",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500", description = "Unknown error (Keycloak error)",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))})
    @PreAuthorize(Authority.ADMIN)
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Long id) {
        userService.deleteById(id);
    }

}
