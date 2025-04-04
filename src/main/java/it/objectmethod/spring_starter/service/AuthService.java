package it.objectmethod.spring_starter.service;

import it.objectmethod.spring_starter.authentication.JwtTokenProvider;
import it.objectmethod.spring_starter.dto.UtenteDTO;
import it.objectmethod.spring_starter.exception.exceptions.EmailAlreadyRegisteredException;
import it.objectmethod.spring_starter.mapper.UtenteMapstructMapper;
import it.objectmethod.spring_starter.repository.UtenteRepository;
import it.objectmethod.spring_starter.util.Role;
import jakarta.persistence.EntityNotFoundException;
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
            throw new EntityNotFoundException("User not found!");
        }
        return true;
    }

    public boolean canRegister(@Validated UtenteDTO utenteDTO) {
        String email = utenteDTO.getEmail();

        if (utenteRepository.existsByEmail(email)) {
            throw new EmailAlreadyRegisteredException(utenteDTO.getEmail());
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
        }
        return Map.of("token", token);
    }

    /**
     * register POST request.
     *
     * @param utenteDTO email and password
     */
    public void register(@Validated UtenteDTO utenteDTO) {
        boolean isMailValid = canRegister(utenteDTO);
        if (isMailValid) {
            utenteDTO.setId(null);
            utenteDTO.setRuoli(List.of(Role.USER)); //default role.
            utenteService.save(utenteDTO);
        }
    }
}
