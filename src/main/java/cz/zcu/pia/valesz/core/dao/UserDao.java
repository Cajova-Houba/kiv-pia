package cz.zcu.pia.valesz.core.dao;

import cz.zcu.pia.valesz.core.domain.User;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Methods for fetching user related data.
 */
@NoRepositoryBean
public interface UserDao extends GenericDao<User, Long> {

    /**
     * Loads user by his username.
     * @param username Username.
     * @return User or null if no user is found.
     */
    User findByUsername(String username);
}
