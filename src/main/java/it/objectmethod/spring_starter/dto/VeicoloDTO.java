package it.objectmethod.spring_starter.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VeicoloDTO {

    private Long id;

    @NotNull
    private String numTarga;

    @NotNull
    private String modello;

    @NotNull
    private String colore;

    @NotNull
    private List<AutistaDTO> autisti;
}
