package nu.swe.vehicleservice.maintenance.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nu.swe.vehicleservice.maintenance.enums.MaintenanceType;

/**
 * This class is a DTO representation of get pageable users list search parameters.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaintenanceGetRequest {

    @Schema(description = "Search parameter for vehicle id", example = "1")
    private Integer vehicleId;

    @Schema(description = "Search parameter for maintenance personnel id", example = "1")
    private Integer maintenancePersonnelId;

    @Schema(description = "Search parameter for maintenance type", example = "REPAIR")
    private MaintenanceType maintenanceType;
}

