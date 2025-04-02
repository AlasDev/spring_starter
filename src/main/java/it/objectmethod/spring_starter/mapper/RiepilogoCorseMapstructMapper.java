package it.objectmethod.spring_starter.mapper;

import it.objectmethod.spring_starter.dto.RiepilogoCorseDTO;
import it.objectmethod.spring_starter.entity.RiepilogoCorse;
import it.objectmethod.spring_starter.util.BasicMethodMapping;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = {ClienteMapstructMapper.class,
                AutistaMapstructMapper.class,
                VeicoloMapstructMapper.class},
        builder = @Builder(disableBuilder = true))
public interface RiepilogoCorseMapstructMapper extends BasicMethodMapping<RiepilogoCorseDTO, RiepilogoCorse> {

    RiepilogoCorseDTO mapToDto(RiepilogoCorse entity);

    RiepilogoCorse mapToEntity(RiepilogoCorseDTO dto);

    List<RiepilogoCorse> mapToEntities(List<RiepilogoCorseDTO> dtos);

    List<RiepilogoCorseDTO> mapToDtos(List<RiepilogoCorse> entities);
}
