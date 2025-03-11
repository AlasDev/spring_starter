package it.objectmethod.spring_starter.mapper;

import it.objectmethod.spring_starter.dto.PageDTO;
import it.objectmethod.spring_starter.dto.VeicoloDTO;
import it.objectmethod.spring_starter.entity.Autista;
import it.objectmethod.spring_starter.entity.Veicolo;
import it.objectmethod.spring_starter.util.BasicMethodMapping;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring",
        uses = {AutistaMapstructMapper.class},
        builder = @Builder(disableBuilder = true))
public interface VeicoloMapstructMapper extends BasicMethodMapping<VeicoloDTO, Veicolo> {

    @Mapping(target = "autisti", source = "autisti", qualifiedByName = "toAutistiId")
    VeicoloDTO mapToDto(Veicolo veicolo);

    @Mapping(target = "autisti", source = "autisti", qualifiedByName = "toAutisti")
    Veicolo mapToEntity(VeicoloDTO veicoloDTO);

    @Named("toAutistiId")
    default Long toVeicoliId(Autista autista) {
        return autista.getId();
    }

    @Named("toAutisti")
    default Autista toCorseId(Long id) {
        return Autista.builder().id(id).build();
    }

    PageDTO<VeicoloDTO> mapToPageDTO(Page<Veicolo> page);
}
