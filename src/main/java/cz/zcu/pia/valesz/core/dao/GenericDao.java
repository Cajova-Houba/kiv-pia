package cz.zcu.pia.valesz.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

/**
 * Base interface for all other DAO interfaces.
 */
@NoRepositoryBean
public interface GenericDao<T, PK extends Serializable> extends JpaRepository<T, PK> {

    /**
     * Delete object from database.
     * @param deleted Object to be deleted.
     */
    void delete(T deleted);

    /**
     * Delete object by its id.
     * @param id Id of the object to be deleted.
     */
//    void delete(PK id);

    /**
     * Returns all instances of persisted class.
     * @return
     */
    List<T> findAll();

    /**
     * Find one instance of persisted class.
     *
     * @param id Unique identifier.
     * @return Optional containing the returned object.
     */
//    Optional<T> findOne(PK id);
//
//    /**
//     * Persist instance.
//     * @param persisted Object to be saved.
//     * @return Saved object.
//     */
//    T save(T persisted);
}
