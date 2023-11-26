package nu.swe.vehicleservice.route.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class is a DTO representation of get pageable users list search parameters.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RouteGetRequest {

    @Schema(description = "Search parameter for vehicle id", example = "1")
    private Integer vehicleId;

    @Schema(description = "Search parameter for staff personnel id", example = "1")
    private Integer staffId;

    @Schema(description = "Search parameter for driver id", example = "1")
    private Integer driverId;
}

