package cz.zcu.pia.valesz.web;

import cz.zcu.pia.valesz.core.domain.User;
import cz.zcu.pia.valesz.core.service.UserManager;
import cz.zcu.pia.valesz.web.vo.UserForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.Set;

/**
 * Controller for register page.
 */
@Controller
@RequestMapping("/register")
public class RegisterController {

    private static final Logger log = LoggerFactory.getLogger(RegisterController.class);

    // errors passed back to registration form
    public static final String REG_USERNAME_EXISTS = "errUsernameExists";
    public static final String REG_WRONG_EMAIL = "errWrongEmail";
    public static final String REG_PASS_DONT_MATCH = "errPassDontMatch";
    public static final String REG_TOO_YOUNG_MAN = "errTooYoungMan";
    public static final String REG_NOT_A_GENDER = "errNotAGender";
    public static final String REG_SHUT_UP_AND_ACCEPT = "errShutUpAndAccept";

    @Autowired
    private UserManager userManager;

    @RequestMapping(method = RequestMethod.GET)
    public String displayPage(ModelMap modelMap) {
        modelMap.addAttribute("currDate", new Date());
        modelMap.addAttribute("userForm", new UserForm());
        return "index";
    }

    /**
     * Handles registration.
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public String handleRegistration(Model model,
                                     @ModelAttribute("userForm")UserForm userForm,
                                     BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            log.warn("Error occurred while binding registration form to UserForm object.");
            for(ObjectError error : bindingResult.getAllErrors()) {
                log.warn("Error: object name = {}; code = {}; toString = {}.",error.getObjectName(), error.getCode(), error.toString());
            }

            return "index";
        }

        // todo: handle registration
        Set<String> errors = userManager.validateRegistration(userForm);

        if(errors.isEmpty()) {
            // proceed with registration
            // todo: hash password
            User u = new User(userForm);
            u = userManager.registerUser(u);
            return "reg-succ";
        } else {
            for(String error : errors) {
                model.addAttribute(error, true);
            }
        }

        model.addAttribute("currDate", new Date());
        return "index";
    }
}
