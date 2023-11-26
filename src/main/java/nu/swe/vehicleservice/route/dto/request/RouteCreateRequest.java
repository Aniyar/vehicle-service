package nu.swe.vehicleservice.route.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class RouteCreateRequest {

    @NotBlank
    @Schema(description = "Route start point", example = "Some start point, probably coordinates")
    private String startPoint;

    @NotBlank
    @Schema(description = "Route end point", example = "Some end point, probably coordinates")
    private String endPoint;
}
