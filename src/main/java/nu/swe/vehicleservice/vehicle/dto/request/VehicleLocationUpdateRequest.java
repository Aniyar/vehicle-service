package nu.swe.vehicleservice.vehicle.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class VehicleLocationUpdateRequest {

    @NotBlank
    @Schema(description = "Vehicle longitude", example = "41.40338")
    private String longitude;

    @NotBlank
    @Schema(description = "Vehicle latitude", example = "2.17403")
    private String latitude;
}
