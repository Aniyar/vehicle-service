package nu.swe.vehicleservice.route.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RouteUpdateRequest extends RouteCreateRequest {
    @Schema(description = "Maintenance id", example = "1")
    private Long id;
}
