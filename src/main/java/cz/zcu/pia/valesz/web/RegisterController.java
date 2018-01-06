package cz.zcu.pia.valesz.web;

import cz.zcu.pia.valesz.core.domain.Gender;
import cz.zcu.pia.valesz.core.domain.User;
import cz.zcu.pia.valesz.core.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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
        return "index";
    }

    /**
     * Handles registration.
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    // todo: use spring model or something instead
    public String handleRegistration(Model model,
                                     @Param("username") String username,
                                     @Param("email") String email,
                                     @Param("password-conf") String passwordConf,
                                     @Param("password") String password,
                                     @Param("full-name") String fullName,
                                     @Param("birth-date") Date birthDate,
                                     @Param("gender") String gender,
                                     @Param("accept-terms") boolean acceptTerms
                                     ) {
        // todo: handle registration
        Set<String> errors = userManager.validateRegistration(username, email, password, passwordConf, fullName, birthDate, gender, acceptTerms);

        if(errors.isEmpty()) {
            // proceed with registration
            // todo: hash password
            User u = new User(email, username, password, birthDate, fullName, Gender.webNameToGender(gender));
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
