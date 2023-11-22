package nu.swe.vehicleservice.core.enums;

import nu.swe.vehicleservice.core.exception.LocalizedException;

/**
 * This interface describes feature error codes implementations. Is used for {@link LocalizedException}.
 */
public interface BaseErrorCode {

    /**
     * This method return error's message key.
     *
     * @return {@link String} is used in 'errors' resource bundle.
     */
    String getMessageKey();


    /**
     * This method return http error status.
     *
     * @return {@link Integer} 200, 400, 404, etc.
     */
    int getStatus();

}
