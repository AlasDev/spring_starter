package it.objectmethod.spring_starter.mapper;

import it.objectmethod.spring_starter.dto.AutistaDTO;
import it.objectmethod.spring_starter.dto.PageDTO;
import it.objectmethod.spring_starter.entity.Autista;
import it.objectmethod.spring_starter.entity.Corsa;
import it.objectmethod.spring_starter.entity.Utente;
import it.objectmethod.spring_starter.util.BasicMethodMapping;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring",
        uses = {VeicoloMapstructMapper.class,
                CorsaMapstructMapper.class},
        builder = @Builder(disableBuilder = true))
public interface AutistaMapstructMapper extends BasicMethodMapping<AutistaDTO, Autista> {

    @Mapping(target = "veicolo", source = "veicolo.id")
    @Mapping(target = "utente", source = "utente.id")
    @Mapping(target = "corse", source = "corse", qualifiedByName = "toCorseId")
    AutistaDTO mapToDto(Autista autista);

    @Mapping(target = "veicolo.id", source = "veicolo")
    @Mapping(target = "utente.id", source = "utente")
    @Mapping(target = "corse", source = "corse", qualifiedByName = "toCorse")
    Autista mapToEntity(AutistaDTO autistaDTO);

    PageDTO<AutistaDTO> mapToPageDTO(Page<Autista> page);

    @Named("toCorseId")
    default Long toCorseId(Corsa corsa) {
        return corsa.getId();
    }

    @Named("toCorse")
    default Corsa toCorseId(Long id) {
        return Corsa.builder()
                .id(id)
                .build();
    }

    @Named("toUtenteId")
    default Long toUtenteId(Utente utente) {
        return utente.getId();
    }
}
