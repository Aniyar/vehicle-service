package nu.swe.vehicleservice.vehicle.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import nu.swe.vehicleservice.core.dto.PageResponse;
import nu.swe.vehicleservice.security.authorities.Authority;
import nu.swe.vehicleservice.user.dto.response.UserResponse;
import nu.swe.vehicleservice.vehicle.dto.request.VehicleCreateRequest;
import nu.swe.vehicleservice.vehicle.dto.request.VehicleUpdateRequest;
import nu.swe.vehicleservice.vehicle.dto.response.VehicleResponse;
import nu.swe.vehicleservice.vehicle.repository.VehicleRepository;
import nu.swe.vehicleservice.vehicle.service.VehicleService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vehicles")
@RequiredArgsConstructor
@Tag(name = "Vehicles API controller", description = "Vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    @Operation(summary = "Create vehicle", description = "Create vehicle")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(schema = @Schema(implementation = UserResponse.class),
                            mediaType = "application/json"))})
    @PreAuthorize(Authority.ADMIN_OR_MANAGER)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody VehicleCreateRequest request) {
        vehicleService.create(request);
    }

    @Operation(summary = "Find all vehicles", description = "Find all vehicles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(schema = @Schema(implementation = UserResponse.class),
                            mediaType = "application/json"))})
    @PreAuthorize(Authority.ADMIN_OR_MANAGER)
    @GetMapping("/all")
    public PageResponse<VehicleResponse> findAll(@ParameterObject Pageable pageable) {
        return vehicleService.findAll(pageable);
    }

    @Operation(summary = "Find vehicle by id", description = "Find vehicle by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(schema = @Schema(implementation = UserResponse.class),
                            mediaType = "application/json"))})
    @PreAuthorize(Authority.ADMIN_OR_MANAGER)
    @GetMapping("/{id}")
    public VehicleResponse findById(@PathVariable Integer id) {
        return vehicleService.findById(id);
    }

    @Operation(summary = "Update vehicle", description = "Update vehicle")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(schema = @Schema(implementation = UserResponse.class),
                            mediaType = "application/json"))})
    @PreAuthorize(Authority.ADMIN_OR_MANAGER)
    @PutMapping()
    public void update(@Valid @RequestBody VehicleUpdateRequest request) {
        vehicleService.update(request);
    }
}
