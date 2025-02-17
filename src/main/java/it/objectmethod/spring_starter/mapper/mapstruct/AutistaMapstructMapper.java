package it.objectmethod.spring_starter.mapper.mapstruct;

import it.objectmethod.spring_starter.dto.AutistaDTO;
import it.objectmethod.spring_starter.dto.PageDTO;
import it.objectmethod.spring_starter.entity.Autista;
import it.objectmethod.spring_starter.util.BasicMethodMapping;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring",
        uses = {VeicoloMapstructMapper.class,
                CorsaMapstructMapper.class},
        builder = @Builder(disableBuilder = true))
public interface AutistaMapstructMapper extends BasicMethodMapping<AutistaDTO, Autista> {

    @Override
    @Mapping(target = "veicolo", source = "veicolo.id")
    AutistaDTO mapToDto(Autista autista);

    @Override
    @Mapping(target = "veicolo.id", source = "veicolo")
    @Mapping(target = "corse", source = "corse")
    Autista mapToEntity(AutistaDTO autistaDTO);

    PageDTO<AutistaDTO> mapToPageDTO(Page<Autista> page);
}
