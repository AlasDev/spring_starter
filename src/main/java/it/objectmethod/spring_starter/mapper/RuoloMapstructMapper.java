package it.objectmethod.spring_starter.mapper;

import it.objectmethod.spring_starter.dto.RuoloDTO;
import it.objectmethod.spring_starter.entity.Ruolo;
import it.objectmethod.spring_starter.util.BasicMethodMapping;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        uses = {UtenteMapstructMapper.class},
        builder = @Builder(disableBuilder = true))
public interface RuoloMapstructMapper extends BasicMethodMapping<RuoloDTO, Ruolo> {

    RuoloDTO mapToDto(Ruolo ruolo);

    Ruolo mapToEntity(RuoloDTO ruoloDTO);
}
