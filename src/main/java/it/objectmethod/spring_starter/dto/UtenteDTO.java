package it.objectmethod.spring_starter.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UtenteDTO {

    private Long id;

    @NotBlank(message = "'email' cant be empty")
    @Email(message = "You have to provide a valid email address.")
    private String email;

    @NotBlank(message = "'password' cant be empty")
    private String password;
}
