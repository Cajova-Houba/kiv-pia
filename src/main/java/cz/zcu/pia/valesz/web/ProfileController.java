package cz.zcu.pia.valesz.web;

import cz.zcu.pia.valesz.core.domain.User;
import cz.zcu.pia.valesz.core.domain.Visibility;
import cz.zcu.pia.valesz.core.service.AuthUtils;
import cz.zcu.pia.valesz.core.service.FriendManager;
import cz.zcu.pia.valesz.core.service.MessageManager;
import cz.zcu.pia.valesz.core.service.UserManager;
import cz.zcu.pia.valesz.web.validation.ProfileUpdateValidator;
import cz.zcu.pia.valesz.web.vo.ProfileUpdateForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

/**
 * Controller which handles displaying and editing of users profile.
 */
@Controller
@RequestMapping("/profile")
public class ProfileController {

    private static final Logger log = LoggerFactory.getLogger(ProfileController.class);

    @Autowired
    private AuthUtils authUtils;

    @Autowired
    private UserManager userManager;

    @Autowired
    private FriendManager friendManager;

    @Autowired
    private MessageManager messageManager;

    @Autowired
    private ProfileUpdateValidator profileUpdateValidator;

    @InitBinder("userForm")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(profileUpdateValidator);
    }

    /**
     * Handles update of user's profile.
     * User (given by path variable username) whose profile is being updated must be the current one.
     * If any error occurs while validating update, error will be sent back to form.
     * Redirects to /profile/{username} on success.
     *
     * @param username Username of the user whose profile is being updated.
     * @param modelMap
     * @param profileUpdate Object containing updated data.
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/{username}/edit", method = RequestMethod.POST)
    public String handleProfileUpdate(@PathVariable String username,
                                      ModelMap modelMap,
                                      @ModelAttribute("userForm") @Validated ProfileUpdateForm profileUpdate,
                                      BindingResult bindingResult) {

        log.info("Updating profile of user {}.",username);

        // check that the user can update profile
        User currentUser = authUtils.getCurrentlyLoggedUser();
        if(currentUser == null || !currentUser.getUsername().equals(username)) {
            log.warn("No user is logged in or someone else is trying to update profile of user {}!",username);
            return "redirect:/profile";
        }

        // if there are any errors, display form again
        if(bindingResult.hasErrors()) {
            log.warn("There are errors in binding result!");

            // load stuff to be displayed with error again
            int newFriendReq = friendManager.getNumberOfNewFriendRequests(currentUser);
            int newMsgs = messageManager.getNumberOfNewMessages(currentUser);

            modelMap.addAttribute("newMsgs", newMsgs);
            modelMap.addAttribute("newFriendReq", newFriendReq);
            modelMap.addAttribute("user", currentUser);
            modelMap.addAttribute("isCurrentUser", true);
            modelMap.addAttribute("isEditMode", true);
            modelMap.addAttribute("isAnonymous",false);
            return "profile";
        }

        // save
        log.info("Update ok.");
        userManager.updateUserProfile(currentUser, profileUpdate);


        return "redirect:/profile/"+username;
    }

    /**
     * Displays editing form for user's profile.
     * If the current user isn't same as the one given by username, method will redirect to
     * /profile/user.
     *
     * New profile photos will also be handled by this controller.
     *
     * @param username Username of the user whose profile is going to be edited.
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/{username}/edit", method = RequestMethod.GET)
    public String displayEditForm(@PathVariable String username, ModelMap modelMap) {

        User currentUser = authUtils.getCurrentlyLoggedUserWithProfilePhoto();
        if(!currentUser.getUsername().equals(username)) {
            log.warn("User {} tried to edit profile of user {}.", currentUser.getUsername(), username);
            return "redirect:/profile/"+username;
        }

        // load notifications
        int newFriendReq = friendManager.getNumberOfNewFriendRequests(currentUser);
        int newMsgs = messageManager.getNumberOfNewMessages(currentUser);

        modelMap.addAttribute("newMsgs", newMsgs);
        modelMap.addAttribute("newFriendReq", newFriendReq);
        modelMap.addAttribute("user", currentUser);
        modelMap.addAttribute("userForm", new ProfileUpdateForm(currentUser));
        modelMap.addAttribute("isCurrentUser", true);
        modelMap.addAttribute("isEditMode", true);
        modelMap.addAttribute("isAnonymous",false);

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
            return "redirect:/profile";
        }

        // true if the displayed profile belongs to the currently logged user
        User currentUser = authUtils.getCurrentlyLoggedUser();
        boolean isAnonymous = currentUser == null;
        boolean isCurrentUser = (!isAnonymous && currentUser.equals(user));

        // true if the profile shouldn't be displayed to viewer
        boolean isHidden = false;

        if(isAnonymous && user.getProfileVisibility() != Visibility.EVERYONE) {
            // hide profile if none is logged in and visibility is not everyone
            isHidden = true;
        } else if (!isCurrentUser && user.getProfileVisibility() == Visibility.OWNER) {
            // hide profile if the visibility is OWNER but the viewed user is not the current one
            isHidden = true;
        } else if (!isCurrentUser && !friendManager.isFriendOf(user, currentUser) && user.getProfileVisibility() == Visibility.OWNER_FRIENDS) {
            // hide profile if the viewed user is not the current one, visibility is set only for friends and users are not friends
            isHidden = true;
        }

        // if the user can't see the requested profile, redirect him to error-like page
        if(isHidden) {
            modelMap.addAttribute("isAnonymous", isAnonymous);
            return "profile-hidden";
        }

        int newFriendReq = 0;
        int newMsgs = 0;
        if(isCurrentUser) {
            // load notifications
            newFriendReq = friendManager.getNumberOfNewFriendRequests(user);
            newMsgs = messageManager.getNumberOfNewMessages(user);
        }

        modelMap.addAttribute("newMsgs", newMsgs);
        modelMap.addAttribute("newFriendReq", newFriendReq);
        modelMap.addAttribute("user", user);
        modelMap.addAttribute("userForm", new ProfileUpdateForm(user));
        modelMap.addAttribute("isCurrentUser", isCurrentUser);
        modelMap.addAttribute("isEditMode", false);
        modelMap.addAttribute("isAnonymous",isAnonymous);

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
