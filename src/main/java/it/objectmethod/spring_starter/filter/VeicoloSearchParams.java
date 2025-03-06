package it.objectmethod.spring_starter.filter;

import it.objectmethod.spring_starter.entity.Veicolo;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

@Data
public class VeicoloSearchParams {
    //presi dall'entità
    private String numTarga;
    private String modello;
    private String colore;

    @SafeVarargs
    private Specification<Veicolo> combineSpecifications(Specification<Veicolo>... specs) {
        Specification<Veicolo> result = null;
        for (Specification<Veicolo> spec : specs) {
            if (spec != null) {
                result = (result == null) ? spec : result.or(spec);
            }
        }
        return result;
    }

    public Specification<Veicolo> toSpecification() {
        return Specification.<Veicolo>where(null)
                //numTarga
                .and(combineSpecifications(
                        equalNumTargaSpecification(numTarga),
                        inNumTargaSpecification(numTarga)
                ))
                //modello
                .and(combineSpecifications(
                        equalModelloSpecification(modello),
                        inModelloSpecification(modello)
                ))
                //colore
                .and(combineSpecifications(
                        equalColoreSpecification(colore),
                        inColoreSpecification(colore)
                ));
    }

    //numTarga
    private Specification<Veicolo> equalNumTargaSpecification(String numTarga) {
        return (Root<Veicolo> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (numTarga == null || numTarga.isBlank()) {
                return null;
            }

            return criteriaBuilder.equal(root.get("numTarga"), numTarga);
        };
    }

    private Specification<Veicolo> inNumTargaSpecification(String numTarga) {
        return (root, query, criteriaBuilder) -> {
            if (numTarga == null || numTarga.isBlank()) {
                return null;
            }
            return criteriaBuilder.in(root.get("numTarga"));
        };
    }

    //modello
    private Specification<Veicolo> equalModelloSpecification(String modello) {
        return (root, query, criteriaBuilder) -> {
            if (modello == null || modello.isBlank()) {
                return null;
            }
            return criteriaBuilder.equal(root.get("modello"), modello);
        };
    }

    private Specification<Veicolo> inModelloSpecification(String modello) {
        return (root, query, criteriaBuilder) -> {
            if (modello == null || modello.isBlank()) {
                return null;
            }
            return criteriaBuilder.in(root.get("modello"));
        };
    }

    //colore
    private Specification<Veicolo> equalColoreSpecification(String colore) {
        return (root, query, criteriaBuilder) -> {
            if (colore == null || colore.isBlank()) {
                return null;
            }
            return criteriaBuilder.equal(root.get("colore"), colore);
        };
    }

    private Specification<Veicolo> inColoreSpecification(String colore) {
        return (root, query, criteriaBuilder) -> {
            if (colore == null || colore.isBlank()) {
                return null;
            }
            return criteriaBuilder.in(root.get("colore"));
        };
    }
}
