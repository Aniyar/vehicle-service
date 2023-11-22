package nu.swe.vehicleservice.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for setting up the Minio client.
 *
 * <p>This class configures a {@link MinioClient} bean using properties 'minio.address', 'minio.login'
 * and 'minio.password' sourced from the application.yml or the environment.</p>
 *
 * @see MinioClient
 */
@Configuration
public class MinioConfig {

    @Value("${minio.address}")
    private String address;

    @Value("${minio.login}")
    private String login;

    @Value("${minio.password}")
    private String password;

    /**
     * Creates and returns a Minio(file storage) client instance.
     *
     * @return An instance of {@link MinioClient}
     */
    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .credentials(login, password)
                .endpoint(address)
                .build();
    }

}
