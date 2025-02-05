package it.objectmethod.spring_starter.service;

import it.objectmethod.spring_starter.dto.AutistaDTO;
import it.objectmethod.spring_starter.entity.Autista;
import it.objectmethod.spring_starter.mapper.AutistaMapper;
import it.objectmethod.spring_starter.repository.AutistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutistaService {

    @Autowired
    private AutistaRepository autistaRepository;

    @Autowired
    private AutistaMapper autistaMapper;

    public List<AutistaDTO> getAll() {
        return autistaMapper.mapToDtos(autistaRepository.findAll());
    }

    public AutistaDTO getAutista(Integer id) {
        return autistaMapper.mapToDto(autistaRepository.getAutistaById(id));
    }

    public AutistaDTO setAutista(AutistaDTO autistaDTO) {
        Autista autista = autistaMapper.mapToEntity(autistaDTO);
        Autista save = autistaRepository.save(autista);
        return autistaMapper.mapToDto(save);
    }

    public void deleteAutista(Integer id) {

    }

    public AutistaDTO getByNome(String nome) {
        return autistaMapper.mapToDto(autistaRepository.getAutistaByNome(nome));
    }
}
