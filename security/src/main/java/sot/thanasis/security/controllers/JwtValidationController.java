package sot.thanasis.security.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sot.thanasis.security.security.JwtUtilities;

@RestController
@RequiredArgsConstructor
@RequestMapping("/validate-jwt")
public class JwtValidationController {
    private final JwtUtilities jwtUtilities;

    @GetMapping("/validate")
    public ResponseEntity<String> validateJwt(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok("JWT is valid");
    }
}
