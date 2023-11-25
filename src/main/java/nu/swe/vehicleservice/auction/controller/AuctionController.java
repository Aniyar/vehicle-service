package nu.swe.vehicleservice.auction.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import nu.swe.vehicleservice.auction.dto.request.AuctionBidRequest;
import nu.swe.vehicleservice.auction.enums.AuctionStatus;
import nu.swe.vehicleservice.core.dto.PageResponse;
import nu.swe.vehicleservice.auction.dto.request.AuctionCreateRequest;
import nu.swe.vehicleservice.auction.dto.request.AuctionGetRequest;
import nu.swe.vehicleservice.auction.dto.request.AuctionUpdateRequest;
import nu.swe.vehicleservice.auction.dto.response.AuctionResponse;
import nu.swe.vehicleservice.auction.service.AuctionService;
import nu.swe.vehicleservice.security.authorities.Authority;
import nu.swe.vehicleservice.user.dto.response.UserResponse;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auctions")
@RequiredArgsConstructor
@Tag(name = "Auctions API controller", description = "Auctions")
public class AuctionController {

    private final AuctionService auctionService;

    @Operation(summary = "Find auction by id", description = "Find existing auction by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(schema = @Schema(implementation = UserResponse.class),
                            mediaType = "application/json"))})
    @PreAuthorize(Authority.ADMIN)
    @GetMapping("/{id}")
    public AuctionResponse findById(@PathVariable("id") Long id) {
        return auctionService.findById(id);
    }

    @Operation(summary = "Find all auctions", description = "Find all auctions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(schema = @Schema(implementation = UserResponse.class),
                            mediaType = "application/json"))})
    @GetMapping("/all")
    public PageResponse<AuctionResponse> findAll(@ParameterObject AuctionGetRequest request, @ParameterObject Pageable pageable) {
        return auctionService.findAll(request, pageable);
    }

    @Operation(summary = "Update auctions", description = "Update auctions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(schema = @Schema(implementation = UserResponse.class),
                            mediaType = "application/json"))})
    @PreAuthorize(Authority.ADMIN)
    @PutMapping()
    public void update(@Valid @RequestBody AuctionUpdateRequest request) {
        auctionService.update(request);
    }

    @Operation(summary = "Update bid", description = "Update bid")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(schema = @Schema(implementation = UserResponse.class),
                            mediaType = "application/json"))})
    @PreAuthorize(Authority.AUTHENTICATED)
    @PutMapping("/bid")
    public void bid(@Valid @RequestBody AuctionBidRequest request) {
        auctionService.bid(request);
    }

    @Operation(summary = "Change status", description = "Change status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(schema = @Schema(implementation = UserResponse.class),
                            mediaType = "application/json"))})
    @PreAuthorize(Authority.ADMIN)
    @PutMapping("/{id}/change-status/{status}")
    public void bid(@PathVariable Long id, @PathVariable AuctionStatus status) {
        auctionService.changeStatus(id, status);
    }

    @Operation(summary = "Create auction", description = "Create auction")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(schema = @Schema(implementation = UserResponse.class),
                            mediaType = "application/json"))})
    @PreAuthorize(Authority.ADMIN)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody AuctionCreateRequest request) {
        auctionService.create(request);
    }
}
