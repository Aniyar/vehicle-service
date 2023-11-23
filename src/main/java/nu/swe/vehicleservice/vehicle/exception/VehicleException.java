package nu.swe.vehicleservice.vehicle.exception;

import nu.swe.vehicleservice.core.exception.LocalizedException;
import nu.swe.vehicleservice.vehicle.enums.VehicleErrorCode;

/**
 * This is implementation of {@link LocalizedException} when managing users.
 */
public class VehicleException extends LocalizedException {

    /**
     * Constructor for error without parameters.
     *
     * @param error {@link VehicleErrorCode}
     */
    public VehicleException(VehicleErrorCode error) {
        super(error);
    }

    /**
     * Constructor for error with parameters.
     *
     * @param error {@link VehicleErrorCode} message code
     * @param params params for final error message.
     */
    public VehicleException(VehicleErrorCode error, Object[] params) {
        super(error, params);
    }

}
