package nu.swe.vehicleservice.route.enums;


import java.util.Arrays;

public enum RouteStatus {
    NEW,
    WAITING,
    IN_PROGRESS,
    COMPLETED,
    CANCELLED;

    public boolean in(RouteStatus... statuses) {
        return Arrays.stream(statuses)
                .anyMatch(status -> status == this);
    }

}
