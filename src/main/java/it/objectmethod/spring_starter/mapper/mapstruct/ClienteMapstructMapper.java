package it.objectmethod.spring_starter.mapper.mapstruct;

import it.objectmethod.spring_starter.dto.ClienteDTO;
import it.objectmethod.spring_starter.dto.PageDTO;
import it.objectmethod.spring_starter.entity.Cliente;
import it.objectmethod.spring_starter.util.BasicMethodMapping;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring",
        uses = {CorsaMapstructMapper.class},
        builder = @Builder(disableBuilder = true))
public interface ClienteMapstructMapper extends BasicMethodMapping<ClienteDTO, Cliente> {

    ClienteDTO mapToDto(Cliente cliente);

    Cliente mapToEntity(ClienteDTO clienteDTO);

    PageDTO<ClienteDTO> mapToPageDTO(Page<Cliente> page);
}
