package nu.swe.vehicleservice.user.util;

import nu.swe.vehicleservice.user.enums.UserErrorCode;
import nu.swe.vehicleservice.user.exception.UserException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class contains user's validation related methods.
 */
@Slf4j
public class UserValidationUtil {

    /**
     * Validates if password matches all requirements.
     *
     * @param password user's password
     * @return true if valid, false if invalid
     */
    public static boolean isValidPassword(String password) {
        if (password == null)
            return false;

        Pattern p = Pattern.compile("^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&!+=()-])"
                + "(?=\\S+$).{6,20}$");

        Matcher m = p.matcher(password);
        return m.matches();
    }

    /**
     * This method handles general exceptions and turns them into {@link UserException}.
     *
     * @param errorCode {@link UserErrorCode} error's code to be thrown
     * @param e {@link Exception} general exception
     */
    @SneakyThrows
    public static void handleUserOperationError(UserErrorCode errorCode, Exception e) {
        if (e instanceof UserException) {
            throw e;
        }

        log.error("Caught exception {}, user=[{}]. \n{}", errorCode, e.getMessage(), e.getStackTrace());
        throw new UserException(errorCode);
    }
}
