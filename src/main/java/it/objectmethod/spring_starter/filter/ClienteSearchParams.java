package it.objectmethod.spring_starter.filter;

import it.objectmethod.spring_starter.entity.Cliente;
import it.objectmethod.spring_starter.entity.Utente;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

@Data
public class ClienteSearchParams {
    //presi dall'entità
    private String nome;
    private String cognome;
    private LocalDate dataNascita;
    private String codFiscale;
    private LocalDate dataIscrizione;
    private Utente utente;

    @SafeVarargs
    private Specification<Cliente> combineSpecifications(Specification<Cliente>... specs) {
        Specification<Cliente> result = null;
        for (Specification<Cliente> spec : specs) {
            if (spec != null) {
                result = (result == null) ? spec : result.or(spec);
            }
        }
        return result;
    }

    public Specification<Cliente> toSpecification() {
        return Specification.<Cliente>where(null)
                //nome
                .and(combineSpecifications(
                        equalNomeSpecification(nome),
                        inNomeSpecification(nome)
                ))
                //cognome
                .and(combineSpecifications(
                        equalCognomeSpecification(cognome),
                        inCognomeSpecification(cognome)
                ))
                //dataNascita
                .and(combineSpecifications(
                        equalDataNascitaSpecification(dataNascita),
                        inDataNascitaSpecification(dataNascita)
                ))
                //codFiscale
                .and(combineSpecifications(
                        equalCodFiscaleSpecification(codFiscale),
                        inCodFiscaleSpecification(codFiscale)
                ))
                //dataIscrizione
                .and(combineSpecifications(
                        equalDataIscrizioneSpecification(dataIscrizione),
                        inDataIscrizioneSpecification(dataIscrizione)
                ))
                //utenteId
                .and(combineSpecifications(
                        equalUtenteIdSpecification(utente),
                        inUtenteIdSpecification(utente)
                ));
    }

    //nome
    private Specification<Cliente> equalNomeSpecification(String nome) {
        return (Root<Cliente> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (nome == null || nome.isBlank()) {
                return null;
            }
            return criteriaBuilder.equal(root.get("nome"), nome);
        };
    }
    private Specification<Cliente> inNomeSpecification(String nome) {
        return (Root<Cliente> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (nome == null || nome.isBlank()) {
                return null;
            }
            return criteriaBuilder.in(root.get("nome"));
        };
    }

    //cognome
    private Specification<Cliente> equalCognomeSpecification(String cognome) {
        return (Root<Cliente> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (cognome == null || cognome.isBlank()) {
                return null;
            }
            return criteriaBuilder.equal(root.get("cognome"), cognome);
        };
    }
    private Specification<Cliente> inCognomeSpecification(String cognome) {
        return (Root<Cliente> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (cognome == null || cognome.isBlank()) {
                return null;
            }
            return criteriaBuilder.in(root.get("cognome"));
        };
    }

    //dataNascita
    private Specification<Cliente> equalDataNascitaSpecification(LocalDate dataNascita) {
        return (root, query, criteriaBuilder) -> {
            if (dataNascita == null) {
                return null;
            }
            if (dataNascita.toString().isBlank()) {
                return null;
            }
            return criteriaBuilder.equal(root.get("dataNascita"), dataNascita);
        };
    }
    private Specification<Cliente> inDataNascitaSpecification(LocalDate dataNascita) {
        return (root, query, criteriaBuilder) -> {
            if (dataNascita == null) {
                return null;
            }
            if (dataNascita.toString().isBlank()) {
                return null;
            }
            return criteriaBuilder.in(root.get("dataNascita"));
        };
    }

    //codFiscale
    private Specification<Cliente> equalCodFiscaleSpecification(String codFiscale) {
        return (Root<Cliente> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (codFiscale == null || codFiscale.isBlank()) {
                return null;
            }

            return criteriaBuilder.equal(root.get("codFiscale"), codFiscale);
        };
    }
    private Specification<Cliente> inCodFiscaleSpecification(String codFiscale) {
        return (Root<Cliente> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (codFiscale == null || codFiscale.isBlank()) {
                return null;
            }

            return criteriaBuilder.in(root.get("codFiscale"));
        };
    }

    //dataIscrizione
    private Specification<Cliente> equalDataIscrizioneSpecification(LocalDate dataIscrizione) {
        return (root, query, criteriaBuilder) -> {
            if (dataIscrizione == null) {
                return null;
            }
            if (dataIscrizione.toString().isBlank()) {
                return null;
            }
            return criteriaBuilder.equal(root.get("dataIscrizione"), dataIscrizione);
        };
    }
    private Specification<Cliente> inDataIscrizioneSpecification(LocalDate dataIscrizione) {
        return (root, query, criteriaBuilder) -> {
            if (dataIscrizione == null) {
                return null;
            }
            if (dataIscrizione.toString().isBlank()) {
                return null;
            }
            return criteriaBuilder.in(root.get("dataIscrizione"));
        };
    }

    //utenteId
    private Specification<Cliente> equalUtenteIdSpecification(Utente utente) {
        return (root, query, criteriaBuilder) -> {
            if (utente == null) {
                return null;
            }
            Long utenteId = utente.getId();
            if (utenteId == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("utente").get("id"), utenteId);
        };
    }
    private Specification<Cliente> inUtenteIdSpecification(Utente utente) {
        return (root, query, criteriaBuilder) -> {
            if (utente == null) {
                return null;
            }
            Long utenteId = utente.getId();
            if (utenteId == null) {
                return null;
            }
            return criteriaBuilder.in(root.get("utente").get("id"));
        };
    }
}
