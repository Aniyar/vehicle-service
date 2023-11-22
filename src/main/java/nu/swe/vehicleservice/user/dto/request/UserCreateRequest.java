package nu.swe.vehicleservice.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * This class is a DTO representation of user creation request.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserCreateRequest extends UserUpdateRequest {

    @NotEmpty
    @Schema(description = "User's password. Must be min 6 char long max 20, at least "
            + "1 upper case char, 1 lower case char, 1 special symbol.", example = "!QAZxsw2")
    private String password;

}
