package it.objectmethod.spring_starter.mapper;

import it.objectmethod.spring_starter.dto.ClienteDTO;
import it.objectmethod.spring_starter.dto.PageDTO;
import it.objectmethod.spring_starter.entity.Cliente;
import it.objectmethod.spring_starter.util.BasicMethodMapping;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClienteMapper implements BasicMethodMapping<ClienteDTO, Cliente> {

    @Override
    public ClienteDTO mapToDto(Cliente cliente) {
        return ClienteDTO.builder()
                .id(cliente.getId())
                .nome(cliente.getNome())
                .cognome(cliente.getCognome())
                .dataNascita(cliente.getDataNascita())
                .codFiscale(cliente.getCodFiscale())
                .dataIscrizione(cliente.getDataIscrizione())
                .build();
    }

    @Override
    public Cliente mapToEntity(ClienteDTO clienteDTO) {
        return Cliente.builder()
                .id(clienteDTO.getId())
                .nome(clienteDTO.getNome())
                .cognome(clienteDTO.getCognome())
                .dataNascita(clienteDTO.getDataNascita())
                .codFiscale(clienteDTO.getCodFiscale())
                .dataIscrizione(clienteDTO.getDataIscrizione())
                .build();
    }

    @Override
    public List<Cliente> mapToEntities(List<ClienteDTO> dtos) {
        List<Cliente> entities = new ArrayList<>();
        for (ClienteDTO dto : dtos) {
            entities.add(mapToEntity(dto));
        }
        return entities;
    }

    @Override
    public List<ClienteDTO> mapToDtos(List<Cliente> entities) {
        List<ClienteDTO> dtos = new ArrayList<>();
        for (Cliente entity : entities) {
            dtos.add(mapToDto(entity));
        }
        return dtos;
    }

    //PAGE
    @Override
    public PageDTO<ClienteDTO> mapToPageDTO(Page<Cliente> page) {
        return PageDTO.<ClienteDTO>builder()
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
