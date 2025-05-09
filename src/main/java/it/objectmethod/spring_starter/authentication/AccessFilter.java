package it.objectmethod.spring_starter.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import it.objectmethod.spring_starter.exception.ErrorBody;
import it.objectmethod.spring_starter.exception.exceptions.RoleNotAuthorizedException;
import it.objectmethod.spring_starter.exception.exceptions.UnauthorizedException;
import it.objectmethod.spring_starter.util.Role;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Component
@Order(1)
public class AccessFilter extends OncePerRequestFilter {
    private static final String AUTH_ENDPOINT = "/api/auth";
    public static final int API_LENGTH = "/api".length();
    private final JwtTokenProvider jwtTokenProvider;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public AccessFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    private boolean isAuthenticated(final String jwtToken, HttpServletResponse response) throws IOException {
        if (jwtTokenProvider.isTokenEmpty(jwtToken) || !jwtTokenProvider.isValid(jwtToken) || jwtTokenProvider.isTokenExpired(jwtToken)) {
            handleException(new UnauthorizedException("User is not authenticated", HttpStatus.UNAUTHORIZED), "User is not authenticated", HttpStatus.UNAUTHORIZED, response);
            return false;
        }
        return true;
    }

    private void handleException(Throwable e, String message, HttpStatus status, HttpServletResponse response) throws IOException {
        ErrorBody errorBody = new ErrorBody(message, status, List.of(e.getLocalizedMessage()));
        response.setStatus(status.value());
        response.getWriter().write(objectMapper.writeValueAsString(errorBody));
        response.getWriter().flush();
        response.getWriter().close();
    }

    /**
     * Is called by default each time a new request arrives.
     *
     * @param request     Request received from the user
     * @param response    Response returned to the user
     * @param filterChain Object provided by the servlet container to the developer giving a view into the invocation chain of a filtered request for a resource. Filters use the FilterChain to invoke the next filter in the chain,
     * @throws ServletException Possible exception
     * @throws IOException      Possible exception
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String url = request.getRequestURI();
        String method = request.getMethod();

        if (!url.contains("/api")) return; //if url doesn't have '/api' in it, it will be ignored

        /*
         * Used by front-ent to make sure that backend is working (operation of pre-flight)
         * A preflight request is an automatic browser initiated OPTIONS request that takes
         * occurs before certain cors-origin requests to ensure that backend/server is working,
         * so that server accepts the upcoming request method, header and credentials.
         */
        if (method.equalsIgnoreCase("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        //don't ask for a token to login or register
        if (method.equalsIgnoreCase("POST") && url.startsWith(AUTH_ENDPOINT) &&
                url.endsWith("/login") ||
                url.endsWith("/register")) {
            filterChain.doFilter(request, response);
            return;
        }
        //don't ask for a token when using GET on tables that are not Utente
        if (method.equalsIgnoreCase("GET") && !url.startsWith("/utente", API_LENGTH)){
            filterChain.doFilter(request, response);
            return;
        }

        try {
            final String token = request.getHeader("Authorization");
            if (!url.startsWith(AUTH_ENDPOINT)) {
                if (Objects.isNull(token) || !isAuthenticated(token, response)) {
                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
                    return;
                }
            }
        } catch (ExpiredJwtException e) {
            handleException(e, "Token expired", HttpStatus.UNAUTHORIZED, response);
            return;
        } catch (SignatureException e) {
            handleException(e, "Invalid token signature", HttpStatus.UNAUTHORIZED, response);
            return;
        } catch (MalformedJwtException e) {
            handleException(e, "Malformed token", HttpStatus.BAD_REQUEST, response);
            return;
        } catch (IllegalArgumentException e) {
            handleException(e, "Invalid argument", HttpStatus.BAD_REQUEST, response);
            return;
        } catch (UnauthorizedException e) {
            handleException(e, e.getMessage(), HttpStatus.UNAUTHORIZED, response);
            return;
        }

        final String token = request.getHeader("Authorization");
        final List<Role> roleEnums = jwtTokenProvider.extractRuoloFromClaims(token);
        final Long myId = jwtTokenProvider.extractIdFromClaims(token);

        if (roleEnums.contains(Role.ADMIN)){
            filterChain.doFilter(request, response);
            return;
        }
        if (roleEnums.contains(Role.USER) && method.equalsIgnoreCase("GET") && url.startsWith("/utente/get/myself/" + myId, API_LENGTH)) {
            filterChain.doFilter(request, response);
            return;
        }
        if (roleEnums.contains(Role.MODIFY_AUTISTA) && url.startsWith("/autista", API_LENGTH)) {
            filterChain.doFilter(request, response);
            return;
        }
        if (roleEnums.contains(Role.MODIFY_CLIENTE) && url.startsWith("/cliente", API_LENGTH)) {
            filterChain.doFilter(request, response);
            return;
        }
        if (roleEnums.contains(Role.MODIFY_CORSA) && url.startsWith("/corsa", API_LENGTH)) {
            filterChain.doFilter(request, response);
            return;
        }
        if (roleEnums.contains(Role.MODIFY_UTENTE) && url.startsWith("/utente", API_LENGTH)) {
            filterChain.doFilter(request, response);
            return;
        }
        if (roleEnums.contains(Role.MODIFY_VEICOLO) && url.startsWith("/veicolo", API_LENGTH)) {
            filterChain.doFilter(request, response);
            return;
        }
        handleException(
                new RoleNotAuthorizedException(roleEnums, method),
                "Role'" + roleEnums + "' can't use method '" + method + "'.",
                HttpStatus.FORBIDDEN,
                response);
    }
}