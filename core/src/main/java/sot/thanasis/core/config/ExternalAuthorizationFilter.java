package sot.thanasis.core.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class ExternalAuthorizationFilter extends OncePerRequestFilter {
    private static final String AUTH_URL = "http://localhost:8081/api/validate-jwt/validate";
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        try {
            if (isValidExternalAuthToken(request)) {
                CustomUserDetails userDetails = createCustomUserDetailsFromToken();
                Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        filterChain.doFilter(request, response);
    }

    private boolean isValidExternalAuthToken(HttpServletRequest request) throws IOException, InterruptedException {
        // Implement your logic to validate the external auth token
        // Return true if the token is valid; otherwise, return false
        // Create an instance of HttpClient
        HttpClient httpClient = HttpClient.newHttpClient();
        httpClient.followRedirects();

        // Create a URI for the target URL
        URI uri = URI.create(AUTH_URL);

        // Create an HttpRequest with custom headers
        HttpRequest.Builder
                request1 = HttpRequest.newBuilder(uri)
                .GET();

        for (Map.Entry<String, String> entry : extractHeaders(request).entrySet()) {
            request1.setHeader(entry.getKey(), entry.getValue());
        }

        // Send the request and receive the response

        HttpResponse<String> response = httpClient.send(request1.build(), HttpResponse.BodyHandlers.ofString());

        return response.statusCode() == 200;
    }

    private CustomUserDetails createCustomUserDetailsFromToken() {
        // Implement your logic to create a CustomUserDetails object
        // from the external auth token
        // For this example, we're creating a dummy user with a single role
        return new CustomUserDetails("username", "password", Collections.singleton(() -> "USER"));
    }

    private Map<String, String> extractHeaders(HttpServletRequest request) {
        Map<String, String> headersMap = new HashMap<>();

        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            if (headerName.equals("host") || headerName.equals("connection")) {
                continue;
            }
            headersMap.put(headerName, headerValue);

        }

        return headersMap;
    }

}
