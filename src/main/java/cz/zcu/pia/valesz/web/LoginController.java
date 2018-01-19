package cz.zcu.pia.valesz.web;

import cz.zcu.pia.valesz.core.service.AuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller which displays login page and redirects users which are already logged in.
 * Logging process itself is handled by spring security.
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthUtils authUtils;

    /**
     * Displays login form.
     */
    @RequestMapping(method = RequestMethod.GET)
    public String displayForm(ModelMap modelMap, @RequestParam(value = "error", required = false) String error) {

        if(authUtils.getCurrentlyLoggedUser() != null) {
            return "redirect:/feed";
        }

        if (error != null) {
            modelMap.addAttribute("error", error);
        }
        return "login";
    }
}
