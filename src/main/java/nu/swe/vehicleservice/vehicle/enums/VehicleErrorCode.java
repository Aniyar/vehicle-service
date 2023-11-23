package nu.swe.vehicleservice.vehicle.enums;

import lombok.Getter;
import nu.swe.vehicleservice.core.enums.BaseErrorCode;

@Getter
public enum VehicleErrorCode implements BaseErrorCode {

    VEHICLE_NOT_FOUND(404);


    private final int status;

    VehicleErrorCode(int status) {
        this.status = status;
    }

    @Override
    public String getMessageKey() {
        return this.name();
    }
}
