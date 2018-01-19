package cz.zcu.pia.valesz.web;

import cz.zcu.pia.valesz.core.domain.FriendRequest;
import cz.zcu.pia.valesz.core.domain.User;
import cz.zcu.pia.valesz.core.service.AuthUtils;
import cz.zcu.pia.valesz.core.service.FriendManager;
import cz.zcu.pia.valesz.core.service.UserManager;
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

    @Autowired
    private AuthUtils authUtils;

    @Autowired
    private FriendManager friendManager;

    @Autowired
    private UserManager userManager;


    /**
     * Creates a new friend request with current user as a sender and user given by username as
     * a receiver. If such request (non-cancelled) already exits, nothing will happen. If the request exists
     * and is cancelled, it will be deleted and renewed.
     *
     * @param username Target's username.
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/send/{username}")
    public String handleFriendRequestSending(@PathVariable("username") String username, ModelMap modelMap) {
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
