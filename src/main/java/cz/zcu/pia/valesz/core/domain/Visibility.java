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
    OWNER("Only you"),

    /**
     * Only owner and his friends can see this.
     */
    OWNER_FRIENDS("Friends"),

    /**
     * Everyone who is registered (logged) can see this. Default.
     */
    REGISTERED_USERS("Others"),

    /**
     * Guess what...
     */
    EVERYONE("Everyone");

    /**
     * Displayable visibility name.
     */
    public final String displayName;

    Visibility(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Returns visibilities for convenient form handling.
     * @return
     */
    public static Map<String, String> getFormVisibilities() {
        Map<String, String> visibilityMap = new HashMap<>();
        visibilityMap.put(OWNER.name(), OWNER.displayName);
        visibilityMap.put(OWNER_FRIENDS.name(), OWNER_FRIENDS.displayName);
        visibilityMap.put(REGISTERED_USERS.name(), REGISTERED_USERS.displayName);
        visibilityMap.put(EVERYONE.name(), EVERYONE.displayName);

        return visibilityMap;
    }


}
