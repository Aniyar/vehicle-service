package nu.swe.vehicleservice.auction.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;


@Data
public class AuctionCreateRequest {

    @NotNull
    @Schema(description = "Vehicle id", example = "1")
    private Long vehicleId;

    @NotNull
    @Schema(description = "start date", example = "2023-11-18")
    private LocalDate startDate;

    @NotNull
    @Schema(description = "end date", example = "2024-11-18")
    private LocalDate endDate;

    @NotNull
    @Schema(description = "current bid", example = "10000000")
    private BigDecimal currentBid;
}
