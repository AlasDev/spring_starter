package it.objectmethod.spring_starter.mapper;

import it.objectmethod.spring_starter.dto.AutistaDTO;
import it.objectmethod.spring_starter.dto.PageDTO;
import it.objectmethod.spring_starter.entity.Autista;
import it.objectmethod.spring_starter.entity.Veicolo;
import it.objectmethod.spring_starter.util.BasicMethodMapping;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AutistaMapper implements BasicMethodMapping<AutistaDTO, Autista> {

    @Override
    public AutistaDTO mapToDto(Autista autista) {
        return AutistaDTO.builder()
                .id(autista.getId())
                .nome(autista.getNome())
                .cognome(autista.getCognome())
                .dataNascita(autista.getDataNascita())
                .codFiscale(autista.getCodFiscale())
                .veicolo_ID(autista.getVeicolo().getId())
                .build();
    }

    @Override
    public Autista mapToEntity(AutistaDTO autistaDTO) {
        return Autista.builder()
                .id(autistaDTO.getId())
                .nome(autistaDTO.getNome())
                .cognome(autistaDTO.getCognome())
                .dataNascita(autistaDTO.getDataNascita())
                .codFiscale(autistaDTO.getCodFiscale())
                .veicolo(Veicolo.builder()
                        .id(autistaDTO.getId())
                        .build())
                .build();
    }

    @Override
    public List<Autista> mapToEntities(List<AutistaDTO> dtos) {
        List<Autista> entities = new ArrayList<>();
        for (AutistaDTO dto : dtos) {
            entities.add(mapToEntity(dto));
        }
        return entities;
    }

    @Override
    public List<AutistaDTO> mapToDtos(List<Autista> entities) {
        List<AutistaDTO> dtos = new ArrayList<>();
        for (Autista entity : entities) {
            dtos.add(mapToDto(entity));
        }
        return dtos;
    }

    //PAGE
    @Override
    public PageDTO<AutistaDTO> mapToPageDTO(Page<Autista> page) {
        return PageDTO.<AutistaDTO>builder()
                .content(this.mapToDtos(page.getContent()))
                .pageSize(page.getSize())
                .numberOfElements(page.getNumberOfElements())
                .firstPage(page.isFirst())
                .lastPage(page.isLast())
                .totalPages(page.getTotalPages())
                .pageNumber(page.getNumber())
                .build();
    }
}