package cz.zcu.pia.valesz.core.service;


import cz.zcu.pia.valesz.core.domain.User;

/**
 * Interface for user-related actions.
 */
public interface UserManager {

    /**
     * Returns the currently logged user.
     * @return
     */
    User getCurrentlyLoggerUser();

    /**
     * Loads user by his username.
     * @param username Username.
     * @return User or null if no user is found.
     */
    User loadByUsername(String username);
}
