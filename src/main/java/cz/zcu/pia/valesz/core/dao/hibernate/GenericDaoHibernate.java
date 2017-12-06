package cz.zcu.pia.valesz.core.dao.hibernate;

import cz.zcu.pia.valesz.core.dao.GenericDao;

/**
 * A generic class for every dao implementation.
 */
public class GenericDaoHibernate<T, PK> implements GenericDao<T,PK> {

    @Override
    public T get(PK key) {
        return null;
    }

    @Override
    public T save(T object) {
        return null;
    }

    @Override
    public void delete(PK key) {

    }
}
