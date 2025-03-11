package it.objectmethod.spring_starter.mapper;

import it.objectmethod.spring_starter.dto.PageDTO;
import it.objectmethod.spring_starter.dto.RuoloDTO;
import it.objectmethod.spring_starter.entity.Ruolo;
import it.objectmethod.spring_starter.entity.Utente;
import it.objectmethod.spring_starter.util.BasicMethodMapping;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = {UtenteMapstructMapper.class},
        builder = @Builder(disableBuilder = true))
public interface RuoloMapstructMapper extends BasicMethodMapping<RuoloDTO, Ruolo> {

    @Mapping(target = "utenti", source = "utenti", qualifiedByName = "toUtentiId")
    RuoloDTO mapToDto(Ruolo ruolo);

    @Mapping(target = "utenti", source = "utenti", qualifiedByName = "toUtenti")
    Ruolo mapToEntity(RuoloDTO ruoloDTO);

    @Named("toUtentiId")
    default List<Long> toUtentiId(List<Utente> utenti) {
        return utenti.stream().map(Utente::getId).toList();
    }

    @Named("toUtenti")
    default List<Utente> toUtenti(List<Long> ids) {
        return ids.stream().map(id -> Utente.builder().id(id).build()).toList();
    }

    PageDTO<RuoloDTO> mapToPageDTO(Page<Ruolo> page);
}
