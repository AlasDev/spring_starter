package it.objectmethod.spring_starter.service;

import it.objectmethod.spring_starter.dto.AutistaDTO;
import it.objectmethod.spring_starter.dto.PageDTO;
import it.objectmethod.spring_starter.entity.Autista;
import it.objectmethod.spring_starter.entity.Corsa;
import it.objectmethod.spring_starter.entity.Utente;
import it.objectmethod.spring_starter.entity.Veicolo;
import it.objectmethod.spring_starter.filter.AutistaSearchParams;
import it.objectmethod.spring_starter.mapper.AutistaMapstructMapper;
import it.objectmethod.spring_starter.repository.AutistaRepository;
import it.objectmethod.spring_starter.repository.CorsaRepository;
import it.objectmethod.spring_starter.repository.UtenteRepository;
import it.objectmethod.spring_starter.repository.VeicoloRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AutistaService {
    private final AutistaRepository autistaRepository;
    private final AutistaMapstructMapper autistaMapstructMapper;

    private final UtenteRepository utenteRepository;
    private final VeicoloRepository veicoloRepository;
    private final CorsaRepository corsaRepository;

    public AutistaService(AutistaRepository autistaRepository, AutistaMapstructMapper autistaMapstructMapper, UtenteRepository utenteRepository, VeicoloRepository veicoloRepository, CorsaRepository corsaRepository) {
        this.autistaRepository = autistaRepository;
        this.autistaMapstructMapper = autistaMapstructMapper;
        this.utenteRepository = utenteRepository;
        this.veicoloRepository = veicoloRepository;
        this.corsaRepository = corsaRepository;
    }

    public List<AutistaDTO> getAll() {
        return autistaMapstructMapper.mapToDtos(autistaRepository.findAll());
    }

    public AutistaDTO getAutista(Long id) {
        if (!autistaRepository.existsById(id)) {
            throw new NoSuchElementException("Autista with id '" + id + "' not found");
        }
        return autistaMapstructMapper.mapToDto(autistaRepository.findById(id).orElseGet(Autista::new));
    }

    public AutistaDTO updateAutista(@Validated AutistaDTO autistaDTO) {

        Long autistaId = autistaDTO.getId(); //Id
        Long veicoloId = autistaDTO.getVeicolo(); //Veicolo
        Long utenteId = autistaDTO.getUtente(); //Utente

        autistaRepository.findById(autistaId).orElseThrow(
                () -> new NoSuchElementException("Autista with id '" + autistaId + "' not found"));

        Veicolo veicolo = veicoloRepository.findById(veicoloId).orElseThrow(
                () -> new NoSuchElementException("Veicolo with id '" + veicoloId + "' not found"));

        Utente utente = utenteRepository.findById(utenteId).orElseThrow(
                () -> new NoSuchElementException("Utente with id '" + utenteId + "' not found"));

        List<Corsa> corse = new ArrayList<>();
        autistaDTO.getCorse().forEach(
                corsaId -> corse.add(corsaRepository.findById(corsaId).orElseThrow(
                        () -> new NoSuchElementException("Corsa with id '" + corsaId + "' not found")))
        );

        Autista autista = autistaMapstructMapper.mapToEntity(autistaDTO);
        autista.setVeicolo(veicolo);
        autista.setUtente(utente);
        autista.setCorse(corse);
        Autista autistaUpdated = autistaRepository.save(autista);
        return autistaMapstructMapper.mapToDto(autistaUpdated);
    }

    public void deleteAutista(Long id) {
        if (!autistaRepository.existsById(id)) {
            throw new NoSuchElementException("Autista with id: " + id + " not found");
        }
        autistaRepository.deleteById(id);
    }

    public AutistaDTO save(@Validated AutistaDTO autistaDTO) {
        autistaDTO.setId(null); //Autista id must not be there, that's why we will ignore it if it gets inputted anyway.

        Long veicoloId = autistaDTO.getVeicolo();
        Long utenteId = autistaDTO.getUtente();

        Veicolo veicolo = veicoloRepository.findById(veicoloId).orElseThrow(
                () -> new NoSuchElementException("Veicolo with id '" + veicoloId + "' not found"));
        Utente utente = utenteRepository.findById(utenteId).orElseThrow(
                () -> new NoSuchElementException("Utente with id '" + utenteId + "' not found"));

        Autista autista = autistaMapstructMapper.mapToEntity(autistaDTO);
        autista.setVeicolo(veicolo);
        autista.setUtente(utente);
        Autista autistaSaved = autistaRepository.save(autista);
        return autistaMapstructMapper.mapToDto(autistaSaved);
    }

    //PAGE
    public PageDTO<AutistaDTO> getPage(Pageable pageable) {
        Page<Autista> autistaPage = autistaRepository.findAll(pageable);
        return autistaMapstructMapper.mapToPageDTO(autistaPage);
    }

    //FILTER
    public PageDTO<AutistaDTO> searchAutistaBySpecification(AutistaSearchParams autistaSearchParams, Pageable pageable) {
        Page<Autista> autistaList = autistaRepository.findAll(autistaSearchParams.toSpecification(), pageable);
        return autistaMapstructMapper.mapToPageDTO(autistaList);
    }
}