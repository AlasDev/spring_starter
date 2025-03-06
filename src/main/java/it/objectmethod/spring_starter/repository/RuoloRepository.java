package it.objectmethod.spring_starter.repository;

import it.objectmethod.spring_starter.entity.Ruolo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RuoloRepository extends JpaRepository<Ruolo, Long>, JpaSpecificationExecutor<Ruolo> {
}
