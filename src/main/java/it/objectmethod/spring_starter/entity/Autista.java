package it.objectmethod.spring_starter.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "autista")
public class Autista {

    @Column(name = "autista_ID")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cognome")
    private String cognome;

    @Column(name = "data_nascita")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNascita;

    @Column(name = "cod_fiscale")
    private String codFiscale;

    //Foreign Keys
    @ManyToOne
    @JoinColumn(name = "veicolo_ID")
    private Veicolo veicolo;

    @OneToOne
    @JoinColumn(name = "utente_ID")
    private Utente utente;

    @OneToMany(mappedBy = "autista", cascade = CascadeType.ALL)
    private List<Corsa> corse;
}
