package nu.swe.vehicleservice.route.exception;

import nu.swe.vehicleservice.core.exception.LocalizedException;
import nu.swe.vehicleservice.route.enums.RouteErrorCode;

/**
 * This is implementation of {@link LocalizedException} when managing users.
 */
public class RouteException extends LocalizedException {

    /**
     * Constructor for error without parameters.
     *
     * @param error {@link RouteErrorCode}
     */
    public RouteException(RouteErrorCode error) {
        super(error);
    }

    /**
     * Constructor for error with parameters.
     *
     * @param error {@link RouteErrorCode} message code
     * @param params params for final error message.
     */
    public RouteException(RouteErrorCode error, Object[] params) {
        super(error, params);
    }

}
