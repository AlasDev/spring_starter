package it.objectmethod.spring_starter.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "ruolo")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ruolo {

    @Column(name = "ruolo_ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ruolo_nome")
    private String nome;

    @OneToMany(mappedBy = "ruolo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UtenteRuolo> utenteRuoli;
}
