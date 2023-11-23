package nu.swe.vehicleservice.driver.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class DriverCreateRequest {

    @NotBlank
    @Schema(description = "Driver's address", example = "Turan 37")
    private String address;

    @NotBlank
    @Schema(description = "Driver's licence number", example = "202137464736")
    private String licenseNumber;
}
