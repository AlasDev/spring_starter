package it.objectmethod.spring_starter.service;

import it.objectmethod.spring_starter.dto.CorsaDTO;
import it.objectmethod.spring_starter.dto.PageDTO;
import it.objectmethod.spring_starter.entity.Autista;
import it.objectmethod.spring_starter.entity.Cliente;
import it.objectmethod.spring_starter.entity.Corsa;
import it.objectmethod.spring_starter.exception.exceptions.RequiredValueException;
import it.objectmethod.spring_starter.filter.CorsaSearchParams;
import it.objectmethod.spring_starter.mapper.CorsaMapstructMapper;
import it.objectmethod.spring_starter.repository.AutistaRepository;
import it.objectmethod.spring_starter.repository.ClienteRepository;
import it.objectmethod.spring_starter.repository.CorsaRepository;
import it.objectmethod.spring_starter.util.StatoCorsa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CorsaService {
    private final CorsaRepository corsaRepository;
    private final CorsaMapstructMapper corsaMapstructMapper;
    private final AutistaRepository autistaRepository;
    private final ClienteRepository clienteRepository;

    public CorsaService(CorsaRepository corsaRepository, CorsaMapstructMapper corsaMapstructMapper, AutistaRepository autistaRepository, ClienteRepository clienteRepository) {
        this.corsaRepository = corsaRepository;
        this.corsaMapstructMapper = corsaMapstructMapper;
        this.autistaRepository = autistaRepository;
        this.clienteRepository = clienteRepository;
    }

    public List<CorsaDTO> getAll() {
        return corsaMapstructMapper.mapToDtos(corsaRepository.findAll());
    }

    public CorsaDTO getCorsa(Long id) {
        if (!corsaRepository.existsById(id)) {
            throw new NoSuchElementException("Corsa with id '" + id + "' not found");
        }
        return corsaMapstructMapper.mapToDto(corsaRepository.findById(id).orElseGet(Corsa::new));
    }

    public CorsaDTO updateCorsa(CorsaDTO corsaDTO) {
        Long corsaId = corsaDTO.getId();
        Long autistaId = corsaDTO.getAutista();
        Long clienteId = corsaDTO.getCliente();

        if (corsaId == null) {
            throw new RequiredValueException("Id");
        }
        if (autistaId == null) {
            throw new RequiredValueException("Autista");
        }
        if (clienteId == null) {
            throw new RequiredValueException("Cliente");
        }

        corsaMapstructMapper.mapToDto(corsaRepository.findById(corsaId).orElseThrow(
                () -> new NoSuchElementException("Corsa with id '" + corsaId + "' not found"))
        );

        Autista autista = autistaRepository.findById(autistaId).orElseThrow(
                () -> new NoSuchElementException("Autista with id '" + autistaId + "' not found")
        );

        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(
                () -> new NoSuchElementException("Cliente with id '" + clienteId + "' not found")
        );

        Corsa corsa = corsaMapstructMapper.mapToEntity(corsaDTO);
        corsa.setAutista(autista);
        corsa.setCliente(cliente);
        Corsa corsaUpdated = corsaRepository.save(corsa);
        return corsaMapstructMapper.mapToDto(corsaUpdated);
    }

    public void deleteCorsa(Long id) {
        if (!corsaRepository.existsById(id)) {
            throw new NoSuchElementException("Corsa with id " + id + " not found");
        }
        corsaRepository.deleteById(id);
    }

    public CorsaDTO save(CorsaDTO corsaDTO) {
        corsaDTO.setId(null);
        Long autistaId = corsaDTO.getAutista();
        Long clienteId = corsaDTO.getCliente();
        StatoCorsa statoCorsa = corsaDTO.getStatoCorsa();

        if (autistaId == null) {
            throw new RequiredValueException("Autista");
        }
        if (clienteId == null) {
            throw new RequiredValueException("Cliente");
        }
        if (statoCorsa == null) {
            throw new RequiredValueException("StatoCorsa");
        }

        Autista autista = autistaRepository.findById(autistaId).orElseThrow(
                () -> new NoSuchElementException("Autista with id '" + autistaId + "' not found")
        );

        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(
                () -> new NoSuchElementException("Cliente with id '" + clienteId + "' not found")
        );

        Corsa corsa = corsaMapstructMapper.mapToEntity(corsaDTO);
        corsa.setAutista(autista);
        corsa.setCliente(cliente);
        Corsa corsaUpdated = corsaRepository.save(corsa);
        return corsaMapstructMapper.mapToDto(corsaUpdated);
    }

    //PAGE
    public PageDTO<CorsaDTO> getPage(Pageable pageable) {
        Page<Corsa> page = corsaRepository.findAll(pageable);
        return corsaMapstructMapper.mapToPageDTO(page);
    }

    //FILTER
    public PageDTO<CorsaDTO> searchCorsaBySpecification(CorsaSearchParams corsaSearchParams, Pageable pageable) {
        Page<Corsa> corsaList = corsaRepository.findAll(corsaSearchParams.toSpecification(), pageable);
        return corsaMapstructMapper.mapToPageDTO(corsaList);
    }
}