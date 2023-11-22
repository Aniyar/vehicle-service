package nu.swe.vehicleservice.user.enums;

import nu.swe.vehicleservice.core.enums.BaseErrorCode;
import lombok.Getter;

/**
 * This enum contains codes that are related to the user managing errors.
 * These codes should be used as error references in 'errors' resource bundle.
 */
@Getter
public enum UserErrorCode implements BaseErrorCode {

    USER_PASSWORDS_DOESNT_MATCH(400),
    USER_LOGIN_IS_INVALID(400),
    USER_PASSWORD_IS_INVALID(400),
    USER_ROLE_DOES_NOT_EXISTS(400),
    USER_ALREADY_EXIST(409),
    USER_CREATION_ERROR(500),
    USER_DELETION_ERROR(500),
    USER_NOT_FOUND(404),
    USER_CAN_NOT_DELETE_APPLICANT(400),
    USER_ALREADY_DISABLED(400);

    private final int status;

    UserErrorCode(int status) {
        this.status = status;
    }

    @Override
    public String getMessageKey() {
        return this.name();
    }
}
