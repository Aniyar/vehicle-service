package nu.swe.vehicleservice.user.service;

import nu.swe.vehicleservice.user.dto.request.LoginRequest;
import nu.swe.vehicleservice.user.dto.response.AuthResponse;

/**
 * This interface is containing methods for authenticating users.
 */
public interface AuthService {

    /**
     * Login method.
     *
     * @param request Login request DTO.
     * @return ResponseEntity with token
     */
    AuthResponse login(LoginRequest request);
}
