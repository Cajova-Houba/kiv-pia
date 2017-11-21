package cz.zcu.pia.valesz.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller which handles user's login.
 */
@Controller
@RequestMapping("/login")
public class LoginController {


    /**
     * Displays login form.
     */
    @RequestMapping(method = RequestMethod.GET)
    public String displayForm() {
        return "login";
    }

    /**
     * Handles user's login. If the login is not successful, display error.
     *
     * @param modelMap
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public String handleLogin(ModelMap modelMap) {
        return "main-page";
    }
}
