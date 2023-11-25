package nu.swe.vehicleservice.auction.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AuctionBidRequest {
    @Schema(description = "Auction id", example = "1")
    private Long id;

    @Schema(description = "new bid", example = "200000.23")
    private BigDecimal bid;
}
