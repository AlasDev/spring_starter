package it.objectmethod.spring_starter.dto.filter;

import it.objectmethod.spring_starter.entity.Autista;
import it.objectmethod.spring_starter.entity.Cliente;
import it.objectmethod.spring_starter.entity.Corsa;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

@Data
public class CorsaSearchParams {
    //presi dalla entity
    private String statoCorsa;
    private Double distanzaPercorsa;
    private Double costoCorsa;
    private String indirizzoInizio;
    private String indirizzoFine;

    //in fuso orario default: UTC+0 "Zulu time"
    private LocalDateTime dataPrenotazione;
    private LocalDateTime dataInizio;
    private LocalDateTime dataFine;

    private Cliente cliente;
    private Autista autista;

    public Specification<Corsa> toSpecification() {
        return Specification.<Corsa>where(null)
                //statoCorsa
                .or(equalStatoCorsaSpecification(statoCorsa))
                .or(likeStatoCorsaSpecification(statoCorsa))
                .or(inStatoCorsaSpecification(statoCorsa))
                //distanzaPercorsa
                .or(equalDistanzaPercorsaSpecification(distanzaPercorsa))
                .or(inDistanzaPercorsaSpecification(distanzaPercorsa))
                //costoCorsa
                .or(equalCostoCorsaSpecification(costoCorsa))
                .or(inCostoCorsaSpecification(costoCorsa))
                //indirizzoInizio
                .or(equalIndirizzoinizioSpecification(indirizzoInizio))
                .or(likeIndirizzoinizioSpecification(indirizzoInizio))
                .or(inIndirizzoinizioSpecification(indirizzoInizio))
                //indirizzoFine
                .or(equalIndirizzoFineSpecification(indirizzoFine))
                .or(likeIndirizzoFineSpecification(indirizzoFine))
                .or(inIndirizzoFineSpecification(indirizzoFine))
                //dataPrenotazione
                .or(equalDataPrenotazioneSpecification(dataPrenotazione))
                .or(inDataPrenotazioneSpecification(dataPrenotazione))
                //dataInizio
                .or(equalDataInizioSpecification(dataInizio))
                .or(inDataInizioSpecification(dataInizio))
                //dataFine
                .or(equalDataFineSpecification(dataFine))
                .or(inDataFineSpecification(dataFine))
                //cliente
                .or(equalClienteSpecification(cliente))
                .or(inClienteSpecification(cliente))
                //autista
                .or(equalAutistaSpecification(autista))
                .or(inAutistaSpecification(autista))
                ;
    }

    //statoCorsa
    private Specification<Corsa> equalStatoCorsaSpecification(String statoCorsa) {
        return (Root<Corsa> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (statoCorsa == null || statoCorsa.isBlank()) {
                return null;
            }
            return criteriaBuilder.equal(root.get("statoCorsa"), statoCorsa);
        };
    }
    private Specification<Corsa> likeStatoCorsaSpecification(String statoCorsa) {
        return (Root<Corsa> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (statoCorsa == null || statoCorsa.isBlank()) {
                return null;
            }
            return criteriaBuilder.like(root.get("statoCorsa"), statoCorsa);
        };
    }
    private Specification<Corsa> inStatoCorsaSpecification(String statoCorsa) {
        return (Root<Corsa> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (statoCorsa == null || statoCorsa.isBlank()) {
                return null;
            }
            return criteriaBuilder.in(root.get("statoCorsa"));
        };
    }

    //distanzaPercorsa
    private Specification<Corsa> equalDistanzaPercorsaSpecification(Double distanzaPercorsa) {
        return (Root<Corsa> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (distanzaPercorsa == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("distanzaPercorsa"), distanzaPercorsa);
        };
    }
    private Specification<Corsa> inDistanzaPercorsaSpecification(Double distanzaPercorsa) {
        return (Root<Corsa> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (distanzaPercorsa == null) {
                return null;
            }
            return criteriaBuilder.in(root.get("distanzaPercorsa"));
        };
    }

    //costoCorsa
    private Specification<Corsa> equalCostoCorsaSpecification(Double costoCorsa) {
        return (Root<Corsa> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (costoCorsa == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("costoCorsa"), costoCorsa);
        };
    }
    private Specification<Corsa> inCostoCorsaSpecification(Double costoCorsa) {
        return (Root<Corsa> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (costoCorsa == null) {
                return null;
            }
            return criteriaBuilder.in(root.get("costoCorsa"));
        };
    }

    //indirizzoInizio
    private Specification<Corsa> equalIndirizzoinizioSpecification(String indirizzoInizio) {
        return (Root<Corsa> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (indirizzoInizio == null || indirizzoInizio.isBlank()) {
                return null;
            }
            return criteriaBuilder.equal(root.get("indirizzoInizio"), indirizzoInizio);
        };
    }
    private Specification<Corsa> likeIndirizzoinizioSpecification(String indirizzoInizio) {
        return (Root<Corsa> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (indirizzoInizio == null || indirizzoInizio.isBlank()) {
                return null;
            }
            return criteriaBuilder.like(root.get("indirizzoInizio"), indirizzoInizio);
        };
    }
    private Specification<Corsa> inIndirizzoinizioSpecification(String indirizzoInizio) {
        return (Root<Corsa> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (indirizzoInizio == null || indirizzoInizio.isBlank()) {
                return null;
            }
            return criteriaBuilder.in(root.get("indirizzoInizio"));
        };
    }

    //indirizzoFine
    private Specification<Corsa> equalIndirizzoFineSpecification(String indirizzoFine) {
        return (Root<Corsa> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (indirizzoFine == null || indirizzoFine.isBlank()) {
                return null;
            }
            return criteriaBuilder.equal(root.get("indirizzoFine"), indirizzoFine);
        };
    }
    private Specification<Corsa> likeIndirizzoFineSpecification(String indirizzoFine) {
        return (Root<Corsa> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (indirizzoFine == null || indirizzoFine.isBlank()) {
                return null;
            }
            return criteriaBuilder.like(root.get("indirizzoFine"), indirizzoFine);
        };
    }
    private Specification<Corsa> inIndirizzoFineSpecification(String indirizzoFine) {
        return (Root<Corsa> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (indirizzoFine == null || indirizzoFine.isBlank()) {
                return null;
            }
            return criteriaBuilder.in(root.get("indirizzoFine"));
        };
    }

    //dataPrenotazione
    private Specification<Corsa> equalDataPrenotazioneSpecification(LocalDateTime dataPrenotazione) {
        return (Root<Corsa> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (dataPrenotazione == null) {
                return null;
            }
            if (dataPrenotazione.toString().isBlank()) {
                return null;
            }
            return criteriaBuilder.equal(root.get("dataPrenotazione"), dataPrenotazione);
        };
    }
    private Specification<Corsa> inDataPrenotazioneSpecification(LocalDateTime dataPrenotazione) {
        return (Root<Corsa> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (dataPrenotazione == null) {
                return null;
            }
            if (dataPrenotazione.toString().isBlank()) {
                return null;
            }
            return criteriaBuilder.in(root.get("dataPrenotazione"));
        };
    }

    //dataInizio
    private Specification<Corsa> equalDataInizioSpecification(LocalDateTime dataInizio) {
        return (Root<Corsa> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (dataInizio == null) {
                return null;
            }
            if (dataInizio.toString().isBlank()) {
                return null;
            }
            return criteriaBuilder.equal(root.get("dataInizio"), dataInizio);
        };
    }
    private Specification<Corsa> inDataInizioSpecification(LocalDateTime dataInizio) {
        return (Root<Corsa> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (dataInizio == null) {
                return null;
            }
            if (dataInizio.toString().isBlank()) {
                return null;
            }
            return criteriaBuilder.in(root.get("dataInizio"));
        };
    }

    //dataFine
    private Specification<Corsa> equalDataFineSpecification(LocalDateTime dataFine) {
        return (Root<Corsa> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (dataFine == null) {
                return null;
            }
            if (dataFine.toString().isBlank()) {
                return null;
            }
            return criteriaBuilder.equal(root.get("dataFine"), dataFine);
        };
    }
    private Specification<Corsa> inDataFineSpecification(LocalDateTime dataFine) {
        return (Root<Corsa> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (dataFine == null) {
                return null;
            }
            if (dataFine.toString().isBlank()) {
                return null;
            }
            return criteriaBuilder.in(root.get("dataFine"));
        };
    }

    //cliente
    private Specification<Corsa> equalClienteSpecification(Cliente cliente) {
        return (Root<Corsa> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (cliente == null) {
                return null;
            }
            Long clienteId = cliente.getId();
            if (clienteId == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("cliente").get("id"), clienteId);
        };
    }
    private Specification<Corsa> inClienteSpecification(Cliente cliente) {
        return (Root<Corsa> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (cliente == null) {
                return null;
            }
            Long clienteId = cliente.getId();
            if (clienteId == null) {
                return null;
            }
            return criteriaBuilder.in(root.get("cliente").get("id"));
        };
    }

    //autista
    private Specification<Corsa> equalAutistaSpecification(Autista autista) {
        return (Root<Corsa> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (autista == null) {
                return null;
            }
            Long autistaId = autista.getId();
            if (autistaId == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("autista").get("id"), autistaId);
        };
    }
    private Specification<Corsa> inAutistaSpecification(Autista autista) {
        return (Root<Corsa> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (autista == null) {
                return null;
            }
            Long autistaId = autista.getId();
            if (autistaId == null) {
                return null;
            }
            return criteriaBuilder.in(root.get("autista").get("id"));
        };
    }
}