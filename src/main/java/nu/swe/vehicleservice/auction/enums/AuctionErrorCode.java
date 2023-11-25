package nu.swe.vehicleservice.auction.enums;

import lombok.Getter;
import nu.swe.vehicleservice.core.enums.BaseErrorCode;

@Getter
public enum AuctionErrorCode implements BaseErrorCode {

    AUCTION_NOT_FOUND(404),
    AUCTION_INVALID_BID(400);


    private final int status;

    AuctionErrorCode(int status) {
        this.status = status;
    }

    @Override
    public String getMessageKey() {
        return this.name();
    }
}
