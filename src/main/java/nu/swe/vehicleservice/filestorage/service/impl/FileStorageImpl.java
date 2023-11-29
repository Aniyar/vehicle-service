package nu.swe.vehicleservice.filestorage.service.impl;

import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.http.Method;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import nu.swe.vehicleservice.filestorage.service.FileStorage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.UUID;

/**
 * Implementation of the FileStorage interface using Minio.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class FileStorageImpl implements FileStorage {

    private final MinioClient minioClient;

    @Value("${app-config.minio.bucket-name}")
    private String bucketName;

    @SneakyThrows
    @Override
    public String uploadFile(byte[] content, String publicName) {
        log.info("Starting file upload. Public name: {}", publicName);

        var inputStream = new ByteArrayInputStream(content);
        var fileId = UUID.randomUUID().toString();

        var request = PutObjectArgs.builder()
                .bucket(bucketName)
                .object(fileId)
                .stream(inputStream, content.length, -1)
                .contentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
                .headers(Map.of("Content-Disposition", "attachment; filename*=UTF-8''" +
                        URLEncoder.encode(publicName, StandardCharsets.UTF_8)))
                .build();

        minioClient.putObject(request);
        log.info("File uploaded successfully. File ID: {}", fileId);
        return fileId;
    }

    @SneakyThrows
    @Override
    public String getTemporaryDownloadUrl(String fileId) {
        log.info("Generating temporary access URL for file ID: {}", fileId);

        GetPresignedObjectUrlArgs args = GetPresignedObjectUrlArgs.builder()
                .method(Method.GET)
                .bucket(bucketName)
                .object(fileId)
                .expiry(600)
                .build();

        var presignedUrl = minioClient.getPresignedObjectUrl(args);
        log.info("Temporary access URL generated successfully for file ID: {}", fileId);

        var uri = URI.create(presignedUrl);
        return uri.getRawPath() + "?" + uri.getRawQuery();
    }
}
