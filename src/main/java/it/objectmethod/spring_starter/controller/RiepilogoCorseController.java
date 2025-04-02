package it.objectmethod.spring_starter.controller;

import it.objectmethod.spring_starter.dto.PageDTO;
import it.objectmethod.spring_starter.dto.RiepilogoCorseDTO;
import it.objectmethod.spring_starter.service.RiepilogoCorseService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/riepilogo")
public class RiepilogoCorseController {

    private final RiepilogoCorseService riepilogoCorseService;

    public RiepilogoCorseController(RiepilogoCorseService riepilogoCorseService) {
        this.riepilogoCorseService = riepilogoCorseService;
    }

    @GetMapping("/get/all")
    public List<RiepilogoCorseDTO> getAll() {
        return riepilogoCorseService.getAll();
    }

    @GetMapping("/get/{id}")
    public RiepilogoCorseDTO getCorsa(@PathVariable long id) {
        return riepilogoCorseService.getById(id);
    }

    @GetMapping("/count")
    public Long count() {
        return riepilogoCorseService.count();
    }

    @GetMapping("/page")
    public PageDTO<RiepilogoCorseDTO> getAll(@PageableDefault(size = 5) Pageable pageable) {
        return riepilogoCorseService.getAll(pageable);
    }
}
