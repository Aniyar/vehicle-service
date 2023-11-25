package nu.swe.vehicleservice.fuel.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import nu.swe.vehicleservice.fuel.enums.FuelType;

import java.math.BigDecimal;


@Data
public class FuelCreateRequest {

    @NotNull
    @Schema(description = "Fuel type", example = "GAS")
    private FuelType fuelType;

    @NotNull
    @Schema(description = "Vehicle id", example = "1")
    private Long vehicleId;

    @NotNull
    @Schema(description = "Price per liters", example = "213.72")
    private BigDecimal pricePerLiter;

    @NotNull
    @Schema(description = "Number of liters", example = "13")
    private Integer numberLiters;
}
