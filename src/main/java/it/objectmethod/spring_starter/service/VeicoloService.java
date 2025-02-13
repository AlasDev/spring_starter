package it.objectmethod.spring_starter.service;

import it.objectmethod.spring_starter.dto.PageDTO;
import it.objectmethod.spring_starter.dto.VeicoloDTO;
import it.objectmethod.spring_starter.dto.filter.VeicoloSearchParams;
import it.objectmethod.spring_starter.entity.Veicolo;
import it.objectmethod.spring_starter.mapper.VeicoloMapper;
import it.objectmethod.spring_starter.repository.VeicoloRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeicoloService {
    private final VeicoloRepository veicoloRepository;
    private final VeicoloMapper veicoloMapper;

    @Autowired
    public VeicoloService(VeicoloRepository veicoloRepository, VeicoloMapper veicoloMapper) {
        this.veicoloRepository = veicoloRepository;
        this.veicoloMapper = veicoloMapper;
    }

    public List<VeicoloDTO> getAll() {
        return veicoloMapper.mapToDtos(veicoloRepository.findAll());
    }
    public VeicoloDTO getVeicolo(Long id) {
        return veicoloMapper.mapToDto(veicoloRepository.getVeicoloById(id).orElseGet(Veicolo::new));
    }
    public VeicoloDTO setVeicolo(VeicoloDTO veicoloDTO) {
        Veicolo veicolo = veicoloMapper.mapToEntity(veicoloDTO);
        Veicolo veicoloSaved = veicoloRepository.save(veicolo);
        return veicoloMapper.mapToDto(veicoloSaved);
    }
    public void deleteVeicolo(Long id) {
        if (!veicoloRepository.existsById(id)) {
            throw new EntityNotFoundException("Il veicolo con id:" + id + " che stai provando a cancellare non è stato trovato");
        }
        veicoloRepository.deleteById(id);
    }
    public VeicoloDTO save(VeicoloDTO veicoloDTO) {
        return veicoloMapper.mapToDto(veicoloRepository.save(veicoloMapper.mapToEntity(veicoloDTO)));
    }

    //PAGE
    public PageDTO<VeicoloDTO> getPage(Pageable pageable) {
        Page<Veicolo> page = veicoloRepository.findAll(pageable);

        PageDTO<VeicoloDTO> pageDto = veicoloMapper.mapToPageDTO(page);
        return pageDto;
    }

    //FILTER
    public List<VeicoloDTO> searchVeicoloBySpecification(VeicoloSearchParams veicoloSearchParams) {
        List<Veicolo> veicoloList = veicoloRepository.findAll(veicoloSearchParams.toSpecification());
        return veicoloMapper.mapToDtos(veicoloList);
    }
}