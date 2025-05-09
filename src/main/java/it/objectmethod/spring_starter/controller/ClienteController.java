package it.objectmethod.spring_starter.controller;

import it.objectmethod.spring_starter.dto.ClienteDTO;
import it.objectmethod.spring_starter.dto.PageDTO;
import it.objectmethod.spring_starter.filter.ClienteSearchParams;
import it.objectmethod.spring_starter.service.ClienteService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/get/all")
    public List<ClienteDTO> getAll() {
        return clienteService.getAll();
    }

    @GetMapping("/get/{id}")
    public ClienteDTO getCliente(@PathVariable @Validated long id) {
        return clienteService.getCliente(id);
    }

    @PutMapping("/update")
    public ClienteDTO updateCliente(@RequestBody @Validated ClienteDTO clienteDTO) {
        return clienteService.updateCliente(clienteDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCliente(@PathVariable @Validated long id) {
        clienteService.deleteCliente(id);
    }

    @PostMapping("/post")
    public ClienteDTO addCliente(@RequestBody @Validated ClienteDTO clienteDTO) {
        return clienteService.save(clienteDTO);
    }

    //PAGE
    @GetMapping("/page")
    public PageDTO<ClienteDTO> getPage(@PageableDefault(size = 5) Pageable pageable) {
        return clienteService.getPage(pageable);
    }

    //FILTER
    @GetMapping("/filter")
    public PageDTO<ClienteDTO> filter(@PageableDefault(size = 5) Pageable pageable, ClienteSearchParams clienteSearchParams) {
        return clienteService.searchClienteBySpecification(clienteSearchParams, pageable);
    }
}