package it.objectmethod.spring_starter.filter;

import it.objectmethod.spring_starter.entity.Ruolo;
import it.objectmethod.spring_starter.entity.Utente;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

@Data
public class UtenteSearchParams {
    //presi dall'entità
    private String email;
    private Ruolo ruolo;

    @SafeVarargs
    private Specification<Utente> combineSpecifications(Specification<Utente>... specs) {
        Specification<Utente> result = null;
        for (Specification<Utente> spec : specs) {
            if (spec != null) {
                result = (result == null) ? spec : result.or(spec);
            }
        }
        return result;
    }

    public Specification<Utente> toSpecification() {
        return Specification.<Utente>where(null)
                //email
                .and(combineSpecifications(
                        equalEmailSpecification(email),
                        inEmailSpecification(email)
                ))
                //ruolo
                .and(combineSpecifications(
                        equalRuoloSpecification(ruolo),
                        inRuoloSpecification(ruolo)
                ));
    }

    //email
    private Specification<Utente> equalEmailSpecification(String email) {
        return (Root<Utente> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (email == null || email.isBlank()) {
                return null;
            }

            return criteriaBuilder.equal(root.get("email"), email);
        };
    }

    private Specification<Utente> inEmailSpecification(String email) {
        return (root, query, criteriaBuilder) -> {
            if (email == null || email.isBlank()) {
                return null;
            }
            return criteriaBuilder.in(root.get("email"));
        };
    }

    //ruolo
    private Specification<Utente> equalRuoloSpecification(Ruolo ruolo) {
        return (root, query, criteriaBuilder) -> {
            if (ruolo == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("ruolo"), ruolo);
        };
    }

    private Specification<Utente> inRuoloSpecification(Ruolo ruolo) {
        return (root, query, criteriaBuilder) -> {
            if (ruolo == null) {
                return null;
            }
            return criteriaBuilder.in(root.get("ruolo"));
        };
    }
}
