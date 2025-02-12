package it.objectmethod.spring_starter.controller;

import it.objectmethod.spring_starter.dto.ClienteDTO;
import it.objectmethod.spring_starter.dto.PageDTO;
import it.objectmethod.spring_starter.dto.filter.ClienteSearchParams;
import it.objectmethod.spring_starter.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("")
    public List<ClienteDTO> getAll() {
        return clienteService.getAll();
    }

    @GetMapping("/{id}")
    public ClienteDTO getCliente(@PathVariable @Validated long id) {
        return clienteService.getCliente(id);
    }

    @PutMapping("")
    public ClienteDTO updateCliente(@RequestBody @Validated ClienteDTO clienteDTO) {
        return clienteService.setCliente(clienteDTO);
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
    public PageDTO<ClienteDTO> getPage(@PageableDefault(page = 0, size = 5) Pageable pageable) {
        return clienteService.getPage(pageable);
    }

    //FILTER
    @GetMapping("/filter")
    public List<ClienteDTO> filter(ClienteSearchParams clienteSearchParams) {
        return clienteService.searchClienteBySpecification(clienteSearchParams);
    }
}