package it.objectmethod.spring_starter.repository;

import it.objectmethod.spring_starter.entity.Autista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutistaRepository extends JpaRepository<Autista, Long> {

    Autista getAutistaById(Integer id);

    Autista getAutistaByNome(String nome);
}