package cz.zcu.pia.valesz.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

/**
 * Controller for register page.
 */
@Controller
@RequestMapping("/register")
public class RegisterController {

    @RequestMapping(method = RequestMethod.GET)
    public String displayPage(ModelMap modelMap) {
        modelMap.addAttribute("currDate", new Date());
        return "index";
    }
}
