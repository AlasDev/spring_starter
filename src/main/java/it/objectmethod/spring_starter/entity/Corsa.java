package it.objectmethod.spring_starter.entity;

import it.objectmethod.spring_starter.util.StatoCorsa;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "corsa")
public class Corsa {
    @Column(name = "corsa_ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "stato_corsa")
    private StatoCorsa statoCorsa;

    @Column(name = "distanza_percorsa")
    private Double distanzaPercorsa;

    @Column(name = "costo_corsa")
    private Double costoCorsa;

    @Column(name = "indirizzo_inizio")
    private String indirizzoInizio;

    @Column(name = "indirizzo_fine")
    private String indirizzoFine;

    @Column(name = "data_prenotazione")
    private LocalDateTime dataPrenotazione;

    @Column(name = "data_inizio")
    private LocalDateTime dataInizio;

    @Column(name = "data_fine")
    private LocalDateTime dataFine;

    @ManyToOne
    @JoinColumn(name = "autista_ID")
    private Autista autista;

    //Foreign Key
    @ManyToOne
    @JoinColumn(name = "cliente_ID")
    private Cliente cliente;
}