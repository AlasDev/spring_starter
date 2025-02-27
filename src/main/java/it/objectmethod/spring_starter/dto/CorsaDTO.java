package it.objectmethod.spring_starter.dto;

import it.objectmethod.spring_starter.annotation.DecimalValidation;
import it.objectmethod.spring_starter.annotation.TimeComparatorValidation;
import jakarta.validation.constraints.NotBlank;
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
@TimeComparatorValidation(maxHours = 48)
public class CorsaDTO {

    private Long id;

    @NotBlank(message = "'statoCorsa' cant be empty")
    private String statoCorsa;

    @NotNull(message = "insert distance travelled")
    @DecimalValidation(max = 3)
    private Double distanzaPercorsa;

    @NotNull(message = "insert the price")
    @DecimalValidation(max = 2)
    private Double costoCorsa;

    @NotBlank(message = "'indirizzoInizio' cant be empty")
    private String indirizzoInizio;

    @NotBlank(message = "'indirizzoFine' cant be empty")
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
