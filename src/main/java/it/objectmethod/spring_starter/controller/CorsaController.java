package it.objectmethod.spring_starter.controller;

import it.objectmethod.spring_starter.dto.CorsaDTO;
import it.objectmethod.spring_starter.dto.PageDTO;
import it.objectmethod.spring_starter.filter.CorsaSearchParams;
import it.objectmethod.spring_starter.service.CorsaService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/corsa")
public class CorsaController {

    private final CorsaService corsaService;

    public CorsaController(CorsaService corsaService) {
        this.corsaService = corsaService;
    }

    @GetMapping("/get/all")
    public List<CorsaDTO> getAll() {
        return corsaService.getAll();
    }

    @GetMapping("/get/{id}")
    public CorsaDTO getCorsa(@PathVariable @Validated long id) {
        return corsaService.getCorsa(id);
    }

    @PutMapping("/update")
    public CorsaDTO updateCliente(@RequestBody @Validated CorsaDTO corsaDTO) {
        return corsaService.setCorsa(corsaDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCorsa(@PathVariable @Validated long id) {
        corsaService.deleteCorsa(id);
    }

    @PostMapping("/post")
    public CorsaDTO addCorsa(@RequestBody @Validated CorsaDTO corsaDTO) {
        return corsaService.save(corsaDTO);
    }

    //PAGE
    @GetMapping("/page")
    public PageDTO<CorsaDTO> getPage(@PageableDefault(size = 5) Pageable pageable) {
        return corsaService.getPage(pageable);
    }

    //FILTER
    @GetMapping("/filter")
    public List<CorsaDTO> filter( CorsaSearchParams corsaSearchParams) {
        return corsaService.searchCorsaBySpecification(corsaSearchParams);
    }
}