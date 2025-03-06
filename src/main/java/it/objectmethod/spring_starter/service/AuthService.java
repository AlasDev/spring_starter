package it.objectmethod.spring_starter.service;

import it.objectmethod.spring_starter.dto.UtenteDTO;
import it.objectmethod.spring_starter.exception.exceptions.EmailAlreadyRegisteredException;
import it.objectmethod.spring_starter.repository.UtenteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UtenteRepository utenteRepository;

    public AuthService(UtenteRepository utenteRepository) {
        this.utenteRepository = utenteRepository;
    }

    public boolean canLogin(UtenteDTO utenteDTO) {
        String email = utenteDTO.getEmail();
        String password = utenteDTO.getPassword();

        if (!utenteRepository.existsByEmailAndPassword(email, password)) {
            throw new EntityNotFoundException("Utente not found");
        }
        return true;
    }

    public boolean canRegister(UtenteDTO utenteDTO) {
        String email = utenteDTO.getEmail();

        if (utenteRepository.existsByEmail(email)) {
            throw new EmailAlreadyRegisteredException(utenteDTO.getEmail());
        }
        return true;
    }
}
