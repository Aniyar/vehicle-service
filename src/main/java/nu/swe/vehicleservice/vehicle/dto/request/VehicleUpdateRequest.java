package nu.swe.vehicleservice.vehicle.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class VehicleUpdateRequest extends VehicleCreateRequest {
    @Schema(description = "vehicle id", example = "1")
    private Long id;
}
