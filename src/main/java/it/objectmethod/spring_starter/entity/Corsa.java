package it.objectmethod.spring_starter.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "corsa")
public class Corsa {
    @Column(name = "corsa_ID")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "stato_corsa")
    private String statoCorsa;

    @Column(name = "distanza_percorsa")
    private Double distanzaPercorsa;

    @Column(name = "costo_corsa")
    private Double costoCorsa;

    @Column(name = "indirizzo_inizio")
    private String indirizzoInizio;

    @Column(name = "indirizzo_fine")
    private String indirizzoFine;

    @Column(name = "data_prenotazione")
    private OffsetDateTime dataPrenotazione;

    @Column(name = "data_inizio")
    private OffsetDateTime dataInizio;

    @Column(name = "data_fine")
    private OffsetDateTime dataFine;

    @ManyToOne
    @JoinColumn(name = "autista_ID", nullable = false)
    private Autista autista;

    //Foreign Keys
    @ManyToOne
    @JoinColumn(name = "cliente_ID", nullable = false)
    private Cliente cliente;
}