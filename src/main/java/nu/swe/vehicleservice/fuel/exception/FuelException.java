package nu.swe.vehicleservice.fuel.exception;

import nu.swe.vehicleservice.core.exception.LocalizedException;
import nu.swe.vehicleservice.fuel.enums.FuelErrorCode;

/**
 * This is implementation of {@link LocalizedException} when managing users.
 */
public class FuelException extends LocalizedException {

    /**
     * Constructor for error without parameters.
     *
     * @param error {@link FuelErrorCode}
     */
    public FuelException(FuelErrorCode error) {
        super(error);
    }

    /**
     * Constructor for error with parameters.
     *
     * @param error {@link FuelErrorCode} message code
     * @param params params for final error message.
     */
    public FuelException(FuelErrorCode error, Object[] params) {
        super(error, params);
    }

}
