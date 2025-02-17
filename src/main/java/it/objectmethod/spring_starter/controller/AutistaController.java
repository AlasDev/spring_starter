package it.objectmethod.spring_starter.controller;

import it.objectmethod.spring_starter.dto.AutistaDTO;
import it.objectmethod.spring_starter.dto.PageDTO;
import it.objectmethod.spring_starter.dto.filter.AutistaSearchParams;
import it.objectmethod.spring_starter.service.AutistaService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autista")
public class AutistaController {

    private final AutistaService autistaService;

    public AutistaController(AutistaService autistaService) {
        this.autistaService = autistaService;
    }

    //copy all
    @GetMapping("/get/all") // esempio di url: localhost:8080/autista/get/all
    public List<AutistaDTO> getAll() {
        return autistaService.getAll();
    }

    //read something
    @GetMapping("/get/{id}") // esempio di url: localhost:8080/autista/get/5
    public AutistaDTO getAutista(@PathVariable @Validated Long id) {
        return autistaService.getAutista(id);
    }

    //update something
    @PutMapping("/update") // esempio di url: localhost:8080/autista/update
    public AutistaDTO updateAutista(@RequestBody @Validated AutistaDTO autistaDTO) {
        return autistaService.setAutista(autistaDTO);
    }

    //delete something
    @DeleteMapping("/delete/{id}") // esempio di url: localhost:8080/autista/delete/3
    public void deleteAutista(@PathVariable @Validated Long id) {
         autistaService.deleteAutista(id);
    }

    //post (save) something new
    @PostMapping("/post") // esempio di url: localhost:8080/autista/post
    public AutistaDTO addAutista(@RequestBody @Validated AutistaDTO autistaDTO) {
        return autistaService.save(autistaDTO);
    }

    //PAGE
    @GetMapping("/page")
    public PageDTO<AutistaDTO> getPage(@PageableDefault(page = 0, size = 5) Pageable pageable) {
        return autistaService.getPage(pageable);
    }

    //FILTER
    @GetMapping("/filter")
    public List<AutistaDTO> filter(AutistaSearchParams autistaSearchParams) {
        return autistaService.searchAutistaBySpecification(autistaSearchParams);
    }
}