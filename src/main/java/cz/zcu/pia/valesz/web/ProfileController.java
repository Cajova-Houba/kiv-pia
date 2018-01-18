package cz.zcu.pia.valesz.web;

import cz.zcu.pia.valesz.core.domain.User;
import cz.zcu.pia.valesz.core.service.AuthUtils;
import cz.zcu.pia.valesz.core.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller which handles displaying and editing of users profile.
 */
@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private AuthUtils authUtils;

    @Autowired
    private UserManager userManager;

    /**
     * Displays editing form for user's profile.
     * If the current user isn't same as the one given by username, method will redirect to
     * /profile/user.
     *
     * @param username Username of the user whose profile is going to be edited.
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/{username}/edit", method = RequestMethod.GET)
    public String displayEditForm(@PathVariable String username, ModelMap modelMap) {

        User currentUser = authUtils.getCurrentlyLoggedUserWithProfilePhoto();
        if(!currentUser.getUsername().equals(username)) {
            return "redirect:/profile/"+username;
        }

        modelMap.addAttribute("user", currentUser);
        modelMap.addAttribute("isCurrentUser", true);
        modelMap.addAttribute("isEditMode", true);

        return "profile";
    }

    /**
     * Displays a profile of user with given username.
     * If the user is not resolved, method will redirect to /profile.
     *
     * @param username
     * @return
     */
    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public String displayUsersProfile(@PathVariable String username, ModelMap modelMap) {

        User user = userManager.loadByUsername(username, true);
        if(user == null) {
            // redirect to current user's profile
        }

        // true if the displayed profile belongs to the currently logged user
        boolean isCurrentUser = authUtils.getCurrentlyLoggedUser().equals(user);

        modelMap.addAttribute("user", user);
        modelMap.addAttribute("isCurrentUser", isCurrentUser);
        modelMap.addAttribute("isEditMode", false);

        return "profile";
    }

    /**
     * Will redirect to current user's profile.
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String displayCurrentUserProfile() {
        User currentUser = authUtils.getCurrentlyLoggedUser();

        if(currentUser == null) {
            return "redirect:/login";
        }

        return "redirect:/profile/"+currentUser.getUsername();
    }


}
