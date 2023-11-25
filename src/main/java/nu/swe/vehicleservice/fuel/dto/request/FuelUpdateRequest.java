package nu.swe.vehicleservice.fuel.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class FuelUpdateRequest extends FuelCreateRequest {
    @Schema(description = "Fueling id", example = "1")
    private Long id;
}
