package it.objectmethod.spring_starter.dto;

import jakarta.validation.constraints.NotBlank;
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

    @NotBlank
    private String numTarga;

    @NotBlank
    private String modello;

    @NotBlank
    private String colore;

    private List<Long> autisti;
}
