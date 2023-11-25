package nu.swe.vehicleservice.auction.dto.response;

import lombok.Data;
import nu.swe.vehicleservice.auction.enums.AuctionStatus;
import nu.swe.vehicleservice.user.dto.response.UserResponse;
import nu.swe.vehicleservice.vehicle.dto.response.VehicleResponse;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class AuctionResponse {

    private Long id;

    private VehicleResponse vehicle;

    private Date startDate;

    private Date endDate;

    private BigDecimal currentBid;

    private AuctionStatus status;

    private UserResponse currentBidHolder;
}
