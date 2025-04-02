package it.objectmethod.spring_starter.entity;

import it.objectmethod.spring_starter.util.StatoCorsa;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Immutable;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "riepilogo_corse")
@Immutable
public class RiepilogoCorse {

    @Column(name = "id_corsa")
    @Id
    private Long id;

    @Column(name = "id_cliente")
    private String idCliente;

    @Column(name = "nome_cliente")
    private String nomeCliente;

    @Column(name = "cognome_cliente")
    private String cognomeCliente;

    @Column(name = "id_autista")
    private String idAutista;

    @Column(name = "nome_autista")
    private String nomeAutista;

    @Column(name = "cognome_autista")
    private String cognomeAutista;

    @Column(name = "id_veicolo")
    private Long idVeicolo;

    @Column(name = "targa_veicolo")
    private String targaVeicolo;

    @Column(name = "colore_veicolo")
    private String coloreVeicolo;

    @Column(name = "modello_veicolo")
    private String modelloVeicolo;

    @Enumerated(EnumType.STRING)
    @Column(name = "stato")
    private StatoCorsa statoCorsa;

    @Column(name = "luogo_partenza")
    private String indirizzoInizio;

    @Column(name = "luogo_destinazione")
    private String indirizzoFine;

    @Column(name = "data_prenotazione")
    private LocalDateTime dataPrenotazione;

    @Column(name = "inizio")
    private LocalDateTime dataInizio;

    @Column(name = "fine")
    private LocalDateTime dataFine;

    @Column(name = "distanza")
    private Double distanzaPercorsa;

    @Column(name = "costo")
    private Double costoCorsa;
}
