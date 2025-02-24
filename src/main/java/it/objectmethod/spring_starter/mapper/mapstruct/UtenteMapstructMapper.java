package it.objectmethod.spring_starter.mapper.mapstruct;

import it.objectmethod.spring_starter.dto.UtenteDTO;
import it.objectmethod.spring_starter.entity.Utente;
import org.mapstruct.Mapper;

@Mapper
public interface UtenteMapstructMapper {

    UtenteDTO mapToDto(Utente utente);

    Utente mapToEntity(UtenteDTO utenteDTO);
}
