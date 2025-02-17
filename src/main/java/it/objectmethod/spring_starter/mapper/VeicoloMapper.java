package it.objectmethod.spring_starter.mapper;

import it.objectmethod.spring_starter.dto.PageDTO;
import it.objectmethod.spring_starter.dto.VeicoloDTO;
import it.objectmethod.spring_starter.entity.Veicolo;
import it.objectmethod.spring_starter.util.BasicMethodMapping;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VeicoloMapper implements BasicMethodMapping<VeicoloDTO, Veicolo> {
    @Override
    public VeicoloDTO mapToDto(Veicolo veicolo) {
        return VeicoloDTO.builder()
                .id(veicolo.getId())
                .numTarga(veicolo.getNumTarga())
                .modello(veicolo.getModello())
                .colore(veicolo.getColore())
                .build();
    }

    @Override
    public Veicolo mapToEntity(VeicoloDTO veicoloDTO) {
        return Veicolo.builder()
                .id(veicoloDTO.getId())
                .numTarga(veicoloDTO.getNumTarga())
                .modello(veicoloDTO.getModello())
                .colore(veicoloDTO.getColore())
                .build();
    }

    @Override
    public List<Veicolo> mapToEntities(List<VeicoloDTO> dtos) {
        List<Veicolo> entities = new ArrayList<>();
        for (VeicoloDTO dto : dtos) {
            entities.add(mapToEntity(dto));
        }
        return entities;
    }

    @Override
    public List<VeicoloDTO> mapToDtos(List<Veicolo> entities) {
        List<VeicoloDTO> dtos = new ArrayList<>();
        for (Veicolo entity : entities) {
            dtos.add(mapToDto(entity));
        }
        return dtos;
    }

    //PAGE
    @Override
    public PageDTO<VeicoloDTO> mapToPageDTO(Page<Veicolo> page) {
        return PageDTO.<VeicoloDTO>builder()
                .content(this.mapToDtos(page.getContent()))
                .size(page.getSize())
                .numberOfElements(page.getNumberOfElements())
                .first(page.isFirst())
                .last(page.isLast())
                .totalPages(page.getTotalPages())
                .number(page.getNumber())
                .build();
    }
}
