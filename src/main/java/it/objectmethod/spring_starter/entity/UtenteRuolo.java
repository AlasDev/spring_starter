package it.objectmethod.spring_starter.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "utente_ruolo")
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(UtenteRuoloId.class)
public class UtenteRuolo {
    @Id
    @ManyToOne
    @JoinColumn(name = "utente_ID")
    private Utente utente;

    @Id
    @ManyToOne
    @JoinColumn(name = "ruolo_ID")
    private Ruolo ruolo;
}