package it.objectmethod.spring_starter.repository;

import it.objectmethod.spring_starter.entity.Veicolo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VeicoloRepository extends JpaRepository<Veicolo, Long>, JpaSpecificationExecutor<Veicolo> {

    Optional<Veicolo> getVeicoloByNumTarga(String numTarga);

    Optional<Veicolo> getVeicoloById(Long id);

    @Query(value = "SELECT * FROM veicolo v WHERE v.modello = $1", nativeQuery = true)
    Optional<List<Veicolo>> modelloVeicolo(String modello);

    boolean existsByNumTarga(String numTarga);
}