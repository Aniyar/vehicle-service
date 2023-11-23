package nu.swe.vehicleservice.vehicle.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import nu.swe.vehicleservice.vehicle.enums.VehicleFuelType;

@Data
public class VehicleCreateRequest {

    @NotBlank
    @Schema(description = "Vehicle's model", example = "Mersedes S class")
    private String model;

    @NotNull
    @Schema(description = "Vehicle's year", example = "2016")
    private Integer year;

    @NotBlank
    @Schema(description = "Vehicle's licence plate", example = "780ATA01")
    private String licencePlate;

    @NotNull
    @Schema(description = "Vehicle's capacity", example = "5")
    private Integer capacity;

    @NotBlank
    @Schema(description = "Vehicle's vin", example = "Idk what vin is but pls use it")
    private String vin;

    @Schema(description = "Vehicle's fuel type", example = "GAS", implementation = VehicleFuelType.class)
    @Enumerated(EnumType.STRING)
    private VehicleFuelType fuelType;
}
