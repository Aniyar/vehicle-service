package nu.swe.vehicleservice.auction.exception;

import nu.swe.vehicleservice.auction.enums.AuctionErrorCode;
import nu.swe.vehicleservice.core.exception.LocalizedException;

/**
 * This is implementation of {@link LocalizedException} when managing users.
 */
public class AuctionException extends LocalizedException {

    /**
     * Constructor for error without parameters.
     *
     * @param error {@link AuctionErrorCode}
     */
    public AuctionException(AuctionErrorCode error) {
        super(error);
    }

    /**
     * Constructor for error with parameters.
     *
     * @param error {@link AuctionErrorCode} message code
     * @param params params for final error message.
     */
    public AuctionException(AuctionErrorCode error, Object[] params) {
        super(error, params);
    }

}
