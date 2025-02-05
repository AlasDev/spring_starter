package it.objectmethod.spring_starter.mapper;

import it.objectmethod.spring_starter.dto.VeicoloDTO;
import it.objectmethod.spring_starter.entity.Veicolo;
import it.objectmethod.spring_starter.util.BasicMethodMapping;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VeicoloMapper implements BasicMethodMapping<VeicoloDTO, Veicolo> {
    @Override
    public VeicoloDTO mapToDto(Veicolo veicolo) {
        VeicoloDTO veicoloDTO = new VeicoloDTO();
        veicoloDTO.setId(veicolo.getId());
        veicoloDTO.setNumTarga(veicolo.getNumTarga());
        veicoloDTO.setModello(veicolo.getModello());
        veicoloDTO.setColore(veicolo.getColore());
        return veicoloDTO;
    }

    @Override
    public Veicolo mapToEntity(VeicoloDTO veicoloDTO) {
        Veicolo veicolo = new Veicolo();
        veicolo.setId(veicoloDTO.getId());
        veicolo.setNumTarga(veicoloDTO.getNumTarga());
        veicolo.setModello(veicoloDTO.getModello());
        veicolo.setColore(veicoloDTO.getColore());
        return veicolo;
    }

    @Override
    public List<Veicolo> mapToEntities(List<VeicoloDTO> veicoloDTOS) {
        List<Veicolo> veicolos = new ArrayList<Veicolo>();
        for (VeicoloDTO veicoloDTO : veicoloDTOS) {
            veicolos.add(mapToEntity(veicoloDTO));
        }
        return veicolos;
    }

    @Override
    public List<VeicoloDTO> mapToDtos(List<Veicolo> veicolos) {
        List<VeicoloDTO> veicoloDTOS = new ArrayList<>();
        for (Veicolo veicolo : veicolos) {
            veicoloDTOS.add(mapToDto(veicolo));
        }
        return veicoloDTOS;
    }
}
