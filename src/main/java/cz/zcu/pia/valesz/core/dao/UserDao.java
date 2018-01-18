package cz.zcu.pia.valesz.core.dao;

import cz.zcu.pia.valesz.core.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Methods for fetching user related data.
 */
//@NoRepositoryBean
public interface UserDao extends GenericDao<User, Long> {

    /**
     * Loads user by his username and also fetches his profile photo.
     * @param username Username.
     * @return User with his profile photo or null if no user is found.
     */
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.profilePhoto WHERE u.username = :username")
    User findByUsernameFetchProfilePhoto(@Param("username") String username);

    /**
     * Loads user by his username.
     * @param username Username.
     * @return User or null if no user is found.
     */
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.profilePhoto WHERE u.username = :username")
    User findByUsername(@Param("username") String username);

    /**
     * Returns true if user with this username already exists in database.
     * @param username Username.
     * @return True if user with this username already exists in database.
     */
    boolean existsByUsername(String username);
}
