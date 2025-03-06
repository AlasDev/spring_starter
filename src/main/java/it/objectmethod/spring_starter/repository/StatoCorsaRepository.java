package it.objectmethod.spring_starter.repository;

import it.objectmethod.spring_starter.entity.StatoCorsa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StatoCorsaRepository extends JpaRepository<StatoCorsa, Long>, JpaSpecificationExecutor<StatoCorsa> {
}
