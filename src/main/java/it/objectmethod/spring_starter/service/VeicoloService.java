package it.objectmethod.spring_starter.service;

import it.objectmethod.spring_starter.dto.PageDTO;
import it.objectmethod.spring_starter.dto.VeicoloDTO;
import it.objectmethod.spring_starter.entity.Veicolo;
import it.objectmethod.spring_starter.filter.VeicoloSearchParams;
import it.objectmethod.spring_starter.mapper.mapstruct.VeicoloMapstructMapper;
import it.objectmethod.spring_starter.repository.VeicoloRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class VeicoloService {
    private final VeicoloRepository veicoloRepository;
    private final VeicoloMapstructMapper veicoloMapstructMapper;

    public VeicoloService(VeicoloRepository veicoloRepository, VeicoloMapstructMapper veicoloMapstructMapper) {
        this.veicoloRepository = veicoloRepository;
        this.veicoloMapstructMapper = veicoloMapstructMapper;
    }

    public List<VeicoloDTO> getAll() {
        return veicoloMapstructMapper.mapToDtos(veicoloRepository.findAll());
    }
    public VeicoloDTO getVeicolo(Long id) {
        if (!veicoloRepository.existsById(id)) {
            throw new NoSuchElementException("Veicolo with id " + id + " not found");
        }
        return veicoloMapstructMapper.mapToDto(veicoloRepository.getVeicoloById(id).orElseGet(Veicolo::new));
    }
    public VeicoloDTO setVeicolo(VeicoloDTO veicoloDTO) {
        Veicolo veicolo = veicoloMapstructMapper.mapToEntity(veicoloDTO);
        Veicolo veicoloSaved = veicoloRepository.save(veicolo);
        return veicoloMapstructMapper.mapToDto(veicoloSaved);
    }
    public void deleteVeicolo(Long id) {
        if (!veicoloRepository.existsById(id)) {
            throw new NoSuchElementException("Veicolo with id " + id + " not found");
        }
        veicoloRepository.deleteById(id);
    }
    public VeicoloDTO save(VeicoloDTO veicoloDTO) {
        return veicoloMapstructMapper.mapToDto(veicoloRepository.save(veicoloMapstructMapper.mapToEntity(veicoloDTO)));
    }

    //PAGE
    public PageDTO<VeicoloDTO> getPage(Pageable pageable) {
        Page<Veicolo> page = veicoloRepository.findAll(pageable);
        return veicoloMapstructMapper.mapToPageDTO(page);
    }

    //FILTER
    public List<VeicoloDTO> searchVeicoloBySpecification(VeicoloSearchParams veicoloSearchParams) {
        List<Veicolo> veicoloList = veicoloRepository.findAll(veicoloSearchParams.toSpecification());
        return veicoloMapstructMapper.mapToDtos(veicoloList);
    }
}