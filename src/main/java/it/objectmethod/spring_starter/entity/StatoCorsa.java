package it.objectmethod.spring_starter.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "stato_corsa")
public class StatoCorsa {

    @Column(name = "id")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descrizione")
    private String descrizione;

    @OneToMany(mappedBy = "statoCorsa")
    private List<Corsa> corse;

}