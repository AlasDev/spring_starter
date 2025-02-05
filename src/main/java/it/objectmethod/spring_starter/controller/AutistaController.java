package it.objectmethod.spring_starter.controller;

import it.objectmethod.spring_starter.dto.AutistaDTO;
import it.objectmethod.spring_starter.service.AutistaService;
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
    @GetMapping("")
    public List<AutistaDTO> getAll() {
        return autistaService.getAll();
    }

    //read something
    @GetMapping("/{id}") // esempio di url: localhost:8080/autista/5
    public AutistaDTO getAutista(@PathVariable Integer id){
        return autistaService.getAutista(id);
    }

    //update something
    @PutMapping("")
    public AutistaDTO updateAutista(@RequestBody AutistaDTO autistaDTO){
        return autistaService.setAutista(autistaDTO);
    }

    //delete something
    @DeleteMapping("/delete")
    public void deleteAutista(Integer id){
        autistaService.deleteAutista(id);
    }

    //read something
    @GetMapping("/find") // esempio di url: localhost:8080/autista/find?nome=gianni
    public AutistaDTO getAll(@RequestParam String nome) {
        return autistaService.getByNome(nome);
    }
}
