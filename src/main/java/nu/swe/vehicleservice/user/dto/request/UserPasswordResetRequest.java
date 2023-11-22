package nu.swe.vehicleservice.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class is a request body for resetting password.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPasswordResetRequest {

    @NotEmpty
    @Schema(description = "Password to change to", example = "P@ssw0rd", implementation = String.class)
    private String newPassword;

    @NotEmpty
    @Schema(description = "Confirmation of a new password.", example = "P@ssw0rd", implementation = String.class)
    private String confirmPassword;

}
