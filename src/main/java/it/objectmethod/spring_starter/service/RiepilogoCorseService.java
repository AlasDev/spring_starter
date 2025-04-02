package it.objectmethod.spring_starter.service;

import it.objectmethod.spring_starter.dto.PageDTO;
import it.objectmethod.spring_starter.dto.RiepilogoCorseDTO;
import it.objectmethod.spring_starter.entity.RiepilogoCorse;
import it.objectmethod.spring_starter.mapper.RiepilogoCorseMapstructMapper;
import it.objectmethod.spring_starter.repository.RiepilogoCorseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RiepilogoCorseService {
    private final RiepilogoCorseMapstructMapper riepilogoCorseMapstructMapper;
    private final RiepilogoCorseRepository riepilogoCorseRepository;

    public RiepilogoCorseService(RiepilogoCorseMapstructMapper riepilogoCorseMapstructMapper, RiepilogoCorseRepository riepilogoCorseRepository) {
        this.riepilogoCorseMapstructMapper = riepilogoCorseMapstructMapper;
        this.riepilogoCorseRepository = riepilogoCorseRepository;
    }

    public List<RiepilogoCorseDTO> getAll() {
        return riepilogoCorseMapstructMapper.mapToDtos(riepilogoCorseRepository.findAll());
    }

    public PageDTO<RiepilogoCorseDTO> getAll(Pageable pageable) {
        Page<RiepilogoCorse> page = riepilogoCorseRepository.findAll(pageable);
        return riepilogoCorseMapstructMapper.mapToPageDTO(page);
    }

    public RiepilogoCorseDTO getById(Long id) {
        if (!riepilogoCorseRepository.existsById(id)) {
            throw new NoSuchElementException("RiepilogoCorse with id '" + id + "' not found");
        }
        return riepilogoCorseMapstructMapper.mapToDto(riepilogoCorseRepository.findById(id).orElseGet(RiepilogoCorse::new));
    }

    public long count() {
        List<RiepilogoCorse> all = riepilogoCorseRepository.findAll();
        return all.size();
    }
}
