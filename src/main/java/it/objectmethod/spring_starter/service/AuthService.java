package it.objectmethod.spring_starter.service;

import it.objectmethod.spring_starter.authentication.JwtTokenProvider;
import it.objectmethod.spring_starter.dto.UtenteDTO;
import it.objectmethod.spring_starter.exception.exceptions.EmailAlreadyRegisteredException;
import it.objectmethod.spring_starter.mapper.UtenteMapstructMapper;
import it.objectmethod.spring_starter.repository.UtenteRepository;
import it.objectmethod.spring_starter.util.Role;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

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
    public Map<String, Object> login(@Validated UtenteDTO utenteDTO) {
        String token = "";
        boolean isFound = canLogin(utenteDTO);
        if (isFound) {
            UtenteDTO user = utenteMapstructMapper.mapToDto(utenteRepository.findByEmail(utenteDTO.getEmail()));
            token = jwtTokenProvider.generateToken(user);
            return Map.of("token", token, "roles", user.getRuoli());
        } else {
            System.out.println("Could not login");
            throw new NoSuchElementException("Could not login");
        }
    }

    /**
     * register POST request.
     *
     * @param utenteDTO email and password (the rest is empty)
     */
    public Map<String, String> register(UtenteDTO utenteDTO) {
        String email = utenteDTO.getEmail();
        String password = utenteDTO.getPassword();
        boolean isMailValid = canRegister(email);
        if (isMailValid) {
            utenteDTO.setId(null);
            utenteDTO.setEmail(email);
            utenteDTO.setPassword(password);
            utenteDTO.setRuoli(List.of(Role.USER)); //default role.
            utenteService.save(utenteDTO);
            return Map.of("message", "user registered successfully");
        } else {
            System.out.println("Mail already in use");
            throw new EmailAlreadyRegisteredException("Mail already in use");
        }
    }
}
