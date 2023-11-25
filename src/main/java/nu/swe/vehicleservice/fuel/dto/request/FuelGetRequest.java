package nu.swe.vehicleservice.fuel.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nu.swe.vehicleservice.fuel.enums.FuelType;

/**
 * This class is a DTO representation of get pageable users list search parameters.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FuelGetRequest {

    @Schema(description = "Search parameter for vehicle id", example = "1")
    private Integer vehicleId;

    @Schema(description = "Search parameter for fuel personnel id", example = "1")
    private Integer fuelPersonnelId;

    @Schema(description = "Search parameter for fuel type", example = "GAS")
    private FuelType fuelType;
}

