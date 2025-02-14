package it.objectmethod.spring_starter.service;

import it.objectmethod.spring_starter.dto.AutistaDTO;
import it.objectmethod.spring_starter.dto.PageDTO;
import it.objectmethod.spring_starter.dto.filter.AutistaSearchParams;
import it.objectmethod.spring_starter.entity.Autista;
import it.objectmethod.spring_starter.mapper.AutistaMapper;
import it.objectmethod.spring_starter.repository.AutistaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AutistaService {
    private final AutistaRepository autistaRepository;
    private final AutistaMapper autistaMapper;

    @Autowired
    public AutistaService(AutistaRepository autistaRepository, AutistaMapper autistaMapper) {
        this.autistaRepository = autistaRepository;
        this.autistaMapper = autistaMapper;
    }

    public List<AutistaDTO> getAll() {
        return autistaMapper.mapToDtos(autistaRepository.findAll());
    }
    public AutistaDTO getAutista( Long id) {
        return autistaMapper.mapToDto(autistaRepository.getReferenceById(id));
    }
    public AutistaDTO setAutista( AutistaDTO autistaDTO) {
        Autista autista = autistaMapper.mapToEntity(autistaDTO);
        Autista autistaSaved = autistaRepository.save(autista);
        return autistaMapper.mapToDto(autistaSaved);
    }
    public void deleteAutista( Long id) {
        if (!autistaRepository.existsById(id)) {
            throw new EntityNotFoundException("L'autista con id: " + id + " che stai provando a cancellare non è stato trovato");
        }
        autistaRepository.deleteById(id);
    }
    public AutistaDTO save( AutistaDTO autistaDTO) {
        return setAutista(autistaDTO);
    }

//    public List<AutistaDTO> byNome( String nome) {
//        return autistaMapper.mapToDtos(autistaRepository.getAutistaByNome(nome).orElse(new ArrayList<>()));
//    }
//    public List<AutistaDTO> byCognome( String cognome) {
//        return autistaMapper.mapToDtos(autistaRepository.getAutistaByCognome(cognome).orElse(new ArrayList<>()));
//    }
//    public List<AutistaDTO> byDataNascita(LocalDate dataNascita) {
//        return autistaMapper.mapToDtos(autistaRepository.getAutistaByDataNascita(dataNascita).orElse(new ArrayList<>()));
//    }
//    public List<AutistaDTO> byCodFiscale( String codFiscale) {
//        return autistaMapper.mapToDtos(autistaRepository.getAutistaByCodFiscale(codFiscale).orElse(new ArrayList<>()));
//    }
//    public List<AutistaDTO> byVeicoloId(Long veicoloId) {
//        return autistaMapper.mapToDtos(autistaRepository.getAutistaByVeicoloId(veicoloId).orElse(new ArrayList<>()));
//    }

    //PAGE
    public PageDTO<AutistaDTO> getPage(Pageable pageable) {
        Page<Autista> autistaPage = autistaRepository.findAll(pageable);
        PageDTO<AutistaDTO> pageAutistaDto = autistaMapper.mapToPageDTO(autistaPage);
        return pageAutistaDto;
    }

    //FILTER
    public List<AutistaDTO> searchAutistaBySpecification(AutistaSearchParams autistaSearchParams) {
        List<Autista> autistaList = autistaRepository.findAll(autistaSearchParams.toSpecification());
        return autistaMapper.mapToDtos(autistaList);
    }
}