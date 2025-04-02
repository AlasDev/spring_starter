package it.objectmethod.spring_starter.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

/**
 * A really basic extension of a readonly Repository
 *
 * @param <E> the entity of the repository
 * @param <ID> the type of the ID of the entity
 *
 * @author Emmettippì
 */
@NoRepositoryBean
public interface OMGenericViewJpaRepository<E, ID> extends Repository<E, ID> {
    List<E> findAll();

    List<E> findAllById(Iterable<ID> ids);

    List<E> findAll(Sort sort);

    Page<E> findAll(Pageable pageable);

    Optional<E> findById(ID id);

    long count();

    @Query("SELECT t.id FROM #{#entityName} t")
    List<ID> getAllIds();

    List<E> findByIdIn(Iterable<ID> ids);

    List<E> findByIdNotIn(Iterable<ID> ids);
}