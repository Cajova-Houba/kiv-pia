package cz.zcu.pia.valesz.web;

import cz.zcu.pia.valesz.core.domain.Post;
import cz.zcu.pia.valesz.core.domain.User;
import cz.zcu.pia.valesz.core.domain.Visibility;
import cz.zcu.pia.valesz.core.service.AuthUtils;
import cz.zcu.pia.valesz.core.service.FriendManager;
import cz.zcu.pia.valesz.core.service.MessageManager;
import cz.zcu.pia.valesz.core.service.PostManager;
import cz.zcu.pia.valesz.web.vo.PagingControl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

/**
 * Will display user's main page if he's already logged in.
 *
 */
@Controller
public class MainController {

    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private AuthUtils authUtils;

    @Autowired
    private PostManager postManager;

    @Autowired
    private FriendManager friendManager;

    @Autowired
    private MessageManager messageManager;

    /**
     * Redirects to feed. This needs to be done by POST request.
     * @return
     */
    @RequestMapping(value = "/feed/like", method = RequestMethod.GET)
    public String handleGetLike() {
        return "redirect:/feed";
    }

    /**
     * Handles liking a post. If the post was already liked by user, nothing happens.
     * @param postId Id of the post to be liked.
     * @param currentPage Current page of the post feed which is being displayed.
     * @return
     */
    @RequestMapping(value = "/feed/like", method = RequestMethod.POST)
    public String likePost(@RequestParam("postId") long postId, @RequestParam("currentPage") int currentPage) {
        User user = authUtils.getCurrentlyLoggedUser();
        Post post = postManager.getById(postId);

        if(post == null) {
            log.error("Post with id {} not found!", postId);
            return "redirect:/feed/"+currentPage;
        }

        if(postManager.alreadyLiked(user, post)) {
            log.warn("User {} already liked post {}.", user.getUsername(), postId);
            return "redirect:/feed/"+currentPage;
        }

        // like post
        postManager.likePost(user, post);

        return "redirect:/feed/"+currentPage;
    }

    /**
     * This method will check if user which is accessing /kivobook url is logged in. If not, redirects him to register page.
     * @return
     */
    @RequestMapping("/")
    public String displayFeedOrRedirect() {
        if(authUtils.getCurrentlyLoggedUser() != null) {
            return "redirect:/feed";
        } else {
            return "redirect:/register";
        }
    }

    @RequestMapping(value = "/feed/{page}", method = RequestMethod.GET)
    public String displayPostFeed(ModelMap modelMap, @PathVariable int page) {
        // load current user
        User currentUser = authUtils.getCurrentlyLoggedUserWithProfilePhoto();

        // load notifications
        int newFriendReq = friendManager.getNumberOfNewFriendRequests(currentUser);
        int newMsgs = messageManager.getNumberOfNewMessages(currentUser);

        // load post feed
        if(page < 0) {
            page = 0;
        }
        Pageable pageRequest = PageRequest.of(page, 10, new Sort(Sort.Direction.DESC, "datePosted", "timePosted"));
        Page<Post> postPage = postManager.listPostsForUser(currentUser, pageRequest);
        int lastPage = postPage.getTotalPages() == 0 ? 0 : postPage.getTotalPages() -1;
        PagingControl pagingControl = new PagingControl(page, lastPage);

        // add attributes for jsp
        modelMap.addAttribute("newFriendReq", newFriendReq);
        modelMap.addAttribute("newMsgs", newMsgs);
        modelMap.addAttribute("posts", postPage.getContent());
        modelMap.addAttribute("currentUser", currentUser);
        modelMap.addAttribute("allowedVisibilities", Visibility.getFormVisibilities());
        modelMap.addAttribute("newPost", new Post());
        modelMap.addAttribute("pageControls", pagingControl);

        // return the jsp name
        return "main-page";
    }

    /**
     * This method will redirect to /feed/0 which displays the first page of post feed.
     *
     * @return
     */
    @RequestMapping(value = "/feed", method = RequestMethod.GET)
    public String displayPostFeed() {
        return "redirect:/feed/0";
    }

    @RequestMapping(value = "/feed", method = RequestMethod.POST)
    public String createPost(@ModelAttribute("newPost") Post newPost, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            log.warn("Error occurred while binding registration form to Post object.");
            for(ObjectError error : bindingResult.getAllErrors()) {
                log.warn("Error: object name = {}; code = {}; toString = {}.",error.getObjectName(), error.getCode(), error.toString());
            }

            return "main-page";
        }
        User currentUser = authUtils.getCurrentlyLoggedUser();
        postManager.createNewPost(newPost.getText(), newPost.getVisibility(), currentUser);
        return "redirect:/feed";
    }
}
