package it.objectmethod.spring_starter.service;

import it.objectmethod.spring_starter.mapper.CorsaMapper;
import it.objectmethod.spring_starter.repository.CorsaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CorsaService {
    private final CorsaRepository corsaRepository;
    private final CorsaMapper corsaMapper;

    @Autowired
    public CorsaService(CorsaRepository corsaRepository, CorsaMapper corsaMapper) {
        this.corsaRepository = corsaRepository;
        this.corsaMapper = corsaMapper;
    }
}
