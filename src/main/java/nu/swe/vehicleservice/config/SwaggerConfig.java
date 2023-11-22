package nu.swe.vehicleservice.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration file for swagger.
 * <p>
 * Here is the description of the current microservice and the configuration
 * of the Swagger UI to enable the JSON Web token (JWT) when calling our API.
 * </p>
 */
@Configuration
public class SwaggerConfig {

    @Value("${swagger.base-url}")
    private String baseUrl;

    @Bean
    OpenAPI openApi() {
        Server server = new Server();
        server.setUrl(baseUrl);

        return new OpenAPI()
                .addServersItem(server)
                .addSecurityItem(new SecurityRequirement()
                        .addList("Bearer Authentication"))
                .components(new Components().addSecuritySchemes(
                        "Bearer Authentication", createApiKeyScheme()))
                .info(new Info().title("Student service API")
                        .description("API to view and manage students")
                        .version("1.0"));
    }

    private SecurityScheme createApiKeyScheme() {
        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }
}
