package cz.zcu.pia.valesz.web;

import cz.zcu.pia.valesz.core.domain.Post;
import cz.zcu.pia.valesz.core.domain.User;
import cz.zcu.pia.valesz.core.service.AuthUtils;
import cz.zcu.pia.valesz.core.service.FriendManager;
import cz.zcu.pia.valesz.core.service.MessageManager;
import cz.zcu.pia.valesz.core.service.PostManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Will display user's main page if he's already logged in.
 *
 * Created by Zdenek Vales on 16.11.2017.
 */
@Controller
@RequestMapping("/feed")
public class MainController {

    @Autowired
    private AuthUtils authUtils;

    @Autowired
    private PostManager postManager;

    @Autowired
    private FriendManager friendManager;

    @Autowired
    private MessageManager messageManager;

    @RequestMapping(method = RequestMethod.GET)
    public String displayPage(ModelMap modelMap) {

        User currentUser = authUtils.getCurrentlyLoggerUser();
        int newFriendReq = friendManager.getNumberOfNewFriendRequests(currentUser);
        int newMsgs = messageManager.getNumberOfNewMessages(currentUser);
        List<Post> posts = postManager.listPostsForUser(currentUser);

        modelMap.addAttribute("newFriendReq", newFriendReq);
        modelMap.addAttribute("newMsgs", newMsgs);
        modelMap.addAttribute("posts", posts);
        modelMap.addAttribute("currentUser", currentUser);

        return "main-page";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createPost(@RequestParam("post-text") String postText, ModelMap modelMap) {
        User currentUser = authUtils.getCurrentlyLoggerUser();
        postManager.createNewPost(postText, currentUser);
        return "redirect:/feed";
    }
}
