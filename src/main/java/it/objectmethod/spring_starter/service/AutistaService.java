package it.objectmethod.spring_starter.service;

import it.objectmethod.spring_starter.dto.AutistaDTO;
import it.objectmethod.spring_starter.dto.PageDTO;
import it.objectmethod.spring_starter.entity.Autista;
import it.objectmethod.spring_starter.filter.AutistaSearchParams;
import it.objectmethod.spring_starter.mapper.mapstruct.AutistaMapstructMapper;
import it.objectmethod.spring_starter.repository.AutistaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AutistaService {
    private final AutistaRepository autistaRepository;
    private final AutistaMapstructMapper autistaMapstructMapper;

    public AutistaService(AutistaRepository autistaRepository, AutistaMapstructMapper autistaMapstructMapper) {
        this.autistaRepository = autistaRepository;
        this.autistaMapstructMapper = autistaMapstructMapper;
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
    public AutistaDTO setAutista(AutistaDTO autistaDTO) {
        Autista autista = autistaMapstructMapper.mapToEntity(autistaDTO);
        Autista autistaSaved = autistaRepository.save(autista);
        return autistaMapstructMapper.mapToDto(autistaSaved);
    }
    public void deleteAutista(Long id) {
        if (!autistaRepository.existsById(id)) {
            throw new NoSuchElementException("Autista with id: "+ id +" not found");
        }
        autistaRepository.deleteById(id);
    }
    public AutistaDTO save(AutistaDTO autistaDTO) {
        return setAutista(autistaDTO);
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