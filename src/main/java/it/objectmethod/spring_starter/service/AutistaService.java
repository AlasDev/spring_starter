package it.objectmethod.spring_starter.service;

import it.objectmethod.spring_starter.dto.AutistaDTO;
import it.objectmethod.spring_starter.dto.PageDTO;
import it.objectmethod.spring_starter.entity.Autista;
import it.objectmethod.spring_starter.entity.Utente;
import it.objectmethod.spring_starter.entity.Veicolo;
import it.objectmethod.spring_starter.exception.exceptions.RequiredValueException;
import it.objectmethod.spring_starter.filter.AutistaSearchParams;
import it.objectmethod.spring_starter.mapper.AutistaMapstructMapper;
import it.objectmethod.spring_starter.repository.AutistaRepository;
import it.objectmethod.spring_starter.repository.UtenteRepository;
import it.objectmethod.spring_starter.repository.VeicoloRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AutistaService {
    private final AutistaRepository autistaRepository;
    private final AutistaMapstructMapper autistaMapstructMapper;

    private final UtenteRepository utenteRepository;
    private final VeicoloRepository veicoloRepository;

    public AutistaService(AutistaRepository autistaRepository, AutistaMapstructMapper autistaMapstructMapper, UtenteRepository utenteRepository, VeicoloRepository veicoloRepository) {
        this.autistaRepository = autistaRepository;
        this.autistaMapstructMapper = autistaMapstructMapper;
        this.utenteRepository = utenteRepository;
        this.veicoloRepository = veicoloRepository;
    }

    public List<AutistaDTO> getAll() {
        return autistaMapstructMapper.mapToDtos(autistaRepository.findAll());
    }

    public AutistaDTO getAutista(Long id) {
        if (!autistaRepository.existsById(id)) {
            throw new NoSuchElementException("Autista with id '" + id + "' not found");
        }
        return autistaMapstructMapper.mapToDto(autistaRepository.getReferenceById(id));
    }

    public AutistaDTO updateAutista(AutistaDTO autistaDTO) {
        Long autistaId = autistaDTO.getId(); //Id
        Long veicoloId = autistaDTO.getVeicolo(); //Veicolo
        Long utenteId = autistaDTO.getUtente(); //Utente

        if (autistaId == null) {
            throw new RequiredValueException("Id");
        }
        if (veicoloId == null) {
            throw new RequiredValueException("Veicolo");
        }
        if (utenteId == null) {
            throw new RequiredValueException("Utente");
        }

        autistaMapstructMapper.mapToDto(autistaRepository.findById(autistaId).orElseThrow(
                () -> new NoSuchElementException("Autista with id '" + autistaId + "' not found")));

        Veicolo veicolo = veicoloRepository.findById(veicoloId).orElseThrow(
                () -> new NoSuchElementException("Veicolo with id '" + veicoloId + "' not found"));

        Utente utente = utenteRepository.findById(utenteId).orElseThrow(
                () -> new NoSuchElementException("Utente with id '" + utenteId + "' not found"));

        Autista autista = autistaMapstructMapper.mapToEntity(autistaDTO);
        autista.setVeicolo(veicolo);
        autista.setUtente(utente);
        Autista autistaUpdated = autistaRepository.save(autista);
        return autistaMapstructMapper.mapToDto(autistaUpdated);
    }

    public void deleteAutista(Long id) {
        if (!autistaRepository.existsById(id)) {
            throw new NoSuchElementException("Autista with id: " + id + " not found");
        }
        autistaRepository.deleteById(id);
    }

    public AutistaDTO save(AutistaDTO autistaDTO) {
        autistaDTO.setId(null); //Autista id must not be there, that's why we will ignore it if it gets inputted anyway.

        if (autistaDTO.getVeicolo() == null) {
            //"Un autista senza veicolo non è un autista, è solo un pedone."
            //-cit Sala Davide
            throw new RequiredValueException("Veicolo");
        }
        if (autistaDTO.getUtente() == null) {
            throw new RequiredValueException("Utente");
        }

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
    public List<AutistaDTO> searchAutistaBySpecification(AutistaSearchParams autistaSearchParams) {
        List<Autista> autistaList = autistaRepository.findAll(autistaSearchParams.toSpecification());
        return autistaMapstructMapper.mapToDtos(autistaList);
    }
}