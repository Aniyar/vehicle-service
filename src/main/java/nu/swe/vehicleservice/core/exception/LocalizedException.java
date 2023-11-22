package nu.swe.vehicleservice.core.exception;

import nu.swe.vehicleservice.core.enums.BaseErrorCode;
import lombok.Getter;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

/**
 * This class is a base custom exception with localization.
 */
@Getter
public abstract class LocalizedException extends RuntimeException {

    private final String messageKey;
    private final Object[] params;
    private final Locale locale;
    private final int status;


    /**
     * Constructor for error messages without parameters.
     *
     * @param error {@link BaseErrorCode}
     */
    public LocalizedException(BaseErrorCode error) {
        this.messageKey = error.getMessageKey();
        this.params = new Object[]{};
        this.locale = LocaleContextHolder.getLocale();
        this.status = error.getStatus();
    }

    /**
     * Constructor for error messages with parameters.
     *
     * @param error  {@link BaseErrorCode}
     * @param params parameter to be replaced in final error message.
     */
    public LocalizedException(BaseErrorCode error, Object[] params) {
        this.messageKey = error.getMessageKey();
        this.params = params;
        this.locale = LocaleContextHolder.getLocale();
        this.status = error.getStatus();
    }

}
