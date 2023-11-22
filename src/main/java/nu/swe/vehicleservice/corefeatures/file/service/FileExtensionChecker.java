package nu.swe.vehicleservice.corefeatures.file.service;

import nu.swe.vehicleservice.corefeatures.file.enums.FileType;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

/**
 * Utility class to validate the file extension of uploaded documents and images.
 *
 * <p>This class contains a set of allowed file extensions and provides a method
 * to validate if a given filename matches any of the allowed extensions.</p>
 *
 * <p>Supported file extensions for documents include common document and image formats like
 * PNG, PDF, JPEG, and DOCX, among others.</p>
 *
 * <p>Supported file extensions for images include common image formats like
 *  * PNG, HEIC, JPEG, and WEBP, among others.</p>
 */
@Slf4j
public class FileExtensionChecker {

    private static final Set<String> ALLOWED_EXTENSIONS =
            Set.of("png", "pdf", "jpg", "jpeg", "gif", "tiff", "bmp", "doc", "docx", "xls", "xlsx");

    private static final Set<String> ALLOWED_EXTENSIONS_IMAGE =
            Set.of("png", "jpg", "jpeg", "heic", "bmp", "webp", "tiff");


    /**
     * Checks if the given filename has a valid and allowed file extension.
     *
     * <p>If the filename is null, empty, or does not contain a period,
     * the method returns false. For valid filenames, the method checks
     * the file extension against the set of allowed extensions.</p>
     *
     * <h3>Usage Examples:</h3>
     * <pre>
     * {@code
     * FileExtensionChecker.isValidExtension("document.pdf");   // true
     * FileExtensionChecker.isValidExtension("image.jPeg");     // true
     * FileExtensionChecker.isValidExtension("file.unknown");   // false
     * FileExtensionChecker.isValidExtension(" ");              // false
     * FileExtensionChecker.isValidExtension(null);             // false
     * }
     * </pre>
     *
     * @param fileName The name of the file to check.
     * @param fileType Type of the file. Document or Image.
     * @return true if the filename has an allowed extension; false otherwise.
     */
    public static boolean isValidExtension(String fileName, FileType fileType) {
        if (fileName == null || fileName.trim().isEmpty() || !fileName.contains(".")) {
            log.warn("Invalid file name: " + fileName);
            return false;
        }

        String fileExtension = fileName.trim().substring(fileName.lastIndexOf('.') + 1).toLowerCase();

        boolean isValid = false;
        switch (fileType) {
            case IMAGE -> isValid = ALLOWED_EXTENSIONS_IMAGE.contains(fileExtension);
            case DOCUMENT -> isValid = ALLOWED_EXTENSIONS.contains(fileExtension);
            default -> { }
        }

        if (isValid) {
            log.info("Valid file extension: " + fileExtension);
            return true;
        } else {
            log.warn("Unsupported file extension: " + fileExtension);
            return false;
        }
    }
}
