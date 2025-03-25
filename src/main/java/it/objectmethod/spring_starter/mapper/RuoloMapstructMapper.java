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

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring",
        uses = {UtenteMapstructMapper.class},
        builder = @Builder(disableBuilder = true))
public interface RuoloMapstructMapper extends BasicMethodMapping<RuoloDTO, Ruolo> {

    @Mapping(target = "utenti", source = "utenteRuoli", qualifiedByName = "toUtentiDTOs")
    RuoloDTO mapToDto(Ruolo ruolo);

    @Mapping(target = "utenteRuoli", source = "utenti", qualifiedByName = "toUtenteRuoli")
    Ruolo mapToEntity(RuoloDTO ruoloDTO);

    @Named("toUtentiDTOs")
    default List<UtenteDTO> utenteRuoliToUtentiDTOs(List<UtenteRuolo> utenteRuoli) {
        if (utenteRuoli == null) {
            return null;
        }

        return utenteRuoli.stream()
                .map(utenteRuolo -> UtenteDTO.builder().id(utenteRuolo.getUtente().getId()).build())
                .toList();
    }

    @Named("toUtenteRuoli")
    default List<UtenteRuolo> utentiDTOsToUtenteRuoli(List<UtenteDTO> utenteDTOs) {
        if (utenteDTOs == null) {
            return null;
        }
        List<UtenteRuolo> utenteRuoli = new ArrayList<>();
        for (UtenteDTO utenteDTO : utenteDTOs) {
            if (utenteDTO != null) {
                Utente utente = new Utente();
                utente.setId(utenteDTO.getId());

                UtenteRuolo utenteRuolo = new UtenteRuolo(utente, null);
                utenteRuoli.add(utenteRuolo);
            }
        }
        return utenteRuoli;
    }
}