package com.komanrudden.multiauthapi.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * A simple CORS filter for handling Cross-Origin Resource Sharing (CORS) requests.
 * This filter sets the necessary headers to allow CORS requests from any origin.
 *
 * <p>It extends {@link OncePerRequestFilter} to ensure that the filter is executed once per request.</p>
 *
 * <p>The following headers are set:</p>
 * <ul>
 *   <li><b>Access-Control-Allow-Origin</b>: Allows requests from any origin.</li>
 *   <li><b>Access-Control-Allow-Credentials</b>: Allows credentials to be included in requests.</li>
 *   <li><b>Access-Control-Allow-Methods</b>: Specifies the allowed HTTP methods (POST, GET, PUT, OPTIONS, DELETE).</li>
 *   <li><b>Access-Control-Max-Age</b>: Specifies how long the results of a preflight request can be cached.</li>
 *   <li><b>Access-Control-Allow-Headers</b>: Specifies the allowed headers in the request.</li>
 * </ul>
 *
 * <p>This filter is ordered with the highest precedence to ensure it is applied before other filters.</p>
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SimpleCORSFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me, Authorization");

        filterChain.doFilter(request, response);
    }
}
