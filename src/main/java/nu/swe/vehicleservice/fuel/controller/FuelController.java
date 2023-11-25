package nu.swe.vehicleservice.fuel.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import nu.swe.vehicleservice.core.dto.PageResponse;
import nu.swe.vehicleservice.fuel.dto.request.FuelCreateRequest;
import nu.swe.vehicleservice.fuel.dto.request.FuelGetRequest;
import nu.swe.vehicleservice.fuel.dto.request.FuelUpdateRequest;
import nu.swe.vehicleservice.fuel.dto.response.FuelResponse;
import nu.swe.vehicleservice.fuel.service.FuelService;
import nu.swe.vehicleservice.security.authorities.Authority;
import nu.swe.vehicleservice.user.dto.response.UserResponse;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fuels")
@RequiredArgsConstructor
@Tag(name = "Fuels API controller", description = "Fuels")
public class FuelController {

    private final FuelService fuelService;

    @Operation(summary = "Find fuel by id", description = "Find existing fuel by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(schema = @Schema(implementation = UserResponse.class),
                            mediaType = "application/json"))})
    @PreAuthorize(Authority.ADMIN_OR_FUEL)
    @GetMapping("/{id}")
    public FuelResponse findById(@PathVariable("id") Long id) {
        return fuelService.findById(id);
    }

    @Operation(summary = "Find all fuels", description = "Find all fuels")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(schema = @Schema(implementation = UserResponse.class),
                            mediaType = "application/json"))})
    @PreAuthorize(Authority.ADMIN_OR_FUEL)
    @GetMapping("/all")
    public PageResponse<FuelResponse> findAll(@ParameterObject FuelGetRequest request, @ParameterObject Pageable pageable) {
        return fuelService.findAll(request, pageable);
    }

    @Operation(summary = "Update fuels", description = "Update fuels")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(schema = @Schema(implementation = UserResponse.class),
                            mediaType = "application/json"))})
    @PreAuthorize(Authority.FUEL)
    @PutMapping()
    public void update(@Valid @RequestBody FuelUpdateRequest request) {
        fuelService.update(request);
    }

    @Operation(summary = "Create fuel", description = "Create fuel")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(schema = @Schema(implementation = UserResponse.class),
                            mediaType = "application/json"))})
    @PreAuthorize(Authority.FUEL)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody FuelCreateRequest request) {
        fuelService.create(request);
    }
}
