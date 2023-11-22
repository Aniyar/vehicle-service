package nu.swe.vehicleservice.config;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolation;
import nu.swe.vehicleservice.core.dto.response.ErrorResponse;
import nu.swe.vehicleservice.core.exception.LocalizedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

/**
 * This class is responsible for configuring responses when exception is caught.
 */
@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    /**
     * This method localizes exception message and builds {@link ErrorResponse} with relevant response status code.
     *
     * @param exception {@link LocalizedException} custom exception that can be localized
     * @param response  {@link HttpServletResponse} original response
     * @return {@link ErrorResponse}
     */
    @ExceptionHandler
    public ErrorResponse handleLocalizedError(LocalizedException exception, HttpServletResponse response) {
        log.error("Caught exception '{}'.\n {}", exception.getMessageKey(), exception.getStackTrace());

        String errorMessage = messageSource.getMessage(exception.getMessageKey(), exception.getParams(),
                exception.getLocale());

        response.setStatus(exception.getStatus());
        return new ErrorResponse(errorMessage, HttpStatusCode.valueOf(exception.getStatus()));
    }

    /**
     * Handles the {@link MaxUploadSizeExceededException} which is thrown when the uploaded file's size exceeds
     * the configured maximum size limit.
     *
     * @param exception The exception representing the max upload size exceeded scenario.
     * @return An {@link ErrorResponse} containing the error message and HTTP status.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ErrorResponse handleMaxUploadSizeExceededException(MaxUploadSizeExceededException exception) {
        log.error("Max upload exception '{}'", exception.getMessage(), exception);

        long actualSize = 0;
        long permittedSize = 0;

        if (exception.getRootCause() instanceof SizeLimitExceededException sizeEx) {
            actualSize = sizeEx.getActualSize();
            permittedSize = sizeEx.getPermittedSize();
        }

        String errorMessage = messageSource.getMessage("UPLOAD_SIZE_LIMIT_EXCEEDED",
                new Object[]{actualSize, permittedSize}, LocaleContextHolder.getLocale());

        return new ErrorResponse(errorMessage, HttpStatus.BAD_REQUEST);
    }

    /**
     * Exception handler for annotation based(NotNull, NotBlank) validation.
     * <p>Converts the exception message to the format: {error field name} + ' ' + {localized error message}</p>
     *
     * @param exception {@link MethodArgumentNotValidException}
     * @return {@link ErrorResponse}
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgument(MethodArgumentNotValidException exception) {
        log.error(exception.getMessage(), exception);

        String firstError = exception.getBindingResult().getAllErrors()
                .stream()
                .map(err -> err.unwrap(ConstraintViolation.class))
                .map(err -> err.getPropertyPath() + " " + err.getMessage())
                .findFirst()
                .orElse(null);

        ErrorResponse body = new ErrorResponse(firstError, HttpStatusCode.valueOf(400));
        return ResponseEntity.status(400).body(body);
    }

}
