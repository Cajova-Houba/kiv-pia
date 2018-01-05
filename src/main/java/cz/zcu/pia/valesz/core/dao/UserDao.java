package cz.zcu.pia.valesz.core.dao;

import cz.zcu.pia.valesz.core.domain.User;

/**
 * Methods for fetching user related data.
 */
//@NoRepositoryBean
public interface UserDao extends GenericDao<User, Long> {

    /**
     * Loads user by his username.
     * @param username Username.
     * @return User or null if no user is found.
     */
    User findByUsername(String username);

    /**
     * Returns true if user with this username already exists in database.
     * @param username Username.
     * @return True if user with this username already exists in database.
     */
    boolean existsByUsername(String username);
}
