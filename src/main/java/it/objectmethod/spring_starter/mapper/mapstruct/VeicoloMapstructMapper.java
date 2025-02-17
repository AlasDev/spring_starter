package it.objectmethod.spring_starter.mapper.mapstruct;

import it.objectmethod.spring_starter.dto.PageDTO;
import it.objectmethod.spring_starter.dto.VeicoloDTO;
import it.objectmethod.spring_starter.entity.Veicolo;
import it.objectmethod.spring_starter.util.BasicMethodMapping;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring",
        uses = {AutistaMapstructMapper.class},
        builder = @Builder(disableBuilder = true))
public interface VeicoloMapstructMapper extends BasicMethodMapping<VeicoloDTO, Veicolo> {

    @Override
    VeicoloDTO mapToDto(Veicolo veicolo);

    @Override
    Veicolo mapToEntity(VeicoloDTO veicoloDTO);

    PageDTO<VeicoloDTO> mapToPageDTO(Page<Veicolo> page);
}
