package nu.swe.vehicleservice.driver.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import nu.swe.vehicleservice.core.dto.PageResponse;
import nu.swe.vehicleservice.driver.dto.request.DriverCreateRequest;
import nu.swe.vehicleservice.driver.dto.request.DriverUpdateRequest;
import nu.swe.vehicleservice.driver.dto.response.DriverResponse;
import nu.swe.vehicleservice.driver.service.DriverService;
import nu.swe.vehicleservice.security.authorities.Authority;
import nu.swe.vehicleservice.user.dto.response.UserResponse;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/drivers")
@RequiredArgsConstructor
@Tag(name = "Drivers API controller", description = "Drivers")
public class DriverController {

    private final DriverService driverService;

    @Operation(summary = "Find driver by id", description = "Find existing driver by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(schema = @Schema(implementation = UserResponse.class),
                            mediaType = "application/json"))})
    @PreAuthorize(Authority.AUTHENTICATED)
    @GetMapping("/{id}")
    public DriverResponse findById(@PathVariable("id") Long id) {
        return driverService.findById(id);
    }

    @Operation(summary = "Find all drivers", description = "Find all drivers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(schema = @Schema(implementation = UserResponse.class),
                            mediaType = "application/json"))})
    @PreAuthorize(Authority.ADMIN_OR_MANAGER)
    @GetMapping("/all")
    public PageResponse<DriverResponse> findAll(@ParameterObject Pageable pageable) {
        return driverService.findAll(pageable);
    }

    @Operation(summary = "Find all drivers", description = "Find all drivers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(schema = @Schema(implementation = UserResponse.class),
                            mediaType = "application/json"))})
    @PreAuthorize(Authority.DRIVER)
    @PutMapping()
    public void update(@Valid @RequestBody DriverUpdateRequest request) {
        driverService.update(request);
    }

    @Operation(summary = "Find current driver", description = "Find current driver")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(schema = @Schema(implementation = UserResponse.class),
                            mediaType = "application/json"))})
    @PreAuthorize(Authority.DRIVER)
    @GetMapping("/current")
    public DriverResponse findCurrentDriver() {
        return driverService.findCurrent();
    }

    @Operation(summary = "Create driver profile", description = "Create driver profile")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(schema = @Schema(implementation = UserResponse.class),
                            mediaType = "application/json"))})
    @PreAuthorize(Authority.DRIVER)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody DriverCreateRequest request) {
        driverService.create(request);
    }

    @Operation(summary = "Assign vehicle to driver", description = "Assign vehicle to driver")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(schema = @Schema(implementation = UserResponse.class),
                            mediaType = "application/json"))})
    @PreAuthorize(Authority.ADMIN_OR_MANAGER)
    @PutMapping("/{id}/assign-vehicle/{vehicleId}")
    public void create(@PathVariable Long id, @PathVariable Long vehicleId) {
        driverService.assignVehicle(id, vehicleId);
    }

    @Operation(summary = "Delete driver", description = "Delete driver by id. ADMIN AUTHENTICATED")
    @PreAuthorize(Authority.ADMIN)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { driverService.delete(id);
    }
}
