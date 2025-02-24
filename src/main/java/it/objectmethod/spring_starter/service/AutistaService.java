package it.objectmethod.spring_starter.service;

import it.objectmethod.spring_starter.dto.AutistaDTO;
import it.objectmethod.spring_starter.dto.PageDTO;
import it.objectmethod.spring_starter.entity.Autista;
import it.objectmethod.spring_starter.entity.Veicolo;
import it.objectmethod.spring_starter.exception.exceptions.RequiredValueIsMissingException;
import it.objectmethod.spring_starter.filter.AutistaSearchParams;
import it.objectmethod.spring_starter.mapper.mapstruct.AutistaMapstructMapper;
import it.objectmethod.spring_starter.repository.AutistaRepository;
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

    private final VeicoloRepository veicoloRepository;

    public AutistaService(AutistaRepository autistaRepository, AutistaMapstructMapper autistaMapstructMapper, VeicoloRepository veicoloRepository) {
        this.autistaRepository = autistaRepository;
        this.autistaMapstructMapper = autistaMapstructMapper;
        this.veicoloRepository = veicoloRepository;
    }

    public List<AutistaDTO> getAll() {
        return autistaMapstructMapper.mapToDtos(autistaRepository.findAll());
    }

    public AutistaDTO getAutista(Long id) {
        if (!autistaRepository.existsById(id)) {
            throw new NoSuchElementException("Autista with id " + id + " not found");
        }
        return autistaMapstructMapper.mapToDto(autistaRepository.getReferenceById(id));
    }

    public AutistaDTO updateAutista(AutistaDTO autistaDTO) {
        Long autistaId = autistaDTO.getId(); //Id
        Long veicoloId = autistaDTO.getVeicolo(); //Veicolo

        if (autistaId == null) {
            throw new RequiredValueIsMissingException("Id");
        }
        if (veicoloId == null) {
            throw new RequiredValueIsMissingException("Veicolo");
        }

        autistaMapstructMapper.mapToDto(autistaRepository.findById(autistaId).orElseThrow(
                () -> new NoSuchElementException("Autista with id '" + autistaId + "' not found")));

        Veicolo veicolo = veicoloRepository.findById(veicoloId).orElseThrow(
                () -> new NoSuchElementException("Veicolo with id '" + veicoloId + "' not found"));

        Autista autista = autistaMapstructMapper.mapToEntity(autistaDTO);
        autista.setVeicolo(veicolo);
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
            throw new RequiredValueIsMissingException("Veicolo");
        }

        Long veicoloId = autistaDTO.getVeicolo();

        Veicolo veicolo = veicoloRepository.findById(veicoloId).orElseThrow(
                () -> new NoSuchElementException("Veicolo with id '" + veicoloId + "' not found"));

        Autista autista = autistaMapstructMapper.mapToEntity(autistaDTO);
        autista.setVeicolo(veicolo);
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