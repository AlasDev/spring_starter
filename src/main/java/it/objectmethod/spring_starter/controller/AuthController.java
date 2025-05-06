package it.objectmethod.spring_starter.controller;

import it.objectmethod.spring_starter.dto.UtenteDTO;
import it.objectmethod.spring_starter.service.AuthService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public Map<String, Object> login(@Validated @RequestBody UtenteDTO utenteDTO) {
        return authService.login(utenteDTO);
    }

    @PostMapping("/register")
    public void register(@RequestBody UtenteDTO utenteDTO) {
        authService.register(utenteDTO);
    }
}
