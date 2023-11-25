package nu.swe.vehicleservice.auction.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AuctionUpdateRequest extends AuctionCreateRequest {
    @Schema(description = "Auction id", example = "1")
    private Long id;
}
