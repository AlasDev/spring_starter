package it.objectmethod.spring_starter.repository;

import it.objectmethod.spring_starter.entity.RiepilogoCorse;
import org.springframework.stereotype.Repository;

@Repository
public interface RiepilogoCorseRepository extends OMGenericViewJpaRepository<RiepilogoCorse, Long> {
    boolean existsById(Long id);
}
