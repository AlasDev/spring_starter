package it.objectmethod.spring_starter.mapper.mapstruct;

import it.objectmethod.spring_starter.dto.CorsaDTO;
import it.objectmethod.spring_starter.dto.PageDTO;
import it.objectmethod.spring_starter.entity.Corsa;
import it.objectmethod.spring_starter.util.BasicMethodMapping;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring",
        uses = {ClienteMapstructMapper.class,
                AutistaMapstructMapper.class},
        builder = @Builder(disableBuilder = true))
public interface CorsaMapstructMapper extends BasicMethodMapping<CorsaDTO, Corsa> {

    @Mapping(target = "cliente", source = "cliente.id")
    @Mapping(target = "autista", source = "autista.id")
    CorsaDTO mapToDto(Corsa corsa);

    @Mapping(target = "cliente.id", source = "cliente")
    @Mapping(target = "autista.id", source = "autista")
    Corsa mapToEntity(CorsaDTO corsaDTO);

    PageDTO<CorsaDTO> mapToPageDTO(Page<Corsa> page);
}
