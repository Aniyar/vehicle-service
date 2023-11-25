package nu.swe.vehicleservice.maintenance.enums;

import lombok.Getter;
import nu.swe.vehicleservice.core.enums.BaseErrorCode;

@Getter
public enum MaintenanceErrorCode implements BaseErrorCode {

    MAINTENANCE_NOT_FOUND(404);


    private final int status;

    MaintenanceErrorCode(int status) {
        this.status = status;
    }

    @Override
    public String getMessageKey() {
        return this.name();
    }
}
