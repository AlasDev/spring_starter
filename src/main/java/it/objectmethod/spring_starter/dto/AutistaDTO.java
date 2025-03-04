package it.objectmethod.spring_starter.dto;

import jakarta.validation.constraints.*;
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

    @NotBlank(message = "This field is required")
    private String nome;

    @NotBlank(message = "This field is required")
    private String cognome;

    @Past(message = "date of birth cant be a date that has yet to come")
    @NotNull(message = "This field is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNascita;

    @NotBlank(message = "This field is required")
    @Max(value = 16, message = "codFiscale is too long. Must be max 16 characters.")
    @Min(value = 11, message = "codFiscale is too short. Must be at least 11 characters.")
    @Pattern(regexp = "^[A-Za-z]{6}\\d{2}[A-Za-z]\\d{2}[a-zA-Z_0-9]{4}[A-Za-z]$")
    private String codFiscale;

    //Foreign Key
    private Long veicolo;

    //Foreign Key
    private Long utente;

    //Foreign Key (reference)
    private List<Long> corse;

}
