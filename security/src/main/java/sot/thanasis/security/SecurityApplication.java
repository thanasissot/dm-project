package sot.thanasis.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SecurityApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SecurityApplication.class);
    }

//    @Bean
//    CommandLineRunner run (IUserService iUserService , IRoleRepository iRoleRepository , IUserRepository iUserRepository , PasswordEncoder passwordEncoder)
//    {return  args ->
//    {   iUserService.saveRole(new Role(RoleName.USER));
//        iUserService.saveRole(new Role(RoleName.ADMIN));
//        iUserService.saveRole(new Role(RoleName.SUPERADMIN));
//        iUserService.saverUser(new User("user@gmail.com", passwordEncoder.encode("userPassword"), new ArrayList<>()));
//        iUserService.saverUser(new User("admin@gmail.com", passwordEncoder.encode("adminPassword"), new ArrayList<>()));
//        iUserService.saverUser(new User("superadminadmin@gmail.com", passwordEncoder.encode("superadminPassword"), new ArrayList<>()));
//
//        Role role = iRoleRepository.findByRoleName(RoleName.USER);
//        User user = iUserRepository.findByEmail("user@gmail.com").orElse(null);
//        user.getRoles().add(role);
//        iUserService.saverUser(user);
//
//        Role role1 = iRoleRepository.findByRoleName(RoleName.ADMIN);
//        User user1 = iUserRepository.findByEmail("admin@gmail.com").orElse(null);
//        user1.getRoles().add(role1);
//        iUserService.saverUser(user1);
//
//        User userr = iUserRepository.findByEmail("superadminadmin@gmail.com").orElse(null);
//        Role rolee = iRoleRepository.findByRoleName(RoleName.SUPERADMIN);
//        userr.getRoles().add(rolee);
//        iUserService.saverUser(userr);
//    };}

}
