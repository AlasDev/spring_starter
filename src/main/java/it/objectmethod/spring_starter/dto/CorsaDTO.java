package it.objectmethod.spring_starter.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CorsaDTO {

    private Long id;

    @NotNull
    private String statoCorsa;

    @NotNull
    private Double distanzaPercorsa;

    @NotNull
    private Double costoCorsa;

    @NotNull
    private String indirizzoInizio;

    @NotNull
    private String indirizzoFine;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataPrenotazione;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataInizio;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataFine;

    //Foreign Key
    private Long autista;

    //Foreign Key
    private Long cliente;
}
