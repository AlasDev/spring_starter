package it.objectmethod.spring_starter.repository;

import it.objectmethod.spring_starter.entity.Autista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AutistaRepository extends JpaRepository<Autista, Long> {


    Optional<Autista> getAutistaById(Integer id);

    Autista getAutistaByNome(String nome);

    @Query(value = "select * from autista where nome := nome", nativeQuery = true)
    Autista paperino(String nome);
}