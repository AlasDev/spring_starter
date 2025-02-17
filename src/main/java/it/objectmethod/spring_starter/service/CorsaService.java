package it.objectmethod.spring_starter.service;

import it.objectmethod.spring_starter.dto.CorsaDTO;
import it.objectmethod.spring_starter.dto.PageDTO;
import it.objectmethod.spring_starter.dto.filter.CorsaSearchParams;
import it.objectmethod.spring_starter.entity.Corsa;
import it.objectmethod.spring_starter.mapper.CorsaMapper;
import it.objectmethod.spring_starter.repository.CorsaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CorsaService {
    private final CorsaRepository corsaRepository;
    private final CorsaMapper corsaMapper;

    @Autowired
    public CorsaService(CorsaRepository corsaRepository, CorsaMapper corsaMapper) {
        this.corsaRepository = corsaRepository;
        this.corsaMapper = corsaMapper;
    }

    public List<CorsaDTO> getAll() {
        return corsaMapper.mapToDtos(corsaRepository.findAll());
    }
    public CorsaDTO getCorsa(Long id) {
        return corsaMapper.mapToDto(corsaRepository.getCorsaById(id).orElseGet(Corsa::new));
    }
    public CorsaDTO setCorsa(CorsaDTO corsaDTO) {
        Corsa corsa = corsaMapper.mapToEntity(corsaDTO);
        Corsa corsaSaved = corsaRepository.save(corsa);
        return corsaMapper.mapToDto(corsaSaved);
    }
    public void deleteCorsa(Long id) {
        if (!corsaRepository.existsById(id)) {
            throw new NullPointerException("La corsa con id: " + id + " che stai provando a cancellare non è stata trovata");
        }
        corsaRepository.deleteById(id);
    }
    public CorsaDTO save(CorsaDTO corsaDTO) {
        return corsaMapper.mapToDto(corsaRepository.save(corsaMapper.mapToEntity(corsaDTO)));
    }

    //PAGE
    public PageDTO<CorsaDTO> getPage(Pageable pageable) {
        Page<Corsa> page = corsaRepository.findAll(pageable);
        return corsaMapper.mapToPageDTO(page);
    }

    //FILTER
    public List<CorsaDTO> searchCorsaBySpecification(CorsaSearchParams corsaSearchParams) {
        List<Corsa> corsaList = corsaRepository.findAll(corsaSearchParams.toSpecification());
        return corsaMapper.mapToDtos(corsaList);
    }
}