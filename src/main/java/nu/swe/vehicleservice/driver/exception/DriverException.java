package nu.swe.vehicleservice.driver.exception;

import nu.swe.vehicleservice.core.exception.LocalizedException;
import nu.swe.vehicleservice.driver.enums.DriverErrorCode;

/**
 * This is implementation of {@link LocalizedException} when managing users.
 */
public class DriverException extends LocalizedException {

    /**
     * Constructor for error without parameters.
     *
     * @param error {@link DriverErrorCode}
     */
    public DriverException(DriverErrorCode error) {
        super(error);
    }

    /**
     * Constructor for error with parameters.
     *
     * @param error {@link DriverErrorCode} message code
     * @param params params for final error message.
     */
    public DriverException(DriverErrorCode error, Object[] params) {
        super(error, params);
    }

}
