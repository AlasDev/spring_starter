package it.objectmethod.spring_starter.mapper;

import it.objectmethod.spring_starter.dto.AutistaDTO;
import it.objectmethod.spring_starter.entity.Autista;
import it.objectmethod.spring_starter.util.BasicMethodMapping;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

//@Mapper(componentModel = "spring",
//        builder = @Builder(disableBuilder = true))
public interface AutistaMapstructMapper extends BasicMethodMapping<AutistaDTO, Autista> {

//    @Override
//    @Mapping(target = "veicolo", source = "veicolo")
//    AutistaDTO mapToDto(Autista autista);
//
//    @Override
//    @Mapping(target = "veicolo", source = "veicolo")
//    @Mapping(target = "corse", ignore = true)
//    Autista mapToEntity(AutistaDTO autistaDTO);
}
