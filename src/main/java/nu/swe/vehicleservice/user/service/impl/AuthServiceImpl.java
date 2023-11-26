package nu.swe.vehicleservice.user.service.impl;

import com.auth0.jwt.JWT;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nu.swe.vehicleservice.user.dto.request.LoginRequest;
import nu.swe.vehicleservice.user.dto.response.AuthResponse;
import nu.swe.vehicleservice.user.entity.UserEntity;
import nu.swe.vehicleservice.user.enums.UserErrorCode;
import nu.swe.vehicleservice.user.exception.UserException;
import nu.swe.vehicleservice.user.repository.UserRepository;
import nu.swe.vehicleservice.user.service.AuthService;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.InputStream;
import com.auth0.jwt.algorithms.Algorithm;

import java.security.interfaces.RSAPrivateKey;
import java.security.KeyFactory;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthResponse login(LoginRequest request) {
        UserEntity user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UserException(UserErrorCode.USER_LOGIN_IS_INVALID));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new UserException(UserErrorCode.USER_PASSWORD_IS_INVALID);
        }
        try {
            String token = generateToken(user);
            return new AuthResponse(token);
        } catch (Exception exception) {
            return new AuthResponse("something wrong happened");
        }

    }

    private String generateToken(UserEntity user) throws Exception {
        final long EXPIRATION_TIME = 864_000_000; // 10 days
        InputStream is = getClass().getClassLoader().getResourceAsStream("rsa_keys/private_key.pem");
        if (is == null) {
            throw new FileNotFoundException("Cannot find 'public_key.pem'");
        }
        String privateKeyContent = new String(is.readAllBytes()).replaceAll("\\s+", "");

        KeyFactory kf = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec keySpecPKCS8 = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyContent));
        RSAPrivateKey privateKey = (RSAPrivateKey) kf.generatePrivate(keySpecPKCS8);

        return JWT.create()
                .withSubject(user.getId().toString())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .withClaim("preferred_username", user.getUsername())
                .withClaim("id", user.getId())
                .withClaim("name", user.getFirstName() + " " + user.getLastName())
                .withClaim("given_name", user.getFirstName())
                .withClaim("family_name", user.getLastName())
                .withClaim("email", user.getEmail())
                .withClaim("role", user.getRole().toString())
                .sign(Algorithm.RSA256(null, privateKey));
    }
}
