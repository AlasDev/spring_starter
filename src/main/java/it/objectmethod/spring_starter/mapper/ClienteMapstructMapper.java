package it.objectmethod.spring_starter.mapper;

import it.objectmethod.spring_starter.dto.ClienteDTO;
import it.objectmethod.spring_starter.dto.PageDTO;
import it.objectmethod.spring_starter.entity.Cliente;
import it.objectmethod.spring_starter.entity.Corsa;
import it.objectmethod.spring_starter.entity.Utente;
import it.objectmethod.spring_starter.util.BasicMethodMapping;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring",
        uses = {CorsaMapstructMapper.class},
        builder = @Builder(disableBuilder = true))
public interface ClienteMapstructMapper extends BasicMethodMapping<ClienteDTO, Cliente> {

    @Mapping(target = "corse", source = "corse", qualifiedByName = "toCorseId")
    @Mapping(target = "utente", source = "utente", qualifiedByName = "toUtenteId")
    ClienteDTO mapToDto(Cliente cliente);

    @Mapping(target = "corse", source = "corse", qualifiedByName = "toCorse")
    @Mapping(target = "utente", source = "utente", qualifiedByName = "toUtente")
    Cliente mapToEntity(ClienteDTO clienteDTO);

    PageDTO<ClienteDTO> mapToPageDTO(Page<Cliente> page);

    @Named("toCorseId")
    default Long toCorseId(Corsa corsa) {
        return corsa.getId();
    }

    @Named("toCorse")
    default Corsa toCorseId(Long id) {
        return Corsa.builder().id(id).build();
    }

    @Named("toUtenteId")
    default Long toUtenteId(Utente utente) {
        return utente.getId();
    }

    @Named("toUtente")
    default Utente toUtente(Long id) {
        return Utente.builder().id(id).build();
    }
}
