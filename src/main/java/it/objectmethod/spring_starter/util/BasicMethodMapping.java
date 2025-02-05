package it.objectmethod.spring_starter.util;

import java.util.List;

public interface BasicMethodMapping<DTO, ENTITY> {
    //gets an Entity, output: DTO
    DTO mapToDto(ENTITY entity);

    //gets a DTO, output: Entity
    ENTITY mapToEntity(DTO dto);

    //gets a list of DTO, output: list of Entities
    List<ENTITY> mapToEntities(List<DTO> dtos);

    //gets a list of Entities, output: list of DTOs
    List<DTO> mapToDtos(List<ENTITY> entities);
}