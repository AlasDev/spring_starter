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
public class ClienteDTO {

    private Long id;

    @NotBlank(message = "'nome' cant be empty")
    private String nome;

    @NotBlank(message = "'cognome' cant be empty")
    private String cognome;

    @Past(message = "date of birth cant be a date that has yet to come")
    @NotNull(message = "Insert date of birth")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNascita;

    @NotBlank(message = "Username cant be empty")
    @Max(value = 16, message = "codFiscale is too long. Must be max 16 characters.")
    @Min(value = 11, message = "codFiscale is too short. Must be at least 11 characters.")
    @Pattern(regexp = "^[A-Za-z]{6}\\d{2}[A-Za-z]\\d{2}[a-zA-Z_0-9]{4}[A-Za-z]$")
    private String codFiscale;

    @NotNull(message = "Insert date of registration")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataIscrizione;

    //Foreign key
    private List<Long> corse;
}
