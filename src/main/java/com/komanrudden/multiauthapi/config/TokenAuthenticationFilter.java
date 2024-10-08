package com.komanrudden.multiauthapi.config;

import com.komanrudden.multiauthapi.security.CustomUserDetailsService;
import com.komanrudden.multiauthapi.security.TokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


/**
 * Filter class for handling JWT authentication in each request.
 * <p>
 * This class extends `OncePerRequestFilter` to ensure that the filter is executed once per request.
 * It uses `@Service` to indicate that it's a service component in the Spring context,
 * `@Slf4j` for logging, and `@RequiredArgsConstructor` for constructor injection.
 * </p>
 *
 * <p>Methods:</p>
 * <ul>
 *   <li><b>doFilterInternal</b>: Filters incoming requests to validate JWT tokens and set the authentication in the security context.</li>
 *   <li><b>getJWTFromRequest</b>: Extracts the JWT token from the request header.</li>
 * </ul>
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;
    private final CustomUserDetailsService userDetailsService;


    private String getJWTFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring("Bearer ".length());
        }

        return null;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = getJWTFromRequest(request);

            if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
                Long userId = tokenProvider.getUserIdFromToken(jwt);
                UserDetails userDetails = userDetailsService.loadUserById(userId);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception ex) {
            log.error("Could not set user authentication in security context.", ex);
        }

        filterChain.doFilter(request, response);
    }

}
