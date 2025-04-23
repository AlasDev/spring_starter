package it.objectmethod.spring_starter.filter;

import it.objectmethod.spring_starter.entity.Autista;
import it.objectmethod.spring_starter.entity.Utente;
import it.objectmethod.spring_starter.entity.Veicolo;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class AutistaSearchParams {
    //presi dall'entità
    private String nome;
    private String cognome;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNascita;
    private String codFiscale;
    private Veicolo veicolo;
    private Utente utente;

    @SafeVarargs
    private Specification<Autista> combineSpecifications(Specification<Autista>... specs) {
        Specification<Autista> result = null;
        for (Specification<Autista> spec : specs) {
            if (spec != null) {
                result = (result == null) ? spec : result.or(spec);
            }
        }
        return result;
    }

    public Specification<Autista> toSpecification() {
        return Specification.<Autista>where(null)
                //nome
                .and(combineSpecifications(
                        equalNomeSpecification(nome),
                        likeNomeSpecification(nome)
                ))
                //cognome
                .and(combineSpecifications(
                        equalCognomeSpecification(cognome),
                        likeCognomeSpecification(cognome)
                ))
                //dataNascita
                .and(combineSpecifications(
                        equalDataNascitaSpecification(dataNascita)
                ))
                //codFiscale
                .and(combineSpecifications(
                        equalCodFiscaleSpecification(codFiscale),
                        likeCodFiscaleSpecification(codFiscale)
                ))
                //veicoloId
                .and(combineSpecifications(
                        equalVeicoloIdSpecification(veicolo)
                ))
                //utenteId
                .and(combineSpecifications(
                        equalUtenteIdSpecification(utente)
                ));
    }

    //nome
    private Specification<Autista> equalNomeSpecification(String nome) {
        return (root, query, criteriaBuilder) -> {
            if (nome == null || nome.isBlank()) {
                return null;
            }
            return criteriaBuilder.equal(root.get("nome"), nome);
        };
    }

    private Specification<Autista> likeNomeSpecification(String nome) {
        return (root, query, criteriaBuilder) -> {
            if (nome == null || nome.isBlank()) {
                return null;
            }
            return criteriaBuilder.like(root.get("nome"), "%" + nome + "%");
        };
    }

    //cognome
    private Specification<Autista> equalCognomeSpecification(String cognome) {
        return (root, query, criteriaBuilder) -> {
            if (cognome == null || cognome.isBlank()) {
                return null;
            }
            return criteriaBuilder.equal(root.get("cognome"), cognome);
        };
    }

    private Specification<Autista> likeCognomeSpecification(String cognome) {
        return (root, query, criteriaBuilder) -> {
            if (cognome == null || cognome.isBlank()) {
                return null;
            }
            return criteriaBuilder.like(root.get("cognome"), "%" + cognome + "%");
        };
    }

    //dataNascita
    private Specification<Autista> equalDataNascitaSpecification(LocalDate dataNascita) {
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

    //codFiscale
    private Specification<Autista> equalCodFiscaleSpecification(String codFiscale) {
        return (root, query, criteriaBuilder) -> {
            if (codFiscale == null || codFiscale.isBlank()) {
                return null;
            }
            return criteriaBuilder.equal(root.get("codFiscale"), codFiscale);
        };
    }

    private Specification<Autista> likeCodFiscaleSpecification(String codFiscale) {
        return (root, query, criteriaBuilder) -> {
            if (codFiscale == null || codFiscale.isBlank()) {
                return null;
            }
            return criteriaBuilder.like(root.get("codFiscale"), "%" + codFiscale + "%");
        };
    }

    //veicoloId
    private Specification<Autista> equalVeicoloIdSpecification(Veicolo veicolo) {
        return (root, query, criteriaBuilder) -> {
            if (veicolo == null) {
                return null;
            }
            Long veicoloId = veicolo.getId();
            if (veicoloId == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("veicolo").get("id"), veicoloId);
        };
    }

    //utenteId
    private Specification<Autista> equalUtenteIdSpecification(Utente utente) {
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
}
