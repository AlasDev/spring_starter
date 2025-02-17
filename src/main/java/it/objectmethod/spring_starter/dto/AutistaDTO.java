package it.objectmethod.spring_starter.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AutistaDTO {

    private Long id;

    @NotNull
    private String nome;

    @NotNull
    private String cognome;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNascita;

    @NotNull
    private String codFiscale;

    //Foreign Key
    private Long veicolo;

    //Foreign Key
    private List<CorsaDTO> corse;

}
