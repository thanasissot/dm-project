package sot.thanasis.core;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v2")
public class ApiController {

    @GetMapping("/public/hello")
    public String publicHello() {
        return "Hello from public endpoint!";
    }

    @GetMapping("/secure/hello")
    public String secureHello() {
        return "Hello from secure endpoint!";
    }
}
