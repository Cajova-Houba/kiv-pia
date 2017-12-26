package cz.zcu.pia.valesz.web;

import org.springframework.context.annotation.Role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

/**
 * Will display user's main page if he's already logged in.
 *
 * Created by Zdenek Vales on 16.11.2017.
 */
@Controller
@RequestMapping("/")
public class MainController {

    @RequestMapping(method = RequestMethod.GET)
    public String displayPage(ModelMap modelMap) {
        return "main-page";
    }
}
