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
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Locale;

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

    @Autowired
    private MessageSource messageSource;

    @InitBinder("userForm")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(profileUpdateValidator);
    }

    /**
     * This method will just redirect to /profile.
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String handleGetProfileSearch() {
        return "redirect:/profile";
    }

    /**
     * Handles profile search. Username is excepted as parameter.
     * If the search is successful, method will redirect to /profile/{username}.
     * Otherwise it displays 'profile not found page'.
     *
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String handleProfileSearch(@RequestParam("username-search") String usernameSearch, ModelMap modelMap) {
        User user = userManager.loadByUsername(usernameSearch);
        boolean isAnonymous = authUtils.getCurrentlyLoggedUser() != null;
        if(user != null) {
            // user found
            return "redirect:/profile/"+usernameSearch;
        } else {
            // user not found
            Locale defaultLocale = Locale.getDefault();
            modelMap.addAttribute("isAnonymous", isAnonymous);
            modelMap.addAttribute("statusTitle", messageSource.getMessage("user.search.fail.title", new Object[0], defaultLocale));
            modelMap.addAttribute("statusMessage", messageSource.getMessage("user.search.fail.message", new Object[] {usernameSearch}, defaultLocale));
            return "status";
        }

    }

    /**
     * Handles update of a new profile photo.
     *
     * @param username User who is updating his profile photo is identified by this username.
     * @param modelMap
     * @param multipartFile File containing the new profile photo.
     * @return
     */
    @RequestMapping(value = "/{username}/edit/profile-photo", method = RequestMethod.POST)
    public String handleProfilePhotoUpdate(@PathVariable String username,
                                           ModelMap modelMap,
                                           @RequestParam("file")MultipartFile multipartFile) {
        log.info("Updating profile photo of user {}.", username);

        // check if username matches username of the current user
        User currentUser = authUtils.getCurrentlyLoggedUser();
        if(!currentUser.getUsername().equals(username)) {
            log.warn("User {} tried to upload new profile photo for user {}.", username, currentUser.getUsername());
            return "redirect:/profile";
        }

        // check file content
        if(multipartFile.isEmpty()) {
            log.warn("Empty file uploaded!");
            return "redirect:/profile";
        }
        log.info("Picture size: {}.",multipartFile.getSize());
        try {
            log.info("Bytes: {}.",multipartFile.getBytes().length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("Content type: {}.", multipartFile.getContentType());

        // update
        try {
            userManager.updateProfilePhoto(currentUser, multipartFile.getBytes());
        } catch (IOException e) {
            log.error("Update failed with exception {}. Exception message: {}.", e.toString(), e.getMessage());
            return "redirect:/profile";
        }

        return "redirect:/profile";
    }

    /**
     * This method will handle possible GET requests to photo upload url simply by redirecting to profile page..
     * @return
     */
    @RequestMapping(value = "/{username}/edit/profile-photo", method = RequestMethod.GET)
    public String handleGetPhotoUpload(@PathVariable String username) {
        return "redirect:/profile";
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

            // load user again, this time with his profile photo
            currentUser = authUtils.getCurrentlyLoggedUserWithProfilePhoto();
            addCommonAttributesToModelMap(modelMap, true, true, false, false, currentUser, currentUser);
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

        modelMap.addAttribute("userForm", new ProfileUpdateForm(currentUser));
        addCommonAttributesToModelMap(modelMap, true, true, false, false, currentUser, currentUser);

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

        // check if there's already any friend reuqest between current user and other user
        // if false, 'Send friend request' button will be displayed
        boolean isConnection = !isAnonymous && friendManager.connectionExists(currentUser, user);

        // if the user can't see the requested profile, redirect him to error-like page
        if(isHidden) {
            modelMap.addAttribute("isAnonymous", isAnonymous);
            modelMap.addAttribute("isConnection", isConnection);
            modelMap.addAttribute("username", username);
            return "profile-hidden";
        }

        // add attributes
        modelMap.addAttribute("userForm", new ProfileUpdateForm(user));
        addCommonAttributesToModelMap(modelMap, isCurrentUser, false, isAnonymous, isConnection, user, currentUser);

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

    /**
     * Adds common attributes for profile.jsp page such as flags and data objects.
     * New message and friend request counts are added automatically.
     *
     * @param modelMap Model map to which attributes will be added.
     * @param isCurrentUser Whether the viewed user is the same as the current one.
     * @param isEditMode Whether the profile edit form should be displayed.
     * @param isAnonymous Whether the viewer is logged in or not.
     * @param isConnection Whether there is any existing connection between viewer and viewed user.
     * @param user User object to contain viewed user's date (but not for profile display/form, those are passed separately).
     * @param currentUser Currently logged user. User for obtaining new messages/requests counts.
     */
    private void addCommonAttributesToModelMap(ModelMap modelMap,boolean isCurrentUser, boolean isEditMode, boolean isAnonymous, boolean isConnection, User user, User currentUser) {
        int newFriendReq = 0;
        int newMsgs = 0;
        if(isCurrentUser) {
            // load notifications
            newFriendReq = friendManager.getNumberOfNewFriendRequests(user);
            newMsgs = messageManager.getNumberOfNewMessages(user);
        };

        modelMap.addAttribute("newFriendReq", newFriendReq);
        modelMap.addAttribute("newMsgs", newMsgs);
        modelMap.addAttribute("isCurrentUser", isCurrentUser);
        modelMap.addAttribute("isEditMode", isEditMode);
        modelMap.addAttribute("isAnonymous",isAnonymous);
        modelMap.addAttribute("isConnection", isConnection);
        modelMap.addAttribute("user", user);
    }

}
