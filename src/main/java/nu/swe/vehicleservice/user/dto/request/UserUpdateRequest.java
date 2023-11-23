package nu.swe.vehicleservice.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import nu.swe.vehicleservice.user.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents User's DTO for update action.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequest {

    @NotBlank
    @Schema(description = "User's identifier in system", example = "manager")
    private String username;

    @NotNull
    @Schema(description = "User's role in system. ('administrator', 'manager')", example = "manager", enumAsRef = true)
    private UserRole role;

    @NotBlank
    @Schema(description = "User's actual name", example = "John")
    private String firstName;

    @NotBlank
    @Schema(description = "User's actual family name", example = "Doe")
    private String lastName;

    @Email
    @NotBlank
    @Schema(description = "User's email", example = "manager@arada.kz")
    private String email;

    @NotBlank
    @Schema(description = "User's phone", example = "+77771234567")
    private String phoneNumber;

    @Schema(description = "Is user active", example = "true")
    private boolean enabled;

}
