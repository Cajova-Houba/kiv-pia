package cz.zcu.pia.valesz.core.domain;

/**
 * Visibility of a particular item.
 */
public enum Visibility {

    /**
     * Only owner can access this.
     */
    OWNER,

    /**
     * Only owner and his friends can see this.
     */
    OWNER_FRIENDS,

    /**
     * Everyone who is registered (logged) can see this. Default.
     */
    REGISTERED_USERS,

    /**
     * Guess what...
     */
    EVERYONE


}
