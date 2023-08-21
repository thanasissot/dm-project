package sot.thanasis.security.user;

import org.springframework.http.ResponseEntity;
import sot.thanasis.security.dto.LoginDto;
import sot.thanasis.security.dto.RegisterDto;
import sot.thanasis.security.dto.TokenDto;
import sot.thanasis.security.role.Role;

public interface IUserService {
    //ResponseEntity<?> register (RegisterDto registerDto);
    //  ResponseEntity<BearerToken> authenticate(LoginDto loginDto);

    TokenDto authenticate(LoginDto loginDto);

    ResponseEntity<?> register(RegisterDto registerDto);

    Role saveRole(Role role);

    User saverUser(User user);
}