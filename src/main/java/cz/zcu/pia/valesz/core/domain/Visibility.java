package cz.zcu.pia.valesz.core.domain;

import java.util.HashMap;
import java.util.Map;

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
    EVERYONE;

    /**
     * Returns visibilities for convenient form handling.
     * @return
     */
    public static Map<String, String> getFormVisibilities() {
        Map<String, String> visibilityMap = new HashMap<>();
        visibilityMap.put("OWNER", "Only you");
        visibilityMap.put("OWNER_FRIENDS", "Friends");
        visibilityMap.put("REGISTERED_USERS", "Others");
        visibilityMap.put("EVERYONE", "Everyone");

        return visibilityMap;
    }


}
