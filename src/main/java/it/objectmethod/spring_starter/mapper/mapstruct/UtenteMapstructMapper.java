package it.objectmethod.spring_starter.mapper.mapstruct;

import it.objectmethod.spring_starter.dto.PageDTO;
import it.objectmethod.spring_starter.dto.UtenteDTO;
import it.objectmethod.spring_starter.entity.Ruolo;
import it.objectmethod.spring_starter.entity.Utente;
import it.objectmethod.spring_starter.util.BasicMethodMapping;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring",
        builder = @Builder(disableBuilder = true))
public interface UtenteMapstructMapper extends BasicMethodMapping<UtenteDTO, Utente> {

    @Mapping(target = "ruolo", source = "ruolo", qualifiedByName = "toRuoloId")
    UtenteDTO mapToDto(Utente utente);

    @Mapping(target = "ruolo", source = "ruolo", qualifiedByName = "toRuolo")
    Utente mapToEntity(UtenteDTO utenteDTO);

    @Named("toRuoloId")
    default Long toRuoloId(Ruolo ruolo) {
        return ruolo.getId();
    }

    @Named("toRuolo")
    default Ruolo toRuolo(Long id) {
        return Ruolo.builder().id(id).build();
    }

    PageDTO<UtenteDTO> mapToPageDTO(Page<Utente> page);
}
