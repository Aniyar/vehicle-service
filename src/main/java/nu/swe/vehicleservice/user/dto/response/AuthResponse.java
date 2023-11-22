package nu.swe.vehicleservice.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Auth response DTO.
 */
@Data
@AllArgsConstructor
public class AuthResponse {

    private String accessToken;
}
