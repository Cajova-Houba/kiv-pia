package cz.zcu.pia.valesz.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * If for some reason user stumbles upon /logout urls, this controller will redirect him.
 */
@Controller
public class LogoutController {

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String redirectUser() {
        return "redirect:/login";
    }
}
