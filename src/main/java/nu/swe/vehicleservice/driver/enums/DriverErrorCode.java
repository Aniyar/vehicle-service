package nu.swe.vehicleservice.driver.enums;

import lombok.Getter;
import nu.swe.vehicleservice.core.enums.BaseErrorCode;

@Getter
public enum DriverErrorCode implements BaseErrorCode {

    DRIVER_NOT_FOUND(404),
    DRIVER_ALREADY_EXISTS(400);


    private final int status;

    DriverErrorCode(int status) {
        this.status = status;
    }

    @Override
    public String getMessageKey() {
        return this.name();
    }
}
