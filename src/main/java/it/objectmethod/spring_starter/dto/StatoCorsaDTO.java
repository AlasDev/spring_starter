package it.objectmethod.spring_starter.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StatoCorsaDTO {

    private Long id;

    @NotBlank(message = "This field is required")
    private String descrizione;

    private List<Long> corse;
}
