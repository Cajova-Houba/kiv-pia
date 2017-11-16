package cz.zcu.pia.valesz.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

/**
 * Spring controller.
 *
 * Created by Zdenek Vales on 16.11.2017.
 */
@Controller
@RequestMapping("/")
public class KivbookController {

    @RequestMapping(method = RequestMethod.GET)
    public String sayHello(ModelMap modelMap) {
        modelMap.addAttribute("currDate", new Date());
        return "index";
    }
}
