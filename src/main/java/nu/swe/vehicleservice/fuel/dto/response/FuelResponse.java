package nu.swe.vehicleservice.fuel.dto.response;

import lombok.Data;
import nu.swe.vehicleservice.fuel.enums.FuelType;
import nu.swe.vehicleservice.user.dto.response.UserResponse;
import nu.swe.vehicleservice.vehicle.dto.response.VehicleResponse;

import java.math.BigDecimal;

@Data
public class FuelResponse {

    private Long id;

    private FuelType fuelType;

    private BigDecimal pricePerLiter;

    private Integer numberLiters;

    private BigDecimal price;

    private VehicleResponse vehicle;

    private UserResponse fuelPersonnel;
}
