package it.objectmethod.spring_starter.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VeicoloDTO {

    private Long id;

    @NotBlank(message = "'numTarga' cant be empty")
    @Size(min = 2, max = 16, message = "Targa must be 7 characters long.")
    @Pattern(regexp = "^[A-Za-z]{2}\\d{3}[A-Za-z]{2}$", message = "Targa must follow pattern 'ab123cd'")
    private String numTarga;

    @NotBlank(message = "'modello' cant be empty")
    private String modello;

    @NotBlank(message = "'colore' cant be empty")
    private String colore;

    private List<Long> autisti;
}
