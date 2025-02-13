package it.objectmethod.spring_starter.repository;

import it.objectmethod.spring_starter.entity.Veicolo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VeicoloRepository extends JpaRepository<Veicolo, Long>, JpaSpecificationExecutor<Veicolo> {

    Optional<Veicolo> getVeicoloById(Long id);

}