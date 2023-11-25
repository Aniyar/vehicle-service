package nu.swe.vehicleservice.maintenance.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class MaintenanceUpdateRequest extends MainenanceCreateRequest {
    @Schema(description = "Maintenance id", example = "1")
    private Long id;
}
