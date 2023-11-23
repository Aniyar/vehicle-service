package nu.swe.vehicleservice.driver.dto.response;

import lombok.Data;
import nu.swe.vehicleservice.user.dto.response.UserResponse;
import nu.swe.vehicleservice.vehicle.dto.response.VehicleResponse;

import java.math.BigDecimal;

@Data
public class DriverResponse {

    private Long id;

    private String address;

    private String licenseNumber;

    private BigDecimal rating;

    private VehicleResponse vehicle;

    private UserResponse user;
}
