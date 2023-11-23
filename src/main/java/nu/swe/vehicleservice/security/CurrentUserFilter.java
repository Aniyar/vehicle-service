package nu.swe.vehicleservice.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import nu.swe.vehicleservice.user.enums.UserRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.util.Objects;

/**
 * Filter for filling currentUser from JWT token.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class CurrentUserFilter extends GenericFilterBean {

    private final CurrentUser currentUser;
    private final ObjectMapper objectMapper;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterchain)
            throws IOException, ServletException {

        fillCurrentUser();
        filterchain.doFilter(request, response);
    }

    private void fillCurrentUser() {
        try {
            if (SecurityContextHolder.getContext().getAuthentication() instanceof JwtAuthenticationToken auth) {
                Jwt principal = (Jwt) auth.getPrincipal();
                CurrentUser from = objectMapper.convertValue(principal.getClaims(), CurrentUser.class);
                log.info("Current username = {}, uuid = {}", from.getUsername(), from.getId());
                BeanUtils.copyProperties(from, this.currentUser);

                if (auth.getAuthorities() != null) {
                    auth.getAuthorities()
                            .stream()
                            .map(authority -> EnumUtils.getEnum(UserRole.class, authority.getAuthority()))
                            .filter(Objects::nonNull)
                            .findFirst()
                            .ifPresent(this.currentUser::setRole);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}

