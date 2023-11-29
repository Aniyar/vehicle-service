package nu.swe.vehicleservice.filestorage.service;

/**
 * The FileStorage interface defines the contract for file storage operations.
 */
public interface FileStorage {

    /**
     * Uploads a file to the storage.
     *
     * @param content    The byte array representing the file content.
     * @param publicName The public name of the file.
     * @return The unique identifier of the uploaded file.
     */
    String uploadFile(byte[] content, String publicName);

    /**
     * Generates a temporary URL for accessing a stored file.
     *
     * @param fileId The unique identifier of the file.
     * @return A temporary URL for accessing the file.
     */
    String getTemporaryDownloadUrl(String fileId);
}
