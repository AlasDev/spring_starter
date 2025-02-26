package it.objectmethod.spring_starter.mapper.mapstruct;

import it.objectmethod.spring_starter.dto.UtenteDTO;
import it.objectmethod.spring_starter.entity.Utente;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        builder = @Builder(disableBuilder = true))
public interface UtenteMapstructMapper {

    UtenteDTO mapToDto(Utente utente);

    Utente mapToEntity(UtenteDTO utenteDTO);
}
