package it.objectmethod.spring_starter.controller;

import it.objectmethod.spring_starter.dto.PageDTO;
import it.objectmethod.spring_starter.dto.UtenteDTO;
import it.objectmethod.spring_starter.filter.UtenteSearchParams;
import it.objectmethod.spring_starter.service.UtenteService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utente")
public class UtenteController {

    private final UtenteService utenteService;

    public UtenteController(UtenteService utenteService) {
        this.utenteService = utenteService;
    }

    @GetMapping("get/all")
    public List<UtenteDTO> getAll() {
        return utenteService.getAll();
    }

    @GetMapping("/get/{id}")
    public UtenteDTO getUtente(@PathVariable @Validated Long id) {
        return utenteService.getUtente(id);
    }

    @PutMapping("/update")
    public UtenteDTO updateUtente(@RequestBody @Validated UtenteDTO utenteDTO) {
        return utenteService.updateUtente(utenteDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUtente(@PathVariable @Validated Long id) {
        utenteService.deleteUtente(id);
    }

    @PostMapping("/post")
    public UtenteDTO addUtente(@RequestBody @Validated UtenteDTO utenteDTO) {
        return utenteService.save(utenteDTO);
    }

    //PAGE
    @GetMapping("/page")
    public PageDTO<UtenteDTO> getPage(@PageableDefault(size = 5) Pageable pageable) {
        return utenteService.getPage(pageable);
    }

    //FILTER
    @GetMapping("/filter")
    public List<UtenteDTO> filter(UtenteSearchParams utenteSearchParams) {
        return utenteService.searchUtenteBySpecification(utenteSearchParams);
    }
}