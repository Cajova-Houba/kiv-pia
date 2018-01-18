package cz.zcu.pia.valesz.web;

import cz.zcu.pia.valesz.core.domain.Message;
import cz.zcu.pia.valesz.core.domain.User;
import cz.zcu.pia.valesz.core.service.AuthUtils;
import cz.zcu.pia.valesz.core.service.MessageManager;
import cz.zcu.pia.valesz.core.service.UserManager;
import cz.zcu.pia.valesz.web.vo.ConversationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Controller which handles displaying and sending user's messages.
 */
@Controller
@RequestMapping("/messages")
public class MessagesController {

    @Autowired
    private AuthUtils authUtils;

    @Autowired
    private UserManager userManager;

    @Autowired
    private MessageManager messageManager;

    /**
     * Displays a page with form to send a new message.
     *
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String displayNewMessageForm(ModelMap modelMap) {
        return "messages";
    }

    /**
     * Handles a new message being sent to existing conversation.
     *
     * @param username Currently logged user and this user are participants in conversation.
     * @return
     */
    @RequestMapping(value = "/{username}", method = RequestMethod.POST)
    public String handleNewMessageInConversation(@PathVariable String username, @RequestParam("msgText") String messageText) {

        User currentUser = authUtils.getCurrentlyLoggedUser();
        User otherUser = userManager.loadByUsername(username);

        // check
        if(otherUser == null) {
            return "redirect:/messages";
        }
        if(messageText.isEmpty()) {
           return "redirect:/messages/"+username;
        }

        // send message
        Message message = new Message(currentUser, otherUser, messageText);
        messageManager.sendMessage(message);

        // redirect back to conversation
        return "redirect:/messages/"+username;
    }

    /**
     * Displays messages between the currently logged user and user giver by 'username' path variable.
     *
     * If the other user doesn't exist, method will redirect to /messages page which should either display empty
     * message page or redirect to correct conversation.
     *
     * @param modelMap
     * @param username Username of the other user.
     * @return
     */
    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public String displayMessages(ModelMap modelMap, @PathVariable String username) {

        User currentUser = authUtils.getCurrentlyLoggedUserWithProfilePhoto();
        User otherUser = userManager.loadByUsername(username, true);

        if(otherUser == null) {
            return "redirect:/messages";
        }

        // load main conversation
        ConversationVO currentConversation = messageManager.getConversation(currentUser, otherUser);
        // if the last message in conversation was sent to the current user, mark it as read
        if(!currentConversation.getMessages().isEmpty() && currentConversation.getNewestMessage().getReceiver().equals(currentUser)) {
            messageManager.markAsRead(currentConversation.getNewestMessage());
        }

        // load other conversations
        List<ConversationVO> conversations = messageManager.listConversations(currentUser);

        // add attributes for jsp
        modelMap.addAttribute("currentUser", currentUser);
        modelMap.addAttribute("conversations", conversations);
        modelMap.addAttribute("conversation", currentConversation);

        // return view name
        return "messages";
    }

    /**
     * Loads currently logged user's conversations, selects the first one a redirects to /messages/{username} where
     * {username} is the username of the other user form the selected conversation.
     *
     * If the currently logged user has no conversations, empty message page will be displayed.
     *
     * @param modelMap
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String displayPage(ModelMap modelMap) {

        User currentUser = authUtils.getCurrentlyLoggedUser();
        List<ConversationVO> conversations = messageManager.listConversations(currentUser);
        ConversationVO conversation;
        if(conversations.isEmpty()) {
            conversation = null;
        } else {
            // select first conversation and redirect to it
            ConversationVO firstSelected = conversations.get(0);
            return "redirect:/messages/"+firstSelected.getOtherUser(currentUser).getUsername();
        }

        modelMap.addAttribute("currentUser", currentUser);
        modelMap.addAttribute("conversations", conversations);
        modelMap.addAttribute("conversation", conversation);

        return "messages";
    }
}
