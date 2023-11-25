package nu.swe.vehicleservice.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class is a DTO representation of user login request.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    @NotEmpty
    @Schema(description = "User's username", example = "admin")
    private String username;

    @NotEmpty
    @Schema(description = "User's password", example = "!QAZxsw2")
    private String password;
}
