package it.objectmethod.spring_starter.controller;

import it.objectmethod.spring_starter.authentication.JwtTokenProvider;
import it.objectmethod.spring_starter.dto.UtenteDTO;
import it.objectmethod.spring_starter.service.AuthService;
import it.objectmethod.spring_starter.service.UtenteService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UtenteService utenteService;
    private final AuthService authService;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(AuthService authService, JwtTokenProvider jwtTokenProvider, UtenteService utenteService) {
        this.utenteService = utenteService;
        this.authService = authService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    /**
     * Post per effettuare il login e generare il token.
     *
     * @param utenteDTO credenziali d'accesso
     * @return the token
     */
    @PostMapping("/login")
    public Map<String, String> login(@Validated @RequestBody UtenteDTO utenteDTO) {
        String token = "";
        boolean isFound = authService.canLogin(utenteDTO);
        if (isFound) token = jwtTokenProvider.generateToken(utenteDTO);
        return Map.of("token", token);
    }

    /**
     * Post per effettuare la registrazione e verificare se la mail è già registrata.
     *
     * @param utenteDTO credenziali d'accesso
     */
    @PostMapping("/register")
    public void register(@Validated @RequestBody UtenteDTO utenteDTO) {
        boolean isMailValid = authService.canRegister(utenteDTO);
        if (isMailValid) {
            utenteDTO.setRuolo(2L); //tutti gli account appena creati avranno un ruolo predefinito, per cambiare ruolo bisogna fare una modifica in un secondo momento.
            utenteService.save(utenteDTO);
        }
    }
}
