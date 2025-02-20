package it.objectmethod.spring_starter.controller;

import it.objectmethod.spring_starter.dto.PageDTO;
import it.objectmethod.spring_starter.dto.VeicoloDTO;
import it.objectmethod.spring_starter.filter.VeicoloSearchParams;
import it.objectmethod.spring_starter.service.VeicoloService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veicolo")
public class VeicoloController {

    private final VeicoloService veicoloService;

    public VeicoloController(VeicoloService veicoloService) {
        this.veicoloService = veicoloService;
    }

    @GetMapping("get/all")
    public List<VeicoloDTO> getAll() {
        return veicoloService.getAll();
    }

    @GetMapping("/get/{id}")
    public VeicoloDTO getVeicolo(@PathVariable @Validated Long id) {
        return veicoloService.getVeicolo(id);
    }

    @PutMapping("/update")
    public VeicoloDTO updateVeicolo(@RequestBody @Validated VeicoloDTO veicoloDTO) {
        return veicoloService.setVeicolo(veicoloDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteVeicolo(@PathVariable @Validated Long id) {
         veicoloService.deleteVeicolo(id);
    }

    @PostMapping("/post")
    public VeicoloDTO addVeicolo(@RequestBody @Validated VeicoloDTO veicoloDTO) {
        return veicoloService.save(veicoloDTO);
    }

    //PAGE
    @GetMapping("/page")
    public PageDTO<VeicoloDTO> getPage(@PageableDefault(size = 5) Pageable pageable) {
        return veicoloService.getPage(pageable);
    }

    //FILTER
    @GetMapping("/filter")
    public List<VeicoloDTO> filter(VeicoloSearchParams veicoloSearchParams) {
        return veicoloService.searchVeicoloBySpecification(veicoloSearchParams);
    }
}