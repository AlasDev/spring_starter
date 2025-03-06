package it.objectmethod.spring_starter.repository;

import it.objectmethod.spring_starter.entity.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long>, JpaSpecificationExecutor<Utente> {

    Boolean existsByEmailAndPassword(String email, String password);

    Boolean existsByEmail(String email);
}
