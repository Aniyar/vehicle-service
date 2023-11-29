package nu.swe.vehicleservice.maintenance.dto.response;

import lombok.Data;
import nu.swe.vehicleservice.maintenance.enums.MaintenanceType;
import nu.swe.vehicleservice.user.dto.response.UserResponse;
import nu.swe.vehicleservice.vehicle.dto.response.VehicleResponse;


@Data
public class MaintenanceResponse {

    private Long id;

    private MaintenanceType maintenanceType;

    private String details;

    private VehicleResponse vehicle;

    private UserResponse maintenancePersonnel;
}
