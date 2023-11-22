package nu.swe.vehicleservice.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import com.nimbusds.jose.jwk.RSAKey;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * Class for configuring application security.
 *
 * <p>What's going on here:
 * <ul>
 * <li>1) Disabling cors.
 * <li>2) Enabling oauth2ResourceServer.
 * <li>3) Disabling token verification for swagger endpoints.
 * <li>4) Configures a {@link JwtAuthenticationConverter} for extracting roles from JWT tokens
 * </ul>
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {

    @Bean
    SecurityFilterChain web(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/auth/**").permitAll()
                        .requestMatchers("/v3/**", "/swagger-ui/**").permitAll()
                        .anyRequest().authenticated());

        return http.build();
    }

    @Bean
    public JwtDecoder jwtDecoder() throws Exception {

        String publicKeyContent = new String(Files.readAllBytes(Paths.get(getClass().getClassLoader().getResource("rsa_keys/public_key.pem").toURI())))
                .replaceAll("\\s+", "");
        KeyFactory kf = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec keySpecX509 = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyContent));
        RSAPublicKey pubKey = (RSAPublicKey) kf.generatePublic(keySpecX509);
        RSAKey rsaKey = new RSAKey.Builder(pubKey).build();
        return NimbusJwtDecoder.withPublicKey(rsaKey.toRSAPublicKey()).build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter converter = new JwtGrantedAuthoritiesConverter();
        converter.setAuthorityPrefix(""); // If you don't use the ROLE_ prefix
        converter.setAuthoritiesClaimName("role"); // Name of the claim in JWT

        JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
        jwtConverter.setJwtGrantedAuthoritiesConverter(converter);
        return jwtConverter;
    }

}