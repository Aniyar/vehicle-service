package nu.swe.vehicleservice.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class is a DTO representation of get pageable users list search parameters.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserGetRequest {

    @Schema(description = "Search parameter for user role", example = "applicant")
    @NotNull
    private String role;

    @Schema(description = "Search text", example = "77029482790")
    private String search;
}

