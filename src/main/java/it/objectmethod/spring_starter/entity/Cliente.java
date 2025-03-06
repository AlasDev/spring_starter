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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cliente")
public class Cliente {

    @Column(name = "cliente_ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(name = "data_iscrizione")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataIscrizione;

    @OneToOne
    @JoinColumn(name = "utente_ID")
    private Utente utente;

    @OneToMany(mappedBy = "cliente")
    private List<Corsa> corse;
}
