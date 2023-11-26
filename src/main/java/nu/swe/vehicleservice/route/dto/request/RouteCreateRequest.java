package nu.swe.vehicleservice.route.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class RouteCreateRequest {

    @NotBlank
    @Schema(description = "Route start point", example = "Some address")
    private String startPoint;

    @NotBlank
    @Schema(description = "Route start lat", example = "Start longitude")
    private String startLat;

    @NotBlank
    @Schema(description = "Route start lon", example = "Some longitude")
    private String startLon;

    @NotBlank
    @Schema(description = "Route end point", example = "Some address")
    private String endPoint;

    @NotBlank
    @Schema(description = "Route end lat", example = "Start longitude")
    private String endLat;

    @NotBlank
    @Schema(description = "Route end lon", example = "Some longitude")
    private String endLon;


}
