package nu.swe.vehicleservice.maintenance.exception;

import nu.swe.vehicleservice.core.exception.LocalizedException;
import nu.swe.vehicleservice.maintenance.enums.MaintenanceErrorCode;

/**
 * This is implementation of {@link LocalizedException} when managing users.
 */
public class MaintenanceException extends LocalizedException {

    /**
     * Constructor for error without parameters.
     *
     * @param error {@link MaintenanceErrorCode}
     */
    public MaintenanceException(MaintenanceErrorCode error) {
        super(error);
    }

    /**
     * Constructor for error with parameters.
     *
     * @param error {@link MaintenanceErrorCode} message code
     * @param params params for final error message.
     */
    public MaintenanceException(MaintenanceErrorCode error, Object[] params) {
        super(error, params);
    }

}
