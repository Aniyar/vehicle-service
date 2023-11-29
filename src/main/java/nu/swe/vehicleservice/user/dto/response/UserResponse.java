package nu.swe.vehicleservice.user.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import nu.swe.vehicleservice.user.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class is a representation of system's user.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    @Schema(description = "User's unique identifier", example = "1")
    private Integer id;

    @Schema(description = "Username in system", example = "manager")
    private String username;

    @Schema(description = "User's role in system.", example = "manager", enumAsRef = true)
    private UserRole role;

    @Schema(description = "User's actual name", example = "John")
    private String firstName;

    @Schema(description = "User's actual family name", example = "Doe")
    private String lastName;

    @Schema(description = "User's email", example = "manager@arada.kz")
    private String email;

    @Schema(description = "User's phone", example = "+77771234567")
    private String phoneNumber;

    @Schema(description = "User's creation date")
    private String createdDate;

}
