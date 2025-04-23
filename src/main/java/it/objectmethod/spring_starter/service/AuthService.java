package it.objectmethod.spring_starter.service;

import it.objectmethod.spring_starter.authentication.JwtTokenProvider;
import it.objectmethod.spring_starter.dto.UtenteDTO;
import it.objectmethod.spring_starter.mapper.UtenteMapstructMapper;
import it.objectmethod.spring_starter.repository.UtenteRepository;
import it.objectmethod.spring_starter.util.Role;
import jakarta.validation.constraints.Email;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Map;

@Service
public class AuthService {
    private final UtenteRepository utenteRepository;
    private final UtenteMapstructMapper utenteMapstructMapper;
    private final JwtTokenProvider jwtTokenProvider;
    private final UtenteService utenteService;

    public AuthService(UtenteRepository utenteRepository, UtenteMapstructMapper utenteMapstructMapper, JwtTokenProvider jwtTokenProvider, UtenteService utenteService) {
        this.utenteRepository = utenteRepository;
        this.utenteMapstructMapper = utenteMapstructMapper;
        this.jwtTokenProvider = jwtTokenProvider;
        this.utenteService = utenteService;
    }

    public boolean canLogin(@Validated UtenteDTO utenteDTO) {
        String email = utenteDTO.getEmail();
        String password = utenteDTO.getPassword();

        if (!utenteRepository.existsByEmailAndPassword(email, password)) {
            return false;
        }
        return true;
    }

    /**
     * @param email email
     * @return true if email has not yet been used, false otherwise
     */
    public boolean canRegister(String email) {
        if (utenteRepository.existsByEmail(email)) {
            return false;
        }
        return true;
    }

    /**
     * login POST request.
     *
     * @param utenteDTO credentials
     * @return a new token inside a json
     */
    public Map<String, String> login(@Validated UtenteDTO utenteDTO) {
        String token = "";
        boolean isFound = canLogin(utenteDTO);
        UtenteDTO user = utenteMapstructMapper.mapToDto(utenteRepository.findByEmail(utenteDTO.getEmail()));
        if (isFound) {
            token = jwtTokenProvider.generateToken(user);
            return Map.of("token", token);
        }
        return Map.of("result", "User with that email and/or password was not found");
    }

    /**
     * register POST request.
     *
     * @param email email
     * @param password password
     */
    public void register(@Email String email, String password) {
        boolean isMailValid = canRegister(email);
        if (isMailValid) {
            UtenteDTO user = new UtenteDTO();
            user.setId(null);
            user.setEmail(email);
            user.setPassword(password);
            user.setRuoli(List.of(Role.USER)); //default role.
            utenteService.save(user);
        }
    }
}
