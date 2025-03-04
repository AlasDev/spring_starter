package it.objectmethod.spring_starter.authentication;

import it.objectmethod.spring_starter.exception.exceptions.UnauthorizedException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
@Order(1)
public class AccessFilter extends OncePerRequestFilter {
    private static final String AUTH_ENDPOINT = "/api/auth";

    private final JwtTokenProvider jwtTokenProvider;

    public AccessFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    /**
     * Is called by default each time a new request arrives.
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        /*
         * Used by front-ent to make sure that backend is working (operation of pre-flight)
         * A preflight request is an automatic browser initiated OPTIONS request that takes
         * occurs before certain cors-origin requests to ensure that backend/server is working,
         * so that server accepts the upcoming request method, header and credentials.
         */
        if (request.getMethod().equalsIgnoreCase("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        if (request.getMethod().equalsIgnoreCase("GET")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String jwtToken = request.getHeader("Authorization");
        String requestURI = request.getRequestURI() != null ? request.getRequestURI() : "";
        if (!requestURI.startsWith(AUTH_ENDPOINT)) {
            if (Objects.isNull(jwtToken) || !isAuthenticated(jwtToken)) {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private Boolean isAuthenticated(final String jwtToken) {
        if (!jwtTokenProvider.isValid(jwtToken) || jwtTokenProvider.isTokenExpired(jwtToken))
            throw new UnauthorizedException("User is not authenticated", HttpStatus.UNAUTHORIZED);
        return true;
    }
}