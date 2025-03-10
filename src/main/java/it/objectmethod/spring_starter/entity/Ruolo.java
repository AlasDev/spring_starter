package it.objectmethod.spring_starter.entity;

import it.objectmethod.spring_starter.util.Role;
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
@Table(name = "ruolo")
public class Ruolo {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING) //it will use the string value of the enum, not the ordinal value
    @Column(name = "descrizione")
    private Role descrizione;

    @OneToMany(mappedBy = "ruolo")
    private List<Utente> utenti;
}
