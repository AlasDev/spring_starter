package it.objectmethod.spring_starter.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "This field is required")
    @Size(min = 7, max = 7, message = "Targa must be 7 characters long.")
    @Pattern(regexp = "^[A-Za-z]{2}\\d{3}[A-Za-z]{2}$", message = "Targa must follow pattern 'ab123cd'")
    private String numTarga;

    @NotBlank(message = "This field is required")
    private String modello;

    @NotBlank(message = "This field is required")
    private String colore;

    private List<Long> autisti;
}
