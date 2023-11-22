package nu.swe.vehicleservice.corefeatures.file.exception;

import nu.swe.vehicleservice.core.exception.LocalizedException;
import nu.swe.vehicleservice.corefeatures.file.enums.FileErrorCode;

/**
 * This is implementation of {@link LocalizedException} for Minio related exceptions.
 */
public class FileException extends LocalizedException {

    /**
     * Constructor for error without parameters.
     *
     * @param error {@link FileErrorCode}
     */
    public FileException(FileErrorCode error) {
        super(error);
    }
}
