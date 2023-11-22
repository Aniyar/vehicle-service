package nu.swe.vehicleservice.corefeatures.file.service;

import nu.swe.vehicleservice.corefeatures.file.dto.response.FileDownloadResponse;
import nu.swe.vehicleservice.corefeatures.file.entity.FileLinkEntity;
import nu.swe.vehicleservice.corefeatures.file.exception.FileException;
import org.springframework.web.multipart.MultipartFile;

/**
 * This interface is containing methods for managing files.
 */
public interface FileService {

    /**
     * Downloads the file associated with the provided file ID.
     *
     * @param fileId The unique identifier of the file to be downloaded.
     * @return A {@link FileDownloadResponse} containing the file's name and its content.
     * @throws FileException If the file is not found or any other error occurs during the download process.
     */
    FileDownloadResponse downloadFile(Long fileId);

    /**
     * Uploads the provided file.
     *
     * @param file The file to be uploaded, represented as a {@link MultipartFile}.
     * @return A {@link FileLinkEntity}
     * @throws FileException If the file is empty or any other error occurs during the upload process.
     */
    FileLinkEntity uploadFile(MultipartFile file);

    /**
     * Uploads the provided file with isViewable parameter.
     *
     * @param file The file to be uploaded, represented as a {@link MultipartFile}.
     * @param isViewable Flag. If true, file should be displayed inline, otherwise downloaded as attachment.
     * @return A {@link FileLinkEntity}
     * @throws FileException If the file is empty or any other error occurs during the upload process.
     */
    FileLinkEntity uploadFile(MultipartFile file, Boolean isViewable);

    /**
     * Deletes a {@link FileLinkEntity} by its ID and removes the associated object from storage.
     *
     * @param id The ID of the {@link FileLinkEntity} to be deleted.
     * @throws FileException If there's an error while deleting the file from storage.
     */
    void deleteById(Long id);
}
