package it.objectmethod.spring_starter.repository;

import it.objectmethod.spring_starter.entity.Autista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AutistaRepository extends JpaRepository<Autista, Long>, JpaSpecificationExecutor<Autista> {

    //JPA Keywords
    Optional<List<Autista>> getAutistaByNome(String nome);
    
    Optional<List<Autista>> getAutistaByCognome(String cognome);

    Optional<List<Autista>> getAutistaByDataNascita(LocalDate dataNascita);

    Optional<List<Autista>> getAutistaByCodFiscale(String codFiscale);

    Optional<List<Autista>> getAutistaByVeicoloId(Long veicoloId);
}