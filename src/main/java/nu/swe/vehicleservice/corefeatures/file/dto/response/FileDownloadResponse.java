package nu.swe.vehicleservice.corefeatures.file.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.io.Resource;

/**
 * Data class representing the response payload for a file download.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileDownloadResponse {

    private String fileName;
    private Resource resource;
    private String contentDisposition;
    private String contentType;
}
