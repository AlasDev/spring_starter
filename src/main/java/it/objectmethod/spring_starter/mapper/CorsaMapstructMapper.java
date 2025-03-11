package it.objectmethod.spring_starter.mapper;

import it.objectmethod.spring_starter.dto.CorsaDTO;
import it.objectmethod.spring_starter.dto.PageDTO;
import it.objectmethod.spring_starter.entity.Corsa;
import it.objectmethod.spring_starter.entity.StatoCorsa;
import it.objectmethod.spring_starter.util.BasicMethodMapping;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring",
        uses = {ClienteMapstructMapper.class,
                AutistaMapstructMapper.class,
                StatoCorsaMapstructMapper.class},
        builder = @Builder(disableBuilder = true))
public interface CorsaMapstructMapper extends BasicMethodMapping<CorsaDTO, Corsa> {

    @Mapping(target = "cliente", source = "cliente.id")
    @Mapping(target = "autista", source = "autista.id")
    @Mapping(target = "statoCorsa", source = "statoCorsa", qualifiedByName = "toStatoCorsaId")
    CorsaDTO mapToDto(Corsa corsa);

    @Mapping(target = "cliente.id", source = "cliente")
    @Mapping(target = "autista.id", source = "autista")
    @Mapping(target = "statoCorsa", source = "statoCorsa", qualifiedByName = "toStatoCorsa")
    Corsa mapToEntity(CorsaDTO corsaDTO);

    PageDTO<CorsaDTO> mapToPageDTO(Page<Corsa> page);

    @Named("toStatoCorsaId")
    default Long toStatoCorsaId(StatoCorsa statoCorsa) {
        return statoCorsa.getId();
    }

    @Named("toStatoCorsa")
    default StatoCorsa toStatoCorsa(Long id) {
        return StatoCorsa.builder().id(id).build();
    }
}
