package nu.swe.vehicleservice.maintenance.dto.response;

import lombok.Data;
import nu.swe.vehicleservice.maintenance.enums.MaintenanceType;
import nu.swe.vehicleservice.user.dto.response.UserResponse;
import nu.swe.vehicleservice.vehicle.dto.response.VehicleResponse;

import java.math.BigDecimal;

@Data
public class MaintenanceResponse {

    private Long id;

    private MaintenanceType maintenanceType;

    private BigDecimal pricePerLiter;

    private Integer numberLiters;

    private BigDecimal price;

    private VehicleResponse vehicle;

    private UserResponse maintenancePersonnel;
}
