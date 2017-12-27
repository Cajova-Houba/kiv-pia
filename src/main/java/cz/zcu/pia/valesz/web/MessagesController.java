package cz.zcu.pia.valesz.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller which handles displaying and sending user's messages.
 */
@Controller
@RequestMapping("/messages")
public class MessagesController {

    /**
     * Displays the base page with messages overview.
     * @param modelMap
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String displayPage(ModelMap modelMap) {
        return "messages";
    }
}
