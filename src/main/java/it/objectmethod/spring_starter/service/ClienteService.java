package it.objectmethod.spring_starter.service;

import it.objectmethod.spring_starter.dto.ClienteDTO;
import it.objectmethod.spring_starter.dto.PageDTO;
import it.objectmethod.spring_starter.entity.Cliente;
import it.objectmethod.spring_starter.entity.Utente;
import it.objectmethod.spring_starter.filter.ClienteSearchParams;
import it.objectmethod.spring_starter.mapper.ClienteMapstructMapper;
import it.objectmethod.spring_starter.repository.ClienteRepository;
import it.objectmethod.spring_starter.repository.UtenteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;
    private final ClienteMapstructMapper clienteMapstructMapper;
    private final UtenteRepository utenteRepository;

    public ClienteService(ClienteRepository clienteRepository, ClienteMapstructMapper clienteMapstructMapper, UtenteRepository utenteRepository) {
        this.clienteRepository = clienteRepository;
        this.clienteMapstructMapper = clienteMapstructMapper;
        this.utenteRepository = utenteRepository;
    }

    public List<ClienteDTO> getAll() {
        return clienteMapstructMapper.mapToDtos(clienteRepository.findAll());
    }

    public ClienteDTO getCliente(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new NoSuchElementException("Cliente with id '" + id + "' not found");
        }
        return clienteMapstructMapper.mapToDto(clienteRepository.findById(id).orElseGet(Cliente::new));
    }

    public ClienteDTO updateCliente(@Validated ClienteDTO clienteDTO) {
        Long clienteId = clienteDTO.getId(); //Id
        Long utenteId = clienteDTO.getUtente(); //Utente

        clienteMapstructMapper.mapToDto(clienteRepository.findById(clienteId).orElseThrow(
                () -> new NoSuchElementException("Cliente with id '" + clienteId + "' not found")
        ));
        Utente utente = utenteRepository.findById(utenteId).orElseThrow(
                () -> new NoSuchElementException("Utente with id '" + utenteId + "' not found"));

        Cliente cliente = clienteMapstructMapper.mapToEntity(clienteDTO);
        cliente.setUtente(utente);
        Cliente clienteUpdated = clienteRepository.save(cliente);
        return clienteMapstructMapper.mapToDto(clienteUpdated);
    }

    public void deleteCliente(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new NoSuchElementException("Cliente with id: " + id + " not found");
        }
        clienteRepository.deleteById(id);
    }

    public ClienteDTO save(@Validated ClienteDTO clienteDTO) {
        clienteDTO.setId(null);

        Long utenteId = clienteDTO.getUtente();

        Utente utente = utenteRepository.findById(utenteId).orElseThrow(
                () -> new NoSuchElementException("Utente with id '" + utenteId + "' not found"));

        Cliente cliente = clienteMapstructMapper.mapToEntity(clienteDTO);
        cliente.setUtente(utente);
        Cliente clienteSaved = clienteRepository.save(cliente);
        return clienteMapstructMapper.mapToDto(clienteSaved);
    }

    //PAGE
    public PageDTO<ClienteDTO> getPage(Pageable pageable) {
        Page<Cliente> page = clienteRepository.findAll(pageable);
        return clienteMapstructMapper.mapToPageDTO(page);
    }

    //FILTER
    public PageDTO<ClienteDTO> searchClienteBySpecification(ClienteSearchParams clienteSearchParams, Pageable pageable) {
        Page<Cliente> clienteList = clienteRepository.findAll(clienteSearchParams.toSpecification(), pageable);
        return clienteMapstructMapper.mapToPageDTO(clienteList);
    }
}