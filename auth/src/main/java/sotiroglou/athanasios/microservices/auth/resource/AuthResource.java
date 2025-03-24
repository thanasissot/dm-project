package sotiroglou.athanasios.microservices.auth.resource;

import io.smallrye.jwt.build.Jwt;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.eclipse.microprofile.jwt.Claims;
import sotiroglou.athanasios.microservices.auth.dom.AuthUser;
import sotiroglou.athanasios.microservices.auth.repo.UserRepository;

import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Path("/auth")
public class AuthResource {

    @Inject
    UserRepository userRepository;

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response signIn(UserCredentials credentials) {
        AuthUser authUser = userRepository.findByUsername(credentials.getUsername());
        if (authUser == null || !authUser.getPassword().equals(credentials.getPassword())) { // Use hashed password comparison
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        String roles = authUser.getAuthRoles().stream()
                .map(role -> role.getRoleName().name())
                .collect(Collectors.joining(","));
        String token = Jwt.issuer("https://example.com")
                .expiresIn(Duration.ofSeconds(60))
                .upn(authUser.getUsername())
                .groups(new HashSet<>(List.of(roles.split(","))))
                .claim(Claims.birthdate.name(), "2000-01-01")
                .sign();

        return Response.ok(new TokenResponse(token, authUser.getAuthRoles().stream()
                .map(role -> role.getRoleName().name())
                .collect(Collectors.toSet()))).build();
    }


    @Data
    public static class UserCredentials {
        private String username;
        private String password;
    }

    @Data
    @AllArgsConstructor
    public static class TokenResponse {
        private String token;
        private Set<String> roles;

        public TokenResponse(String token) {
            this.token = token;
        }
    }
}
