package nu.swe.vehicleservice.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import nu.swe.vehicleservice.core.dto.response.ErrorResponse;
import nu.swe.vehicleservice.user.dto.request.LoginRequest;
import nu.swe.vehicleservice.user.dto.response.AuthResponse;
import nu.swe.vehicleservice.user.service.AuthService;
import org.springframework.web.bind.annotation.*;

/**
 * This class is a controller for authentication.
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication API controller", description = "Authentication")
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Log in", description = "Log in to user user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Unknown error",
                    content = @Content(schema = @Schema()))})
    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
