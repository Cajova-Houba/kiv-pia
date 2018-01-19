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
import org.springframework.web.bind.annotation.RequestParam;

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

    /**
     * Just redirect back to friends this needs to be done by POST request.
     * @return
     */
    @RequestMapping(value = "/reject", method = RequestMethod.GET)
    public String handleGetRejectFriendRequest() {
        return "redirect:/friends";
    }

    /**
     * Handles rejecting a request. The request must be in PENDING state to be rejected.
     * Only logged user can reject request and only if he is the receiver.
     *
     * @param requestId
     * @return
     */
    @RequestMapping(value = "/reject", method = RequestMethod.POST)
    public String handleRejectFriendRequest(@RequestParam("requestId") long requestId) {
        log.info("Rejecting request {}.", requestId);

        // load the request
        FriendRequest toBeRejected = checkRequestAndReceiver(requestId);
        if(toBeRejected == null) {
            // logging is done in the helper method
            return "redirect:/friends";
        }

        // check if it's rejectable
        if(!toBeRejected.isNew()) {
            log.warn("Request {} is not rejectable!", requestId);
            return "redirect:/friends";
        }

        // reject the request
        friendManager.rejectRequest(toBeRejected);
        log.info("Request rejected.");

        return "redirect:/friends";
    }

    /**
     * Just redirect back to friends this needs to be done by POST request.
     * @return
     */
    @RequestMapping(value = "/accept", method = RequestMethod.GET)
    public String handleGetAcceptFriendRequest() {
        return "redirect:/friends";
    }

    /**
     * Handles accepting a request. The request must be in PENDING state to be accepted.
     * Only logged user can accept request and only if he is the receiver.
     *
     * @param requestId Id of a request to be accepted.
     * @return
     */
    @RequestMapping(value = "/accept", method = RequestMethod.POST)
    public String handleAcceptFriendRequest(@RequestParam("requestId") long requestId) {
        log.info("Accepting request {}.",requestId);

        // load the request
        FriendRequest toBeAccepted = checkRequestAndReceiver(requestId);
        if(toBeAccepted == null) {
            // logging is done in the helper method
            return "redirect:/friends";
        }

        // check if it's acceptable
        if(!toBeAccepted.isNew()) {
            log.warn("Request {} is not acceptable!", requestId);
            return "redirect:/friends";
        }

        // accept the request
        friendManager.acceptRequest(toBeAccepted);
        log.info("Request accepted.");

        return "redirect:/friends";
    }

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

    /**
     * Checks if the request with this id exists and that the currently logged user is the sender.
     * @param requestId Id of a request.
     * @return FriendRequest object if it exists and user is the sender. Null otherwise.
     */
    private FriendRequest checkRequestAndReceiver(long requestId) {
        // load the request and check if it exists
        FriendRequest toBeChecked = friendManager.findByIdFetchUsers(requestId);
        if(toBeChecked == null) {
            log.warn("Friend request with id {} doesn't exist!", requestId);
            return null;
        }

        // check that the current user is the sender
        User currentUser = authUtils.getCurrentlyLoggedUser();
        if(!currentUser.equals(toBeChecked.getReceiver())) {
            log.warn("Current user {} isn't the same as receiver {}!", currentUser.getUsername(), toBeChecked.getSender().getUsername());
            return null;
        }

        return toBeChecked;
    }
}
