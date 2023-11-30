package nu.swe.vehicleservice.route.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import nu.swe.vehicleservice.core.dto.PageResponse;
import nu.swe.vehicleservice.route.dto.request.RouteCreateRequest;
import nu.swe.vehicleservice.route.dto.request.RouteGenerateReportRequest;
import nu.swe.vehicleservice.route.dto.request.RouteGetRequest;
import nu.swe.vehicleservice.route.dto.request.RouteUpdateRequest;
import nu.swe.vehicleservice.route.dto.response.RouteReportResponse;
import nu.swe.vehicleservice.route.dto.response.RouteResponse;
import nu.swe.vehicleservice.route.enums.RouteStatus;
import nu.swe.vehicleservice.route.service.RouteService;
import nu.swe.vehicleservice.security.authorities.Authority;
import nu.swe.vehicleservice.user.dto.response.UserResponse;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/routes")
@RequiredArgsConstructor
@Tag(name = "Routes API controller", description = "Routes")
public class RouteController {

    private final RouteService routeService;

    @Operation(summary = "Find route by id", description = "Find existing route by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(schema = @Schema(implementation = UserResponse.class),
                            mediaType = "application/json"))})
    @PreAuthorize(Authority.AUTHENTICATED)
    @GetMapping("/{id}")
    public RouteResponse findById(@PathVariable("id") Long id) {
        return routeService.findById(id);
    }

    @Operation(summary = "Find all routes", description = "Find all routes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(schema = @Schema(implementation = UserResponse.class),
                            mediaType = "application/json"))})
    @PreAuthorize(Authority.AUTHENTICATED)
    @GetMapping("/all")
    public PageResponse<RouteResponse> findAll(@ParameterObject RouteGetRequest request, @ParameterObject Pageable pageable) {
        return routeService.findAll(request, pageable);
    }

    @Operation(summary = "Update routes", description = "Update routes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(schema = @Schema(implementation = UserResponse.class),
                            mediaType = "application/json"))})
    @PreAuthorize(Authority.AUTHENTICATED)
    @PutMapping()
    public void update(@Valid @RequestBody RouteUpdateRequest request) {
        routeService.update(request);
    }


    @Operation(summary = "Change ride status", description = "Change ride status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(schema = @Schema(implementation = UserResponse.class),
                            mediaType = "application/json"))})
    @PreAuthorize(Authority.DRIVER_OR_STAFF)
    @PutMapping("/{id}/change-status/{status}")
    public void update(@PathVariable Long id, @PathVariable RouteStatus status) {
        routeService.changeStatus(id, status);
    }

    @Operation(summary = "Assign driver to route", description = "Assign driver to route")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(schema = @Schema(implementation = UserResponse.class),
                            mediaType = "application/json"))})
    @PreAuthorize(Authority.ADMIN_OR_DRIVER)
    @PutMapping("/{id}/assign-driver/{driverId}")
    public void assignDriver(@PathVariable Long id, @PathVariable Long driverId) {
        routeService.assignDriver(id, driverId);
    }

    @Operation(summary = "Create route", description = "Create route")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(schema = @Schema(implementation = UserResponse.class),
                            mediaType = "application/json"))})
    @PreAuthorize(Authority.AUTHENTICATED)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody RouteCreateRequest request) {
        routeService.create(request);
    }

    @Operation(summary = "Delete route", description = "Delete route by id. ADMIN AUTHENTICATED")
    @PreAuthorize(Authority.ADMIN)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        routeService.delete(id);
    }

    @Operation(summary = "Generate route report", description = "Generate route report. ADMIN AUTHENTICATED")
    @PreAuthorize(Authority.ADMIN)
    @PostMapping("/report")
    public RouteReportResponse generateReport(@Valid @RequestBody RouteGenerateReportRequest request) {
        return routeService.generateReport(request);
    }
}
