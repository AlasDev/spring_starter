package it.objectmethod.spring_starter.mapper;

import it.objectmethod.spring_starter.dto.PageDTO;
import it.objectmethod.spring_starter.dto.UtenteDTO;
import it.objectmethod.spring_starter.entity.Ruolo;
import it.objectmethod.spring_starter.entity.Utente;
import it.objectmethod.spring_starter.entity.UtenteRuolo;
import it.objectmethod.spring_starter.repository.RuoloRepository;
import it.objectmethod.spring_starter.service.RuoloService;
import it.objectmethod.spring_starter.util.BasicMethodMapping;
import it.objectmethod.spring_starter.util.Role;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring",
        uses = {RuoloMapstructMapper.class, RuoloService.class},
        builder = @Builder(disableBuilder = true))
public interface UtenteMapstructMapper extends BasicMethodMapping<UtenteDTO, Utente> {

    @Mapping(target = "ruoli", source = "utenteRuoli", qualifiedByName = "toRuoli")
    UtenteDTO mapToDto(Utente utente);

    @Mapping(target = "utenteRuoli", source = "ruoli", qualifiedByName = "toUtenteRuoli")
    Utente mapToEntity(UtenteDTO utenteDTO);

    PageDTO<UtenteDTO> mapToPageDTO(Page<Utente> page);

    @Named("toRuoli")
    default List<Role> toRoles(List<UtenteRuolo> utenteRuoli) {
        List<Role> roles = new ArrayList<>();
        for (UtenteRuolo ruolo : utenteRuoli) {
            roles.add(Role.valueOf(ruolo.getRuolo().getNome()));
        }
        return roles;
    }

    @Named("toUtenteRuoli")
    default List<UtenteRuolo> toRuoli(List<Role> roles) {
        List<UtenteRuolo> utenteRuoli = new ArrayList<>();
        for (Role role : roles) {
            Ruolo ruolo = RuoloRepository.findByNome(role.toString());
            if (ruolo != null) {
                utenteRuoli.add(new UtenteRuolo(null, ruolo));  // L'utente verrà settato successivamente
            }
        }
        return utenteRuoli;
    }
}