package it.objectmethod.spring_starter.dto.filter;

import it.objectmethod.spring_starter.entity.Cliente;
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

    public Specification<Cliente> toSpecification() {
        return Specification.<Cliente>where(null)
                //nome
                .or(equalNomeSpecification(nome))
                .or(likeNomeSpecification(nome))
                .or(inNomeSpecification(nome))
                //cognome
                .or(equalCognomeSpecification(cognome))
                .or(likeCognomeSpecification(cognome))
                .or(inCognomeSpecification(cognome))
                //dataNascita
                .or(equalDataNascitaSpecification(dataNascita))
                .or(inDataNascitaSpecification(dataNascita))
                //codFiscale
                .or(equalCodFiscaleSpecification(codFiscale))
                .or(likeCodFiscaleSpecification(codFiscale))
                .or(inCodFiscaleSpecification(codFiscale))
                //dataIscrizione
                .or(equalDataIscrizioneSpecification(dataIscrizione))
                .or(inDataIscrizioneSpecification(dataIscrizione))
                ;
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
    private Specification<Cliente> likeNomeSpecification(String nome) {
        return (Root<Cliente> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (nome == null || nome.isBlank()) {
                return null;
            }
            return criteriaBuilder.like(root.get("nome"), nome);
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
    private Specification<Cliente> likeCognomeSpecification(String cognome) {
        return (Root<Cliente> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (cognome == null || cognome.isBlank()) {
                return null;
            }
            return criteriaBuilder.like(root.get("cognome"), cognome);
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
    private Specification<Cliente> likeCodFiscaleSpecification(String codFiscale) {
        return (Root<Cliente> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (codFiscale == null || codFiscale.isBlank()) {
                return null;
            }

            return criteriaBuilder.like(root.get("codFiscale"), codFiscale);
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
}
