package nu.swe.vehicleservice.corefeatures.file.service.impl;

import io.minio.*;
import nu.swe.vehicleservice.corefeatures.file.dto.response.FileDownloadResponse;
import nu.swe.vehicleservice.corefeatures.file.entity.FileLinkEntity;
import nu.swe.vehicleservice.corefeatures.file.exception.FileException;
import nu.swe.vehicleservice.corefeatures.file.repository.FileLinkRepository;
import nu.swe.vehicleservice.corefeatures.file.service.FileService;
import nu.swe.vehicleservice.security.CurrentUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.UUID;

import static nu.swe.vehicleservice.corefeatures.file.enums.FileErrorCode.*;

/**
 * This is an implementation of {@link FileService}.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileLinkRepository fileLinkRepository;
    private final MinioClient minioClient;
    private final CurrentUser currentUser;

    @Value("${minio.bucket}")
    private String minioBucket;

    @Override
    public FileDownloadResponse downloadFile(Long fileId) {
        log.info("Attempting to download file with ID: {}", fileId);

        FileLinkEntity fileLink = fileLinkRepository.findById(fileId)
                .orElseThrow(() -> new FileException(FILE_IS_NOT_FOUND));

        GetObjectArgs args = GetObjectArgs.builder()
                .bucket(fileLink.getBucketName())
                .object(fileLink.getStorageKey())
                .build();

        try {
            GetObjectResponse response = minioClient.getObject(args);
            ByteArrayResource resource = new ByteArrayResource(response.readAllBytes());
            log.info("Successfully downloaded file with ID: {}", fileId);
            String contentType = fileLink.getIsViewable() ? fileLink.getContentType()
                    : MediaType.APPLICATION_OCTET_STREAM_VALUE;

            String contentDisposition = fileLink.getIsViewable() ? "inline"
                    : "attachment; filename=" + fileLink.getFileName();
            return FileDownloadResponse.builder()
                    .fileName(fileLink.getFileName())
                    .resource(resource)
                    .contentDisposition(contentDisposition)
                    .contentType(contentType)
                    .build();
        } catch (Exception ex) {
            log.error("Error while downloading file with ID {}: {}", fileId, ex.getMessage(), ex);
            throw new FileException(FILE_DOWNLOAD_ERROR);
        }
    }

    @Transactional
    @Override
    public FileLinkEntity uploadFile(MultipartFile file) {
        return uploadFile(file, false);
    }


    /**
     * Main method for uploading files with all parameters.
     *
     * @param file MultipartFile to be uploaded.
     * @param isViewable Flag indicating whether the file should be displayed in browser/app
     *                   or downloaded as attachment.
     * @return FileLinkEntity.
     */
    @Transactional
    @Override
    public FileLinkEntity uploadFile(MultipartFile file, Boolean isViewable) {
        if (Objects.isNull(file) || file.getSize() == 0L) {
            log.warn("File is empty or null. Upload aborted.");
            throw new FileException(FILE_IS_EMPTY);
        }

        log.info("Attempting to upload a file with name: {}", file.getOriginalFilename());
        ObjectWriteResponse response;

        try {
            PutObjectArgs objectArgs = PutObjectArgs.builder()
                    .contentType(file.getContentType())
                    .bucket(minioBucket)
                    .object(UUID.randomUUID().toString())
                    .stream(file.getInputStream(), file.getSize(), -1)
                    .build();

            response = minioClient.putObject(objectArgs);
            log.info("Successfully uploaded file, object: {}, etag: {}", response.object(), response.etag());
        } catch (Exception e) {
            log.error("Error occurred while uploading file: {}", e.getMessage(), e);
            throw new FileException(FILE_UPLOAD_ERROR);
        }

        return createFileLink(file.getOriginalFilename(), response.bucket(), response.object(), isViewable);
    }

    private FileLinkEntity createFileLink(String fileName, String bucket, String storageKey, Boolean isViewable) {
        String contentType = getContentType(fileName);

        FileLinkEntity entity = FileLinkEntity.builder()
                .isViewable(isViewable && contentType != null)
                .contentType(contentType)
                .bucketName(bucket)
                .storageKey(storageKey)
                .fileName(fileName)
                .build();

        return fileLinkRepository.save(entity);
    }

    private String getContentType(String fileName) {
        Path path = new File(fileName).toPath();
        try {
            return Files.probeContentType(path);
        } catch (IOException ex) {
            log.error("Could not extract file's content type");
            return null;
        }
    }

    @Override
    public void deleteById(Long id) {
        FileLinkEntity fileLink = fileLinkRepository.findById(id)
                .orElse(null);

        if (fileLink == null) {
            log.warn("Attempt to delete a non-existent FileLink with ID '{}'", id);
            return;
        }

        try {
            minioClient.removeObject(RemoveObjectArgs.builder()
                    .bucket(fileLink.getBucketName())
                    .object(fileLink.getStorageKey())
                    .build());
            log.info("Successfully deleted the file '{}' from storage", fileLink.getStorageKey());
        } catch (Exception e) {
            log.error("Error while deleting file '{}'", fileLink.getStorageKey(), e);
            throw new FileException(FILE_DELETION_ERROR);
        }

        fileLinkRepository.deleteById(id);
        log.info("Deleted FileLink with ID '{}' from the database", id);
    }
}
