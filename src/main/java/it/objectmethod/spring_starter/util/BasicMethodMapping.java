package it.objectmethod.spring_starter.util;

import it.objectmethod.spring_starter.dto.PageDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BasicMethodMapping<DTO, ENTITY> {
    /**
     * gets an Entity, output: DTO
     */
    DTO mapToDto(ENTITY entity);

    /**
     * gets a DTO, output: Entity
     */
    ENTITY mapToEntity(DTO dto);

    /**
     * gets a list of DTO, output: list of Entities
     */
    List<ENTITY> mapToEntities(List<DTO> dtos);

    /**
     * gets a list of Entities, output: list of DTOs
     */
    List<DTO> mapToDtos(List<ENTITY> entities);

    /**
     * gets a page of an entity, output: page info of said entity as a pageDTO
     */
    PageDTO<DTO> mapToPageDTO(Page<ENTITY> page);
}