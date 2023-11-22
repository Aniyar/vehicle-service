package nu.swe.vehicleservice.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import nu.swe.vehicleservice.core.enums.UserRole;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

/**
 * Represents information about the current user.
 * For each http request, it is filled in the {@link CurrentUserFilter CurrentUserFilter.class} from JWT token.
 */
@Component
@RequestScope
@Data
public class CurrentUser {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("preferred_username")
    private String username;

    @JsonProperty("name")
    private String fullName;

    @JsonProperty("given_name")
    private String firstName;

    @JsonProperty("family_name")
    private String lastName;

    @JsonProperty("email")
    private String email;

    private UserRole role;
}
