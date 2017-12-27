package cz.zcu.pia.valesz.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller which handles management of user's friends and friend requests.
 */
@Controller
@RequestMapping("/friends")
public class FriendsController {

    /**
     * Display base page with friends overview.
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String displayPage() {
        return "friends";
    }
}
