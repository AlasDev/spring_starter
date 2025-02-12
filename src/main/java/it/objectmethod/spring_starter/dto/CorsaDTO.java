package it.objectmethod.spring_starter.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CorsaDTO {

    @NotNull
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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private OffsetDateTime dataPrenotazione;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private OffsetDateTime dataInizio;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private OffsetDateTime dataFine;

    //Foreign Key
    @NotNull
    private Long cliente_ID;

    //Foreign Key
    @NotNull
    private Long autista_ID;
}
