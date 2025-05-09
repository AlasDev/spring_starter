package it.objectmethod.spring_starter.dto;

import it.objectmethod.spring_starter.util.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UtenteDTO {

    private Long id;

    @NotBlank(message = "This field is required")
    @Email(message = "You have to provide a valid email address.")
    private String email;

    @NotBlank(message = "This field is required")
    @Size(min = 6, message = "Password should be at least 6 characters long for security reasons.")
    private String password;

    @Enumerated(EnumType.STRING) //checks if inserted value is in Role enum
    private List<Role> ruoli;
}
