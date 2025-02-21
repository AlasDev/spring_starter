package it.objectmethod.spring_starter.service;

import it.objectmethod.spring_starter.dto.CorsaDTO;
import it.objectmethod.spring_starter.dto.PageDTO;
import it.objectmethod.spring_starter.entity.Corsa;
import it.objectmethod.spring_starter.filter.CorsaSearchParams;
import it.objectmethod.spring_starter.mapper.mapstruct.CorsaMapstructMapper;
import it.objectmethod.spring_starter.repository.CorsaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CorsaService {
    private final CorsaRepository corsaRepository;
    private final CorsaMapstructMapper corsaMapstructMapper;

    public CorsaService(CorsaRepository corsaRepository, CorsaMapstructMapper corsaMapstructMapper) {
        this.corsaRepository = corsaRepository;
        this.corsaMapstructMapper = corsaMapstructMapper;
    }

    public List<CorsaDTO> getAll() {
        return corsaMapstructMapper.mapToDtos(corsaRepository.findAll());
    }
    public CorsaDTO getCorsa(Long id) {
        if (!corsaRepository.existsById(id)) {
            throw new NoSuchElementException("Corsa with id " + id + " not found");
        }
        return corsaMapstructMapper.mapToDto(corsaRepository.findById(id).orElseGet(Corsa::new));
    }
    public CorsaDTO setCorsa(CorsaDTO corsaDTO) {
        Corsa corsa = corsaMapstructMapper.mapToEntity(corsaDTO);
        Corsa corsaSaved = corsaRepository.save(corsa);
        return corsaMapstructMapper.mapToDto(corsaSaved);
    }
    public void deleteCorsa(Long id) {
        if (!corsaRepository.existsById(id)) {
            throw new NoSuchElementException("Corsa with id " + id + " not found");
        }
        corsaRepository.deleteById(id);
    }
    public CorsaDTO save(CorsaDTO corsaDTO) {
        return corsaMapstructMapper.mapToDto(corsaRepository.save(corsaMapstructMapper.mapToEntity(corsaDTO)));
    }

    //PAGE
    public PageDTO<CorsaDTO> getPage(Pageable pageable) {
        Page<Corsa> page = corsaRepository.findAll(pageable);
        return corsaMapstructMapper.mapToPageDTO(page);
    }

    //FILTER
    public List<CorsaDTO> searchCorsaBySpecification(CorsaSearchParams corsaSearchParams) {
        List<Corsa> corsaList = corsaRepository.findAll(corsaSearchParams.toSpecification());
        return corsaMapstructMapper.mapToDtos(corsaList);
    }
}