package it.objectmethod.spring_starter.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UtenteRuoloId implements Serializable {
    private Long utente;
    private Long ruolo;
}
