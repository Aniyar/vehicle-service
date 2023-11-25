package nu.swe.vehicleservice.driver.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class DriverUpdateRequest extends DriverCreateRequest {
    @Schema(description = "Driver id", example = "1")
    private Long id;
}
