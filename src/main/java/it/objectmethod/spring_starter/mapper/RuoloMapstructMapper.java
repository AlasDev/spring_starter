package it.objectmethod.spring_starter.mapper;

import it.objectmethod.spring_starter.dto.RuoloDTO;
import it.objectmethod.spring_starter.dto.UtenteDTO;
import it.objectmethod.spring_starter.entity.Ruolo;
import it.objectmethod.spring_starter.entity.Utente;
import it.objectmethod.spring_starter.entity.UtenteRuolo;
import it.objectmethod.spring_starter.util.BasicMethodMapping;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring",
        uses = {UtenteMapstructMapper.class},
        builder = @Builder(disableBuilder = true))
public interface RuoloMapstructMapper extends BasicMethodMapping<RuoloDTO, Ruolo> {

    UtenteMapstructMapper utenteMapper = Mappers.getMapper(UtenteMapstructMapper.class);

    @Mapping(target = "utenti", source = "utenteRuoli", qualifiedByName = "toUtenti")
    RuoloDTO mapToDto(Ruolo ruolo);

    @Mapping(target = "utenteRuoli", source = "utenti", qualifiedByName = "toUtenteRuoli")
    Ruolo mapToEntity(RuoloDTO ruoloDTO);

    @Named("toUtenti")
    default List<UtenteDTO> toUtenteDto(List<UtenteRuolo> utenteRuoli) {
        List<UtenteDTO> utenteDTOS = new ArrayList<>();
        for (UtenteRuolo utenteRuolo : utenteRuoli) {
            if (utenteRuolo != null) {
                UtenteDTO utenteDTO = utenteMapper.mapToDto(utenteRuolo.getUtente());
                utenteDTOS.add(utenteDTO);
            }
        }
        return utenteDTOS;
    }

    @Named("toUtenteRuoli")
    default List<UtenteRuolo> toUtenteRuoli(List<UtenteDTO> utenteDTOS) {
        List<UtenteRuolo> utenteRuoli = new ArrayList<>();
        for (UtenteDTO utenteDTO : utenteDTOS) {
            Utente utente = utenteMapper.mapToEntity(utenteDTO);
            if (utenteDTO != null) {
                utenteRuoli.add(new UtenteRuolo(utente, null));  // Il ruolo verrà settato successivamente
            }
        }
        return utenteRuoli;
    }
}
