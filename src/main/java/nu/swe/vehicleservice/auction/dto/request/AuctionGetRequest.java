package nu.swe.vehicleservice.auction.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nu.swe.vehicleservice.auction.enums.AuctionStatus;

/**
 * This class is a DTO representation of get pageable users list search parameters.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuctionGetRequest {

    @Schema(description = "Search parameter for auction id", example = "1")
    private Integer vehicleId;

    @Schema(description = "Search parameter for auction status", example = "BIDDING")
    private AuctionStatus status;
}

