package it.objectmethod.spring_starter.mapper;

import it.objectmethod.spring_starter.dto.CorsaDTO;
import it.objectmethod.spring_starter.entity.Autista;
import it.objectmethod.spring_starter.entity.Cliente;
import it.objectmethod.spring_starter.entity.Corsa;
import it.objectmethod.spring_starter.util.BasicMethodMapping;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CorsaMapper implements BasicMethodMapping<CorsaDTO, Corsa> {

    @Override
    public CorsaDTO mapToDto(Corsa corsa) {
        CorsaDTO corsaDTO = new CorsaDTO();
        corsaDTO.setId(corsa.getId());
        corsaDTO.setStatoCorsa(corsa.getStatoCorsa());
        corsaDTO.setDistanzaPercorsa(corsa.getDistanzaPercorsa());
        corsaDTO.setCostoCorsa(corsa.getCostoCorsa());
        corsaDTO.setIndirizzoInizio(corsa.getIndirizzoInizio());
        corsaDTO.setIndirizzoFine(corsa.getIndirizzoFine());
        corsaDTO.setDataPrenotazione(corsa.getDataPrenotazione());
        corsaDTO.setDataInizio(corsa.getDataInizio());
        corsaDTO.setDataFine(corsa.getDataFine());
        corsaDTO.setCliente_ID(corsa.getCliente().getId());
        corsaDTO.setAutista_ID(corsa.getAutista().getId());
        return corsaDTO;
    }

    @Override
    public Corsa mapToEntity(CorsaDTO corsaDTO) {
        Corsa corsa = new Corsa();
        corsa.setId(corsaDTO.getId());
        corsa.setStatoCorsa(corsaDTO.getStatoCorsa());
        corsa.setDistanzaPercorsa(corsaDTO.getDistanzaPercorsa());
        corsa.setCostoCorsa(corsaDTO.getCostoCorsa());
        corsa.setIndirizzoInizio(corsaDTO.getIndirizzoInizio());
        corsa.setIndirizzoFine(corsaDTO.getIndirizzoFine());
        corsa.setDataPrenotazione(corsaDTO.getDataPrenotazione());
        corsa.setDataInizio(corsaDTO.getDataInizio());
        corsa.setDataFine(corsaDTO.getDataFine());

        Cliente cliente = new Cliente();
        cliente.setId(corsaDTO.getCliente_ID());
        corsa.setCliente(cliente);

        Autista autista = new Autista();
        autista.setId(corsaDTO.getAutista_ID());
        corsa.setAutista(autista);
        return corsa;
    }

    @Override
    public List<Corsa> mapToEntities(List<CorsaDTO> corsaDTOS) {
        List<Corsa> corsaList = new ArrayList<>();
        for (CorsaDTO corsaDTO : corsaDTOS) {
            corsaList.add(mapToEntity(corsaDTO));
        }
        return corsaList;
    }

    @Override
    public List<CorsaDTO> mapToDtos(List<Corsa> corsas) {
        List<CorsaDTO> corsaDTOS = new ArrayList<>();
        for (Corsa corsa : corsas) {
            corsaDTOS.add(mapToDto(corsa));
        }
        return corsaDTOS;
    }
}
