package it.objectmethod.spring_starter.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "utente")
public class Utente {

    @Column(name = "utente_ID")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "ruolo_id")
    private Ruolo ruolo;

}
