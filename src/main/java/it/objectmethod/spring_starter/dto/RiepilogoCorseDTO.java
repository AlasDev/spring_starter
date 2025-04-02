package it.objectmethod.spring_starter.dto;

import it.objectmethod.spring_starter.util.StatoCorsa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RiepilogoCorseDTO {

    private Long id;

    private String idCliente;

    private String nomeCliente;

    private String cognomeCliente;

    private String idAutista;

    private String nomeAutista;

    private String cognomeAutista;

    private Long idVeicolo;

    private String targaVeicolo;

    private String coloreVeicolo;

    private String modelloVeicolo;

    private StatoCorsa statoCorsa;

    private String indirizzoInizio;

    private String indirizzoFine;

    private LocalDateTime dataPrenotazione;

    private LocalDateTime dataInizio;

    private LocalDateTime dataFine;

    private Double distanzaPercorsa;

    private Double costoCorsa;
}
