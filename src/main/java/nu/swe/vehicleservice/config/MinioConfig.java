package nu.swe.vehicleservice.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for setting up the MinIO client.
 */
@Configuration
public class MinioConfig {

    @Value("${app-config.minio.url}")
    private String url;

    @Value("${app-config.minio.access-key}")
    private String accessKey;

    @Value("${app-config.minio.secret-key}")
    private String secretKey;

    /**
     * Creates and returns an instance of MinioClient.
     *
     * @return Configured instance of MinioClient.
     */
    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(url)
                .credentials(accessKey, secretKey)
                .build();
    }
}
