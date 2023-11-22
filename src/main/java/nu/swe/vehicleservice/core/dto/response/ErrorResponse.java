package nu.swe.vehicleservice.core.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatusCode;

import java.io.Serializable;

/**
 * This class is a custom error response representation.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse implements Serializable {

    @Schema(description = "Localized error message", example = "Something went wrong")
    private String message;

    @Schema(description = "Error's http status code", example = "NOT_FOUND", hidden = true)
    private HttpStatusCode code;

}
