package it.objectmethod.spring_starter.service;

import it.objectmethod.spring_starter.dto.ClienteDTO;
import it.objectmethod.spring_starter.dto.PageDTO;
import it.objectmethod.spring_starter.dto.filter.ClienteSearchParams;
import it.objectmethod.spring_starter.entity.Cliente;
import it.objectmethod.spring_starter.mapper.ClienteMapper;
import it.objectmethod.spring_starter.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }

    public List<ClienteDTO> getAll() {
        return clienteMapper.mapToDtos(clienteRepository.findAll());
    }
    public ClienteDTO getCliente(Long id) {
        return clienteMapper.mapToDto(clienteRepository.findById(id).orElseGet(Cliente::new));
    }
    public ClienteDTO setCliente(ClienteDTO clienteDTO) {
        Cliente cliente = clienteMapper.mapToEntity(clienteDTO);
        Cliente clienteSaved = clienteRepository.save(cliente);
        return clienteMapper.mapToDto(clienteSaved);
    }
    public void deleteCliente(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new EntityNotFoundException("Il cliente con id: " + id + " che stai provando a cancellare non è stato trovato");
        }
        clienteRepository.deleteById(id);
    }
    public ClienteDTO save( ClienteDTO clienteDTO) {
        Cliente cliente = clienteMapper.mapToEntity(clienteDTO);
        Cliente save = clienteRepository.save(cliente);
        return clienteMapper.mapToDto(save);
    }

    //PAGE
    public PageDTO<ClienteDTO> getPage(Pageable pageable) {
        Page<Cliente> page = clienteRepository.findAll(pageable);

        PageDTO<ClienteDTO> pageDto = clienteMapper.mapToPageDTO(page);
        return pageDto;
    }

    //FILTER
    public List<ClienteDTO> searchClienteBySpecification(ClienteSearchParams clienteSearchParams) {
        List<Cliente> clienteList = clienteRepository.findAll(clienteSearchParams.toSpecification());
        return clienteMapper.mapToDtos(clienteList);
    }
}