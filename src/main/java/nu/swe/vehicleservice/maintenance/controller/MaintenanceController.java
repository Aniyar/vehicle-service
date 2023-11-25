package nu.swe.vehicleservice.maintenance.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import nu.swe.vehicleservice.core.dto.PageResponse;
import nu.swe.vehicleservice.maintenance.dto.request.MainenanceCreateRequest;
import nu.swe.vehicleservice.maintenance.dto.request.MaintenanceGetRequest;
import nu.swe.vehicleservice.maintenance.dto.request.MaintenanceUpdateRequest;
import nu.swe.vehicleservice.maintenance.dto.response.MaintenanceResponse;
import nu.swe.vehicleservice.maintenance.service.MaintenanceService;
import nu.swe.vehicleservice.security.authorities.Authority;
import nu.swe.vehicleservice.user.dto.response.UserResponse;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/maintenances")
@RequiredArgsConstructor
@Tag(name = "Maintenances API controller", description = "Maintenances")
public class MaintenanceController {

    private final MaintenanceService maintenanceService;

    @Operation(summary = "Find maintenance by id", description = "Find existing maintenance by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(schema = @Schema(implementation = UserResponse.class),
                            mediaType = "application/json"))})
    @PreAuthorize(Authority.ADMIN_OR_MAINTENANCE)
    @GetMapping("/{id}")
    public MaintenanceResponse findById(@PathVariable("id") Long id) {
        return maintenanceService.findById(id);
    }

    @Operation(summary = "Find all maintenances", description = "Find all maintenances")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(schema = @Schema(implementation = UserResponse.class),
                            mediaType = "application/json"))})
    @PreAuthorize(Authority.ADMIN_OR_MAINTENANCE)
    @GetMapping("/all")
    public PageResponse<MaintenanceResponse> findAll(@ParameterObject MaintenanceGetRequest request, @ParameterObject Pageable pageable) {
        return maintenanceService.findAll(request, pageable);
    }

    @Operation(summary = "Update maintenances", description = "Update maintenances")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(schema = @Schema(implementation = UserResponse.class),
                            mediaType = "application/json"))})
    @PreAuthorize(Authority.MAINTENANCE)
    @PutMapping()
    public void update(@Valid @RequestBody MaintenanceUpdateRequest request) {
        maintenanceService.update(request);
    }

    @Operation(summary = "Create maintenance", description = "Create maintenance")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(schema = @Schema(implementation = UserResponse.class),
                            mediaType = "application/json"))})
    @PreAuthorize(Authority.MAINTENANCE)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody MainenanceCreateRequest request) {
        maintenanceService.create(request);
    }
}
