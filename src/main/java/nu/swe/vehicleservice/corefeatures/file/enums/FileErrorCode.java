package nu.swe.vehicleservice.corefeatures.file.enums;

import nu.swe.vehicleservice.core.enums.BaseErrorCode;
import nu.swe.vehicleservice.corefeatures.file.exception.FileException;
import lombok.Getter;

/**
 * This enum contains all {@link FileException} related error codes.
 * <p>These codes should be used as error references in 'errors' resource bundle.</p>
 */
@Getter
public enum FileErrorCode implements BaseErrorCode {

    FILE_IS_EMPTY(400),
    FILE_DOWNLOAD_ACCESS_DENIED(403),
    FILE_IS_NOT_FOUND(404),
    FILE_UPLOAD_ERROR(500),
    FILE_DOWNLOAD_ERROR(500),
    FILE_DELETION_ERROR(500),
    FILE_INVALID_EXTENSION(400);

    private final int status;

    FileErrorCode(int status) {
        this.status = status;
    }

    @Override
    public String getMessageKey() {
        return this.name();
    }
}
