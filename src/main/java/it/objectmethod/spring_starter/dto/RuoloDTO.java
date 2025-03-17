package it.objectmethod.spring_starter.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RuoloDTO {

    private Long id;

    @NotBlank(message = "Role name is required")
    private String nome;

    private List<UtenteDTO> utenti;

}
