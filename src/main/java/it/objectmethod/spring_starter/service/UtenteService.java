package it.objectmethod.spring_starter.service;

import it.objectmethod.spring_starter.authentication.JwtTokenProvider;
import it.objectmethod.spring_starter.dto.PageDTO;
import it.objectmethod.spring_starter.dto.UtenteDTO;
import it.objectmethod.spring_starter.entity.Utente;
import it.objectmethod.spring_starter.filter.UtenteSearchParams;
import it.objectmethod.spring_starter.mapper.UtenteMapstructMapper;
import it.objectmethod.spring_starter.repository.UtenteRepository;
import it.objectmethod.spring_starter.util.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UtenteService {
    private final UtenteRepository utenteRepository;
    private final UtenteMapstructMapper utenteMapstructMapper;
    private final JwtTokenProvider jwtTokenProvider;

    public UtenteService(UtenteRepository utenteRepository, UtenteMapstructMapper utenteMapstructMapper, JwtTokenProvider jwtTokenProvider) {
        this.utenteRepository = utenteRepository;
        this.utenteMapstructMapper = utenteMapstructMapper;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public UtenteDTO getMyUtente( String token) {
        Long myId = jwtTokenProvider.extractIdFromClaims(token);
        if (!utenteRepository.existsById(myId)) {
            throw new NoSuchElementException("your Utente with id '" + myId + "' was not found");
        }
        return utenteMapstructMapper.mapToDto(utenteRepository.findById(myId).orElseGet(Utente::new));
    }

    public List<UtenteDTO> getAll() {
        return utenteMapstructMapper.mapToDtos(utenteRepository.findAll());
    }

    public UtenteDTO getUtente(Long id) {
        if (!utenteRepository.existsById(id)) {
            throw new NoSuchElementException("Utente with id '" + id + "' not found");
        }
        return utenteMapstructMapper.mapToDto(utenteRepository.findById(id).orElseGet(Utente::new));
    }

    public UtenteDTO updateUtente(@Validated UtenteDTO utenteDTO) {
        Long utenteId = utenteDTO.getId(); //Id
        List<Role> ruolo = utenteDTO.getRuoli();

        utenteMapstructMapper.mapToDto(utenteRepository.findById(utenteId).orElseThrow(
                () -> new NoSuchElementException("Utente with id '" + utenteId + "' not found")));

        Utente utente = utenteMapstructMapper.mapToEntity(utenteDTO);
        utenteRepository.save(utente);
        return utenteMapstructMapper.mapToDto(utente);
    }

    public void deleteUtente(Long id) {
        if (!utenteRepository.existsById(id)) {
            throw new NoSuchElementException("Utente with id " + id + " not found");
        }
        utenteRepository.deleteById(id);
    }

    public UtenteDTO save(@Validated UtenteDTO utenteDTO) {
        utenteDTO.setId(null);
        List<Role> role = utenteDTO.getRuoli();

        Utente utente = utenteMapstructMapper.mapToEntity(utenteDTO);
        return utenteMapstructMapper.mapToDto(utenteRepository.save(utente));
    }

    //PAGE
    public PageDTO<UtenteDTO> getPage(Pageable pageable) {
        Page<Utente> page = utenteRepository.findAll(pageable);
        return utenteMapstructMapper.mapToPageDTO(page);
    }

    //FILTER
    public PageDTO<UtenteDTO> searchUtenteBySpecification(UtenteSearchParams utenteSearchParams, Pageable pageable) {
        Page<Utente> utenteList = utenteRepository.findAll(utenteSearchParams.toSpecification(), pageable);
        return utenteMapstructMapper.mapToPageDTO(utenteList);
    }
}