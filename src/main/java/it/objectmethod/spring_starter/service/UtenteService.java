package it.objectmethod.spring_starter.service;

import it.objectmethod.spring_starter.dto.PageDTO;
import it.objectmethod.spring_starter.dto.UtenteDTO;
import it.objectmethod.spring_starter.entity.Utente;
import it.objectmethod.spring_starter.exception.exceptions.RequiredValueException;
import it.objectmethod.spring_starter.filter.UtenteSearchParams;
import it.objectmethod.spring_starter.mapper.UtenteMapstructMapper;
import it.objectmethod.spring_starter.repository.UtenteRepository;
import it.objectmethod.spring_starter.util.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UtenteService {
    private final UtenteRepository utenteRepository;
    private final UtenteMapstructMapper utenteMapstructMapper;

    public UtenteService(UtenteRepository utenteRepository, UtenteMapstructMapper utenteMapstructMapper) {
        this.utenteRepository = utenteRepository;
        this.utenteMapstructMapper = utenteMapstructMapper;
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

    public UtenteDTO updateUtente(UtenteDTO utenteDTO) {
        Long utenteId = utenteDTO.getId(); //Id
        Role ruolo = utenteDTO.getRuolo();

        if (utenteId == null) {
            throw new RequiredValueException("Id");
        }
        if (ruolo == null) {
            throw new RequiredValueException("Ruolo");
        }

        utenteMapstructMapper.mapToDto(utenteRepository.findById(utenteId).orElseThrow(
                () -> new NoSuchElementException("Utente with id '" + utenteId + "' not found")));

        Utente utente = utenteMapstructMapper.mapToEntity(utenteDTO);
        utente.setRuolo(ruolo);
        utenteRepository.save(utente);
        return utenteMapstructMapper.mapToDto(utente);
    }

    public void deleteUtente(Long id) {
        if (!utenteRepository.existsById(id)) {
            throw new NoSuchElementException("Utente with id " + id + " not found");
        }
        utenteRepository.deleteById(id);
    }

    public UtenteDTO save(UtenteDTO utenteDTO) {
        utenteDTO.setId(null);
        Role role = utenteDTO.getRuolo();

        if (role == null) {
            throw new RequiredValueException("Ruolo");
        }

        Utente utente = utenteMapstructMapper.mapToEntity(utenteDTO);
        utente.setRuolo(role);
        utenteRepository.save(utente);
        return utenteMapstructMapper.mapToDto(utente);
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