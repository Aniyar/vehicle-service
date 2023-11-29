package nu.swe.vehicleservice.route.dto.response;

import lombok.Data;
import nu.swe.vehicleservice.driver.dto.response.DriverResponse;
import nu.swe.vehicleservice.route.enums.RouteStatus;
import nu.swe.vehicleservice.user.dto.response.UserResponse;
import nu.swe.vehicleservice.vehicle.dto.response.VehicleResponse;
import java.time.LocalDateTime;

@Data
public class RouteResponse {

    private Long id;

    private DriverResponse driver;

    private UserResponse staff;

    private VehicleResponse vehicle;

    private String startPoint;

    private String startLat;

    private String startLon;

    private String endPoint;

    private String endLat;

    private String endLon;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private RouteStatus status;
}
