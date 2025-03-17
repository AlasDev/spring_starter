package it.objectmethod.spring_starter.repository;

import it.objectmethod.spring_starter.entity.Ruolo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RuoloRepository extends JpaRepository<Ruolo, Long>, JpaSpecificationExecutor<Ruolo> {
    static Ruolo findByNome(String nome) {
        Ruolo ruolo = new Ruolo();
        ruolo.setNome(nome);
        return ruolo;
    }
}
