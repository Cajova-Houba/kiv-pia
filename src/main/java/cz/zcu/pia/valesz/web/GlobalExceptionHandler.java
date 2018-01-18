package cz.zcu.pia.valesz.web;

import cz.zcu.pia.valesz.core.domain.User;
import cz.zcu.pia.valesz.core.service.AuthUtils;
import cz.zcu.pia.valesz.core.service.FriendManager;
import cz.zcu.pia.valesz.core.service.MessageManager;
import cz.zcu.pia.valesz.core.service.UserManager;
import cz.zcu.pia.valesz.web.vo.ProfileUpdateForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;

/**
 * Global handler for some exceptions which may occur.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private AuthUtils authUtils;

    @Autowired
    private UserManager userManager;

    @Autowired
    private FriendManager friendManager;

    @Autowired
    private MessageManager messageManager;

    /**
     * Handles the exception which may rise when uploading large photos.
     * The following solution isn't nice but I want the app to behave this way:
     * handler loads all necessary data and then displays edit profile page with error message.
     *
     * Note that if multipart max upload size is changed, more size-related validation will be needed.
     *
     * @return
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ModelAndView handleProfilePhotoSizeUploadError() {
//        log.warn("Data size exception caught - image is too big!");
        User currentUser = authUtils.getCurrentlyLoggedUserWithProfilePhoto();
        int newFriendReq = friendManager.getNumberOfNewFriendRequests(currentUser);
        int newMsgs = messageManager.getNumberOfNewMessages(currentUser);

        ModelAndView modelMap = new ModelAndView();
        modelMap.setViewName("profile");

        modelMap.addObject("newMsgs", newMsgs);
        modelMap.addObject("newFriendReq", newFriendReq);
        modelMap.addObject("user", currentUser);
        modelMap.addObject("userForm", new ProfileUpdateForm(currentUser));
        modelMap.addObject("isCurrentUser", true);
        modelMap.addObject("isEditMode", true);
        modelMap.addObject("isAnonymous",false);
        modelMap.addObject("isImageSizeError", true);
        return modelMap;

    }
}
