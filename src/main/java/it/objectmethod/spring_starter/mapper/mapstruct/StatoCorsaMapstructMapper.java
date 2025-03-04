package it.objectmethod.spring_starter.mapper.mapstruct;

import it.objectmethod.spring_starter.dto.PageDTO;
import it.objectmethod.spring_starter.dto.StatoCorsaDTO;
import it.objectmethod.spring_starter.entity.Corsa;
import it.objectmethod.spring_starter.entity.StatoCorsa;
import it.objectmethod.spring_starter.util.BasicMethodMapping;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring",
        builder = @Builder(disableBuilder = true))
public interface StatoCorsaMapstructMapper extends BasicMethodMapping<StatoCorsaDTO, StatoCorsa> {

    @Mapping(target = "corse", source = "corse", qualifiedByName = "toStatoCorsaId")
    StatoCorsaDTO mapToDto(StatoCorsa statoCorsa);

    @Mapping(target = "corse", source = "corse", qualifiedByName = "toStatoCorsa")
    StatoCorsa mapToEntity(StatoCorsaDTO statoCorsaDTO);


    @Named("toStatoCorsaId")
    default List<Long> toStatoCorsaId(List<Corsa> corse) {
        return corse.stream().map(Corsa::getId).toList();
    }

    @Named("toStatoCorsa")
    default List<Corsa> toStatoCorsa(List<Long> ids) {
        return ids.stream().map(id -> Corsa.builder().id(id).build()).toList();
    }

    PageDTO<StatoCorsaDTO> mapToPageDTO(Page<StatoCorsa> page);
}
