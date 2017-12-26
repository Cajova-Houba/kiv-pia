package cz.zcu.pia.valesz.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String displayForm(ModelMap modelMap, @RequestParam(value = "error", required = false) String error) {
        if (error != null) {
            modelMap.addAttribute("error", error);
        }
        return "login";
    }
}
