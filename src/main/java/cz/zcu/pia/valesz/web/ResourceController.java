package cz.zcu.pia.valesz.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * A controller which will fetch resources such as images and profile photos.
 */
@Controller
@RequestMapping("/resources")
public class ResourceController {

    /**
     * Returns the profile photo of a particular user as a byte array.
     * @param username
     * @return
     */
    @RequestMapping(value = "/profile/{username}", method = RequestMethod.GET)
    public byte[] profilePhoto(@PathVariable("username") String username) {
        return new byte[0];
    }
}
