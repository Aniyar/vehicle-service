package nu.swe.vehicleservice.vehicle.dto.response;

import lombok.Data;
import nu.swe.vehicleservice.driver.dto.response.DriverResponse;
import nu.swe.vehicleservice.fuel.enums.FuelType;

@Data
public class VehicleResponse {

    private Long id;
    private String model;
    private Integer year;
    private String licencePlate;
    private Integer capacity;
    private String vin;
    private FuelType fuelType;
    private DriverResponse driver;
}
