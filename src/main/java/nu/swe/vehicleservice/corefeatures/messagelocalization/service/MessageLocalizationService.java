package nu.swe.vehicleservice.corefeatures.messagelocalization.service;

import nu.swe.vehicleservice.corefeatures.messagelocalization.enums.LocaleLanguage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;

/**
 * This is a service for message localizations.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MessageLocalizationService {

    private final MessageSource messageSource;

    /**
     * Get localized message using message code and locale language.
     *
     * @param code String message code.
     * @param language {@link LocaleLanguage} locale language.
     * @return String localized message.
     * @throws NullPointerException if code or language is null.
     * @throws RuntimeException if message wasn't found in bundle.
     */
    public String getMessage(String code, LocaleLanguage language) {
        if (code == null || language == null) {
            throw new NullPointerException("Message code and language should not be null");
        }

        try {
            return messageSource.getMessage(code, null, new Locale(language.name()));
        } catch (NoSuchMessageException exception) {
            String errorMsg = String.format("Message with code %s not found in %s message bundle",
                    code, language.name());
            throw new RuntimeException(errorMsg);
        }
    }

    /**
     * Get localized message using message code. Locale is set to current user locale.
     *
     * @param code String message code.
     * @return String localized message.
     * @throws NullPointerException if code is null.
     * @throws RuntimeException if message wasn't found in bundle.
     */
    public String getMessage(String code) {
        if (code == null) {
            throw new NullPointerException("Message code should not be null");
        }

        try {
            return messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
        } catch (NoSuchMessageException exception) {
            String errorMsg = String.format("Message with code %s not found in %s message bundle",
                    code, LocaleContextHolder.getLocale().toLanguageTag());
            throw new RuntimeException(errorMsg);
        }
    }
}
