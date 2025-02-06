package it.objectmethod.spring_starter.mapper;

import it.objectmethod.spring_starter.dto.AutistaDTO;
import it.objectmethod.spring_starter.entity.Autista;
import it.objectmethod.spring_starter.entity.Veicolo;
import it.objectmethod.spring_starter.util.BasicMethodMapping;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AutistaMapper implements BasicMethodMapping<AutistaDTO, Autista> {

    @Override
    public AutistaDTO mapToDto(Autista autista) {
        AutistaDTO autistaTDO = new AutistaDTO();
        autistaTDO.setId(autista.getId());
        autistaTDO.setNome(autista.getNome());
        autistaTDO.setCognome(autista.getCognome());
        autistaTDO.setDataNascita(autista.getDataNascita());
        autistaTDO.setCodFiscale(autista.getCodFiscale());
        autistaTDO.setVeicolo_ID(autista.getVeicolo().getId());
        return autistaTDO;
    }

    @Override
    public Autista mapToEntity(AutistaDTO autistaDTO) {
        Autista autista = new Autista();
        autista.setId(autistaDTO.getId());
        autista.setNome(autistaDTO.getNome());
        autista.setCognome(autistaDTO.getCognome());
        autista.setDataNascita(autistaDTO.getDataNascita());
        autista.setCodFiscale(autistaDTO.getCodFiscale());

        Veicolo veicolo = new Veicolo();
        veicolo.setId(autistaDTO.getVeicolo_ID());
        autista.setVeicolo(veicolo);
        return autista;
    }

    @Override
    public List<Autista> mapToEntities(List<AutistaDTO> autistaDTOS) {
        List<Autista> autistas = new ArrayList<>(autistaDTOS.size());
        for (AutistaDTO autistaDTO : autistaDTOS) {
            autistas.add(mapToEntity(autistaDTO));
        }
        //return autistaDTOS.stream().map(this::mapToEntity).toList();
        return autistas;
    }

    @Override
    public List<AutistaDTO> mapToDtos(List<Autista> autistas) {
        List<AutistaDTO> autistaDTOS = new ArrayList<AutistaDTO>();
        for (Autista autista : autistas) {
            autistaDTOS.add(mapToDto(autista));
        }
        //return autistas.stream().map(this::mapToDto).toList();
        return autistaDTOS;
    }
}
