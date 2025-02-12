package it.objectmethod.spring_starter.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "veicolo")
public class Veicolo {
    @Column(name = "veicolo_ID")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "num_targa")
    private String numTarga;

    @Column(name = "modello")
    private String modello;

    @Column(name = "colore")
    private String colore;

    //Foreign Key
    @OneToMany(mappedBy = "veicolo")
    private List<Autista> autisti;
}
