package nu.swe.vehicleservice.user.exception;

import nu.swe.vehicleservice.core.exception.LocalizedException;
import nu.swe.vehicleservice.user.enums.UserErrorCode;

/**
 * This is implementation of {@link LocalizedException} when managing users.
 */
public class UserException extends LocalizedException {

    /**
     * Constructor for error without parameters.
     *
     * @param error {@link UserErrorCode}
     */
    public UserException(UserErrorCode error) {
        super(error);
    }

    /**
     * Constructor for error with parameters.
     *
     * @param error {@link UserErrorCode} message code
     * @param params params for final error message.
     */
    public UserException(UserErrorCode error, Object[] params) {
        super(error, params);
    }

}
