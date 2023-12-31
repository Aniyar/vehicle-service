package nu.swe.vehicleservice.maintenance.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import nu.swe.vehicleservice.maintenance.enums.MaintenanceType;

import java.math.BigDecimal;


@Data
public class MainenanceCreateRequest {

    @NotNull
    @Schema(description = "Maintenance type", example = "REPAIR")
    private MaintenanceType maintenanceType;

    @NotBlank
    @Schema(description = "Maintenance desctiption", example = "repaired this and that")
    private String details;

    @NotNull
    @Schema(description = "Vehicle plate", example = "780ATA01")
    private String vehiclePlate;

    @NotNull
    @Schema(description = "Maintenance cost", example = "20.122")
    private BigDecimal cost;
}
