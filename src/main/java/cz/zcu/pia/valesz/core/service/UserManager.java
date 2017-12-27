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
}
