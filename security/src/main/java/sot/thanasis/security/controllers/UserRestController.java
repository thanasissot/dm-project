package sot.thanasis.security.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sot.thanasis.security.dto.LoginDto;
import sot.thanasis.security.dto.RegisterDto;
import sot.thanasis.security.dto.TokenDto;
import sot.thanasis.security.user.UserService;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserRestController {

    private final UserService iUserService;

    //RessourceEndPoint:http://localhost:8087/api/user/register
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDto registerDto) {
        return iUserService.register(registerDto);
    }

    //RessourceEndPoint:http://localhost:8087/api/user/authenticate
    @PostMapping("/authenticate")
    public TokenDto authenticate(@RequestBody LoginDto loginDto) {
        return iUserService.authenticate(loginDto);
    }

}