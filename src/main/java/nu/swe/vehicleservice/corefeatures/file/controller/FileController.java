package nu.swe.vehicleservice.corefeatures.file.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import nu.swe.vehicleservice.core.dto.response.ErrorResponse;
import nu.swe.vehicleservice.corefeatures.file.dto.response.FileDownloadResponse;
import nu.swe.vehicleservice.corefeatures.file.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller responsible for handling file related operations.
 * <p>This controller provides endpoints to download files based on their ID.</p>
 */
@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
@Tag(name = "Files API controller")
public class FileController {

    private final FileService fileService;

    /**
     * Endpoint to download a file based on its ID.
     *
     * @param id the unique identifier of the file to be downloaded
     * @return a ResponseEntity containing the file as a Resource, appropriate headers and a status code
     */
    @GetMapping("/{id}/download")
    @Operation(summary = "Downloads the specified file by ID. "
            + "Manager and Admin have access to all files. "
            + "Applicants have access only to their uploaded files.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(mediaType = "application/octet-stream", schema = @Schema())),
            @ApiResponse(responseCode = "401", description = "Authorization error",
                    content = @Content(mediaType = "application/json", schema = @Schema())),
            @ApiResponse(responseCode = "403",
                    description = "Forbidden, user does not have necessary permissions to access the file",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "File by ID not found",
                    content = @Content(mediaType = "application/json", schema = @Schema())),
            @ApiResponse(responseCode = "500", description = "Unknown error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<Resource> downloadFileById(@PathVariable("id") Long id) {
        FileDownloadResponse file = fileService.downloadFile(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, file.getContentDisposition())
                .header(HttpHeaders.CONTENT_TYPE, file.getContentType())
                .body(file.getResource());
    }

}
