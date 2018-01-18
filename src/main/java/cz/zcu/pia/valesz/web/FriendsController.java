package cz.zcu.pia.valesz.web;

import cz.zcu.pia.valesz.core.domain.FriendRequest;
import cz.zcu.pia.valesz.core.domain.User;
import cz.zcu.pia.valesz.core.service.AuthUtils;
import cz.zcu.pia.valesz.core.service.FriendManager;
import cz.zcu.pia.valesz.core.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
