package nu.swe.vehicleservice.route.enums;

import lombok.Getter;
import nu.swe.vehicleservice.core.enums.BaseErrorCode;

@Getter
public enum RouteErrorCode implements BaseErrorCode {

    ROUTE_NOT_FOUND(404),
    ROUTE_NO_VEHICLE_ASSIGNED_TO_DRIVER(400);


    private final int status;

    RouteErrorCode(int status) {
        this.status = status;
    }

    @Override
    public String getMessageKey() {
        return this.name();
    }
}
