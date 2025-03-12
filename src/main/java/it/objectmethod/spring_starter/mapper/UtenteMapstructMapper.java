package it.objectmethod.spring_starter.mapper;

import it.objectmethod.spring_starter.dto.PageDTO;
import it.objectmethod.spring_starter.dto.UtenteDTO;
import it.objectmethod.spring_starter.entity.Utente;
import it.objectmethod.spring_starter.util.BasicMethodMapping;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring",
        builder = @Builder(disableBuilder = true))
public interface UtenteMapstructMapper extends BasicMethodMapping<UtenteDTO, Utente> {

    UtenteDTO mapToDto(Utente utente);

    Utente mapToEntity(UtenteDTO utenteDTO);

    PageDTO<UtenteDTO> mapToPageDTO(Page<Utente> page);
}
