package it.objectmethod.spring_starter.controller;

import it.objectmethod.spring_starter.dto.AutistaDTO;
import it.objectmethod.spring_starter.service.AutistaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autista")
public class AutistaController {

    @Autowired
    private AutistaService autistaService;

    //CRUD operations:
    //copy all
    @GetMapping("") // esempio di url: localhost:8080/autista
    public List<AutistaDTO> getAll() {
        return autistaService.getAll();
    }

    //read something
    @GetMapping("/{id}") // esempio di url: localhost:8080/autista/5
    public AutistaDTO getAutista(@PathVariable Integer id) {
        return autistaService.getAutista(id);
    }

    //update something
    @PutMapping("") // esempio di url: localhost:8080/autista
    public AutistaDTO updateAutista(@RequestBody @Valid AutistaDTO autistaDTO) {
        return autistaService.setAutista(autistaDTO);
    }

    //delete something
    @DeleteMapping("/delete/{id}") // esempio di url: localhost:8080/autista/delete/3
    public AutistaDTO deleteAutista(@PathVariable Integer id) {
        return autistaService.deleteAutista(id);
    }

    //put (save) something new
    @PostMapping("/put")
    public AutistaDTO addAutista(@RequestBody @Valid AutistaDTO autistaDTO) {
        return autistaService.save(autistaDTO);
    }

    //read something based on "nome"
    @GetMapping("/find") // esempio di url: localhost:8080/autista/find?nome=gianni
    public AutistaDTO getByName(@RequestParam String nome) {
        return autistaService.getByNome(nome);
    }
}