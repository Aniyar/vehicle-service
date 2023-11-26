package nu.swe.vehicleservice.vehicle.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VehicleLocationResponse {

    private LocalDateTime createdAt;
    private String latitude;
    private String longitude;
}
