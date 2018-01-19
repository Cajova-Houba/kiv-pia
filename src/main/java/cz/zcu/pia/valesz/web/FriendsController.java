package cz.zcu.pia.valesz.web;

import cz.zcu.pia.valesz.core.domain.FriendRequest;
import cz.zcu.pia.valesz.core.domain.User;
import cz.zcu.pia.valesz.core.service.AuthUtils;
import cz.zcu.pia.valesz.core.service.FriendManager;
import cz.zcu.pia.valesz.core.service.UserManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Controller which handles management of user's friends and friend requests.
 */
@Controller
@RequestMapping("/friends")
public class FriendsController {

    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private AuthUtils authUtils;

    @Autowired
    private FriendManager friendManager;

    @Autowired
    private UserManager userManager;

    @RequestMapping(value = "/send/{username}", method = RequestMethod.GET)
    public String handleGetFriendReqSending(@PathVariable String username) {
        return "redirect:/profile"+username;
    }

    /**
     * Creates a new friend request with current user as a sender and user given by username as
     * a receiver. If such request (non-cancelled) already exits, nothing will happen. If there are
     * any cancelled/rejected requests, they will be kept and new one will be created.
     *
     * @param username Target's username.
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/send/{username}", method = RequestMethod.POST)
    public String handleFriendRequestSending(@PathVariable String username, ModelMap modelMap) {

        log.info("Sending friend request to {}.", username);

        User currentUser = authUtils.getCurrentlyLoggedUser();
        User otherUser = userManager.loadByUsername(username);

        // check other user
        if(otherUser == null) {
            // error
            log.warn("User {} not found!", username);
            return "redirect:/profile";
        } else if (otherUser.equals(currentUser)) {
            // error
            log.warn("User {} attempted to send friend request to himself.");
            return "redirect:/profile";
        }

        // check if any friend request doesn't exist already
        if(friendManager.connectionExists(currentUser, otherUser)) {
            log.warn("There is already on friendship request between {} and {}.", currentUser.getUsername(), otherUser.getUsername());
            return "redirect:/profile/"+username;
        }

        // create request
        friendManager.sendFriendRequest(currentUser, otherUser);

        return "redirect:/profile/"+username;
    }

    /**
     * Display base page with friends overview.
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String displayPage(Model model) {

        User currentUser = authUtils.getCurrentlyLoggedUserWithProfilePhoto();
        List<FriendRequest> newRequests = friendManager.listNewFriendRequests(currentUser);
        List<FriendRequest> friendships = friendManager.listFriendships(currentUser);

        model.addAttribute("currentUser", currentUser);
        model.addAttribute("newRequests", newRequests);
        model.addAttribute("friendships", friendships);

        return "friends";
    }
}
