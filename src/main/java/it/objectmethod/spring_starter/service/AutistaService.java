package it.objectmethod.spring_starter.service;

import it.objectmethod.spring_starter.dto.AutistaDTO;
import it.objectmethod.spring_starter.entity.Autista;
import it.objectmethod.spring_starter.mapper.AutistaMapper;
import it.objectmethod.spring_starter.repository.AutistaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public AutistaDTO getAutista(Integer id) {
        return autistaMapper.mapToDto(autistaRepository.getAutistaById(id).orElseGet(() -> new Autista()));
    }

    public AutistaDTO setAutista(AutistaDTO autistaDTO) {
        Autista autista = autistaMapper.mapToEntity(autistaDTO);
        Autista save = autistaRepository.save(autista);
        return autistaMapper.mapToDto(save);
    }

    public AutistaDTO deleteAutista(Integer id) {
        if(!autistaRepository.existsById(id.longValue())) {
            throw new NullPointerException("L'autista con id:"+ id +" che stai provando a cancellare non è stato trovato");
        }
        autistaRepository.deleteById(Long.valueOf(id));
        return getAutista(id); //the autista just deleted
    }

    public AutistaDTO save(@Valid AutistaDTO autistaDTO) {
        autistaRepository.save(autistaMapper.mapToEntity(autistaDTO));
        return getAutista(autistaDTO.getId());
    }

    public AutistaDTO getByNome(String nome) {
        return autistaMapper.mapToDto(autistaRepository.getAutistaByNome(nome));
    }
}
