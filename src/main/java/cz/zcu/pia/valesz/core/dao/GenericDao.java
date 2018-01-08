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
     * Returns all instances of persisted class.
     * @return
     */
    List<T> findAll();

}
