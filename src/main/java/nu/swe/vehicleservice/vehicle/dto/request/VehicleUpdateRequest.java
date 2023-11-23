package nu.swe.vehicleservice.vehicle.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import nu.swe.vehicleservice.vehicle.dto.response.VehicleResponse;

@Data
public class VehicleUpdateRequest extends VehicleResponse {
    @Schema(description = "vehicle id", example = "1")
    private Integer id;
}
