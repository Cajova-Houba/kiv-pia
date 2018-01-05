package cz.zcu.pia.valesz.core.dao;

import cz.zcu.pia.valesz.core.domain.User;

/**
 * Methods for fetching user related data.
 */
public interface UserDao extends GenericRepository<User, Long> {

    /**
     * Loads user by his username.
     * @param username Username.
     * @return User or null if no user is found.
     */
    User findByUsername(String username);
}
