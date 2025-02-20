package it.objectmethod.spring_starter.service;

import it.objectmethod.spring_starter.dto.ClienteDTO;
import it.objectmethod.spring_starter.dto.PageDTO;
import it.objectmethod.spring_starter.entity.Cliente;
import it.objectmethod.spring_starter.filter.ClienteSearchParams;
import it.objectmethod.spring_starter.mapper.mapstruct.ClienteMapstructMapper;
import it.objectmethod.spring_starter.repository.ClienteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;
    private final ClienteMapstructMapper clienteMapstructMapper;

    public ClienteService(ClienteRepository clienteRepository, ClienteMapstructMapper clienteMapstructMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteMapstructMapper = clienteMapstructMapper;
    }

    public List<ClienteDTO> getAll() {
        return clienteMapstructMapper.mapToDtos(clienteRepository.findAll());
    }
    public ClienteDTO getCliente(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new NoSuchElementException("Cliente with id " + id + " not found");
        }
        return clienteMapstructMapper.mapToDto(clienteRepository.findById(id).orElseGet(Cliente::new));
    }
    public ClienteDTO setCliente(ClienteDTO clienteDTO) {
        Cliente cliente = clienteMapstructMapper.mapToEntity(clienteDTO);
        Cliente clienteSaved = clienteRepository.save(cliente);
        return clienteMapstructMapper.mapToDto(clienteSaved);
    }
    public void deleteCliente(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new NoSuchElementException("Cliente with id: " + id + " not found");
        }
        clienteRepository.deleteById(id);
    }
    public ClienteDTO save( ClienteDTO clienteDTO) {
        Cliente cliente = clienteMapstructMapper.mapToEntity(clienteDTO);
        Cliente save = clienteRepository.save(cliente);
        return clienteMapstructMapper.mapToDto(save);
    }

    //PAGE
    public PageDTO<ClienteDTO> getPage(Pageable pageable) {
        Page<Cliente> page = clienteRepository.findAll(pageable);
        return clienteMapstructMapper.mapToPageDTO(page);
    }

    //FILTER
    public List<ClienteDTO> searchClienteBySpecification(ClienteSearchParams clienteSearchParams) {
        List<Cliente> clienteList = clienteRepository.findAll(clienteSearchParams.toSpecification());
        return clienteMapstructMapper.mapToDtos(clienteList);
    }
}