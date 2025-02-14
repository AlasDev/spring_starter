package it.objectmethod.spring_starter.mapper;

import it.objectmethod.spring_starter.dto.CorsaDTO;
import it.objectmethod.spring_starter.dto.PageDTO;
import it.objectmethod.spring_starter.entity.Autista;
import it.objectmethod.spring_starter.entity.Cliente;
import it.objectmethod.spring_starter.entity.Corsa;
import it.objectmethod.spring_starter.util.BasicMethodMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CorsaMapper implements BasicMethodMapping<CorsaDTO, Corsa> {

    @Autowired
    ClienteMapper clienteMapper;
    @Autowired
    AutistaMapper autistaMapper;

    @Override
    public CorsaDTO mapToDto(Corsa corsa) {
        return CorsaDTO.builder()
                .id(corsa.getId())
                .statoCorsa(corsa.getStatoCorsa())
                .distanzaPercorsa(corsa.getDistanzaPercorsa())
                .costoCorsa(corsa.getCostoCorsa())
                .indirizzoInizio(corsa.getIndirizzoInizio())
                .indirizzoFine(corsa.getIndirizzoFine())
                .dataPrenotazione(corsa.getDataPrenotazione())
                .dataInizio(corsa.getDataInizio())
                .dataFine(corsa.getDataFine())
                .cliente(corsa.getCliente().getId())
                .autista(corsa.getAutista().getId())
                .build();
    }

    @Override
    public Corsa mapToEntity(CorsaDTO corsaDTO) {
        return Corsa.builder()
                .id(corsaDTO.getId())
                .statoCorsa(corsaDTO.getStatoCorsa())
                .distanzaPercorsa(corsaDTO.getDistanzaPercorsa())
                .costoCorsa(corsaDTO.getCostoCorsa())
                .indirizzoInizio(corsaDTO.getIndirizzoInizio())
                .indirizzoFine(corsaDTO.getIndirizzoFine())
                .dataPrenotazione(corsaDTO.getDataPrenotazione())
                .dataInizio(corsaDTO.getDataInizio())
                .dataFine(corsaDTO.getDataFine())
                .cliente(Cliente
                        .builder()
                        .id(corsaDTO.getCliente())
                        .build())
                .autista(Autista
                        .builder()
                        .id(corsaDTO.getAutista())
                        .build())
                .build();
    }

    @Override
    public List<Corsa> mapToEntities(List<CorsaDTO> dtos) {
        List<Corsa> entities = new ArrayList<>();
        for (CorsaDTO dto : dtos) {
            entities.add(mapToEntity(dto));
        }
        return entities;
    }

    @Override
    public List<CorsaDTO> mapToDtos(List<Corsa> entities) {
        List<CorsaDTO> dtos = new ArrayList<>();
        for (Corsa entity : entities) {
            dtos.add(mapToDto(entity));
        }
        return dtos;
    }

    //PAGE
    @Override
    public PageDTO<CorsaDTO> mapToPageDTO(Page<Corsa> page) {
        return PageDTO.<CorsaDTO>builder()
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
