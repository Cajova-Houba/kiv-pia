package cz.zcu.pia.valesz.core.dao;

import java.io.Serializable;

/**
 * A generic interface for every other dao.
 * @param <T> Persisted class.
 * @param <PK> Primary key.
 */
public interface GenericDao<T, PK> extends Serializable{

    /**
     * Gets the object from database by its primary key.
     * @param key Primary key.
     * @return Object or null if no object is found.
     */
    T get(PK key);

    /**
     * Saves the object and returns it. Also works for update.
     * @param object Object to be saved.
     * @return Saved object.
     */
    T save(T object);

    /**
     * Deletes object.
     *
     * @param key Primary key of the object to be deleted.
     */
    void delete(PK key);

}
