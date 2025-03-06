package it.objectmethod.spring_starter.filter;

import it.objectmethod.spring_starter.entity.Autista;
import it.objectmethod.spring_starter.entity.Cliente;
import it.objectmethod.spring_starter.entity.Corsa;
import it.objectmethod.spring_starter.entity.StatoCorsa;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

@Data
public class CorsaSearchParams {
    //presi dalla entity
    private StatoCorsa statoCorsa;
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

    @SafeVarargs
    private Specification<Corsa> combineSpecifications(Specification<Corsa>... specs) {
        Specification<Corsa> result = null;
        for (Specification<Corsa> spec : specs) {
            if (spec != null) {
                result = (result == null) ? spec : result.or(spec);
            }
        }
        return result;
    }

    public Specification<Corsa> toSpecification() {
        return Specification.<Corsa>where(null)
                //statoCorsa
                .and(combineSpecifications(
                        equalStatoCorsaIdSpecification(statoCorsa),
                        inStatoCorsaIdSpecification(statoCorsa)
                ))
                //distanzaPercorsa
                .and(combineSpecifications(
                        equalDistanzaPercorsaSpecification(distanzaPercorsa),
                        inDistanzaPercorsaSpecification(distanzaPercorsa)
                ))
                //costoCorsa
                .and(combineSpecifications(
                        equalCostoCorsaSpecification(costoCorsa),
                        inCostoCorsaSpecification(costoCorsa)
                ))
                //indirizzoInizio
                .and(combineSpecifications(
                        equalIndirizzoInizioSpecification(indirizzoInizio),
                        inIndirizzoInizioSpecification(indirizzoInizio)
                ))
                //indirizzoFine
                .and(combineSpecifications(
                        equalIndirizzoFineSpecification(indirizzoFine),
                        inIndirizzoFineSpecification(indirizzoFine)
                ))
                //dataPrenotazione
                .and(combineSpecifications(
                        equalDataPrenotazioneSpecification(dataPrenotazione),
                        inDataPrenotazioneSpecification(dataPrenotazione)
                ))
                //dataInizio
                .and(combineSpecifications(
                        equalDataInizioSpecification(dataInizio),
                        inDataInizioSpecification(dataInizio)
                ))
                //dataFine
                .and(combineSpecifications(
                        equalDataFineSpecification(dataFine),
                        inDataFineSpecification(dataFine)
                ))
                //cliente
                .and(combineSpecifications(
                        equalClienteSpecification(cliente),
                        inClienteSpecification(cliente)
                ))
                //autista
                .and(combineSpecifications(
                        equalAutistaSpecification(autista),
                        inAutistaSpecification(autista)
                ));
    }

    //statoCorsa
    private Specification<Corsa> equalStatoCorsaIdSpecification(StatoCorsa statoCorsa) {
        return (Root<Corsa> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (statoCorsa == null) {
                return null;
            }
            Long statoCorsaId = statoCorsa.getId();
            if (statoCorsaId == null) {
                return null;
            }

            return criteriaBuilder.equal(root.get("statoCorsa"), statoCorsa);
        };
    }

    private Specification<Corsa> inStatoCorsaIdSpecification(StatoCorsa statoCorsa) {
        return (root, query, criteriaBuilder) -> {
            if (statoCorsa == null) {
                return null;
            }
            Long statoCorsaId = statoCorsa.getId();
            if (statoCorsaId == null) {
                return null;
            }
            return criteriaBuilder.in(root.get("statoCorsa").get("id"));
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
    private Specification<Corsa> equalIndirizzoInizioSpecification(String indirizzoInizio) {
        return (Root<Corsa> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (indirizzoInizio == null || indirizzoInizio.isBlank()) {
                return null;
            }
            return criteriaBuilder.equal(root.get("indirizzoInizio"), indirizzoInizio);
        };
    }

    private Specification<Corsa> inIndirizzoInizioSpecification(String indirizzoInizio) {
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