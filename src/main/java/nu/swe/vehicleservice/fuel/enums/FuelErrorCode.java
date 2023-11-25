package nu.swe.vehicleservice.fuel.enums;

import lombok.Getter;
import nu.swe.vehicleservice.core.enums.BaseErrorCode;

@Getter
public enum FuelErrorCode implements BaseErrorCode {

    FUEL_NOT_FOUND(404),
    FUEL_ACCESS_DENIED(403);


    private final int status;

    FuelErrorCode(int status) {
        this.status = status;
    }

    @Override
    public String getMessageKey() {
        return this.name();
    }
}
