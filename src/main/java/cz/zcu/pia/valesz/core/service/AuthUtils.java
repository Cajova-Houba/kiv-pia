package cz.zcu.pia.valesz.core.service;

import cz.zcu.pia.valesz.core.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Library class for some auth-related stuff.
 */
public class AuthUtils {

    @Autowired
    @Qualifier("userManager")
    private UserManager userManager;

    /**
     * Returns the currently logged user.
     * @return User or null if no user is logged in.
     */
    public User getCurrentlyLoggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            return (User) userManager.loadUserByUsername(currentUserName);
        }

        return null;
    }

    /**
     * Returns the currently logged user and also fetches his profile photo.
     * @return User with his profile photo or null if no user is logged in.
     */
    public User getCurrentlyLoggedUserWithProfilePhoto() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            return (User) userManager.loadUserByUsername(currentUserName);
        }

        return null;
    }

}
