package nu.swe.vehicleservice.corefeatures.file.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents a response DTO  for {@link FileLinkResponse}.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileLinkResponse {

    @Schema(description = "File link id", example = "1")
    private Long id;

    @Schema(description = "Original file name", example = "my cat.png")
    private String fileName;

}
