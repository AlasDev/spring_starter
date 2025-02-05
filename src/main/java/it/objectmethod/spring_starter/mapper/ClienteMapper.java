package it.objectmethod.spring_starter.mapper;

import it.objectmethod.spring_starter.dto.ClienteDTO;
import it.objectmethod.spring_starter.entity.Cliente;
import it.objectmethod.spring_starter.util.BasicMethodMapping;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClienteMapper implements BasicMethodMapping<ClienteDTO, Cliente> {

    @Override
    public ClienteDTO mapToDto(Cliente cliente) {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(cliente.getId());
        clienteDTO.setNome(cliente.getNome());
        clienteDTO.setCognome(cliente.getCognome());
        clienteDTO.setDataNascita(cliente.getDataNascita());
        clienteDTO.setCodiceFiscale(cliente.getCodFiscale());
        clienteDTO.setDataIscrizione(cliente.getDataIscrizione());
        return clienteDTO;
    }

    @Override
    public Cliente mapToEntity(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setId(clienteDTO.getId());
        cliente.setNome(clienteDTO.getNome());
        cliente.setCognome(clienteDTO.getCognome());
        cliente.setDataNascita(clienteDTO.getDataNascita());
        cliente.setCodFiscale(clienteDTO.getCodiceFiscale());
        cliente.setDataIscrizione(clienteDTO.getDataIscrizione());
        return cliente;
    }

    @Override
    public List<Cliente> mapToEntities(List<ClienteDTO> clienteDTOS) {
        List<Cliente> clientes = new ArrayList<>();
        for (ClienteDTO clienteDTO : clienteDTOS) {
            clientes.add(mapToEntity(clienteDTO));
        }
        return clientes;
    }

    @Override
    public List<ClienteDTO> mapToDtos(List<Cliente> clientes) {
        List<ClienteDTO> clienteDTOS = new ArrayList<>();
        for (Cliente cliente : clientes) {
            clienteDTOS.add(mapToDto(cliente));
        }
        return clienteDTOS;
    }
}
