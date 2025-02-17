package it.objectmethod.spring_starter.repository;

import it.objectmethod.spring_starter.entity.Corsa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CorsaRepository extends JpaRepository<Corsa, Long>, JpaSpecificationExecutor<Corsa> {
    Optional<Corsa> getCorsaById(Long id);
}
