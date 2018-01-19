package cz.zcu.pia.valesz.web;

import cz.zcu.pia.valesz.core.domain.FriendRequest;
import cz.zcu.pia.valesz.core.domain.Message;
import cz.zcu.pia.valesz.core.domain.User;
import cz.zcu.pia.valesz.core.service.AuthUtils;
import cz.zcu.pia.valesz.core.service.FriendManager;
import cz.zcu.pia.valesz.core.service.MessageManager;
import cz.zcu.pia.valesz.core.service.UserManager;
import cz.zcu.pia.valesz.web.vo.ConversationVO;
import cz.zcu.pia.valesz.web.vo.NewConversationForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller which handles displaying and sending user's messages.
 */
@Controller
@RequestMapping("/messages")
public class MessagesController {

    private static final Logger log = LoggerFactory.getLogger(MessagesController.class);

    @Autowired
    private AuthUtils authUtils;

    @Autowired
    private UserManager userManager;

    @Autowired
    private MessageManager messageManager;

    @Autowired
    private FriendManager friendManager;

    /**
     * This method will handle the creation of new conversation between some receiver and currently logged user.
     * If there's already some conversation between those two, new message will just be added to it.
     *
     * @param conversationForm
     * @return
     */
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String handleNewMessage(@ModelAttribute("newConversationModel") NewConversationForm conversationForm) {
        log.info("Creating new conversation.");

        User currentUser = authUtils.getCurrentlyLoggedUser();
        String username = conversationForm.getReceiverUsername();
        // user isn't able to input these values via standard form
        if(username == null || username.isEmpty()) {
            log.warn("User {} attempted to send a new message without specified receiver.", currentUser.getUsername());
            return "redirect:/messages";
        }

        // check if receiver exists
        User receiver = userManager.loadByUsername(username);
        if(receiver == null) {
            log.warn("User {} attempted to send a new message to non existent user {}.", currentUser.getUsername(), username);
            return "redirect:/messages";
        }

        // check message text - again, user shouldn't be able to input those values
        String messageText = conversationForm.getMessageText();
        if(messageText == null
                || messageText.isEmpty()
                || messageText.length() > Message.MAX_MESSAGE_LENGTH) {
            log.warn("User {} attempted to sent empty or too long message.", currentUser.getUsername());
            return "redirect:/messages";
        }

        Message m = new Message(currentUser, receiver, messageText);
        messageManager.sendMessage(m);

        log.info("Message sent.");

        return "redirect:/messages/"+username;
    }

    /**
     * Displays a page with form to send a new message.
     *
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String displayNewMessageForm(ModelMap modelMap) {
        User currentUser = authUtils.getCurrentlyLoggedUser();
        List<ConversationVO> conversations = messageManager.listConversations(currentUser);

        // prepare possible receivers of a new message
        // those are user's friends
        List<FriendRequest> friends = friendManager.listFriendshipsWithoutProfilePhotos(currentUser);
        Map<String, String> possibleReceivers = new HashMap<>();
        for(FriendRequest fr : friends) {
            User otherUser = fr.getOtherUser(currentUser);
            possibleReceivers.put(otherUser.getUsername(), otherUser.getFullName());
        }

        modelMap.addAttribute("currentUser", currentUser);
        modelMap.addAttribute("conversations", conversations);
        modelMap.addAttribute("isNewConv", true);
        modelMap.addAttribute("possibleReceivers", possibleReceivers);
        modelMap.addAttribute("newConversationModel", new NewConversationForm());
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
        // mark as read any unread messages received by current user
        // so that if more messages are sent, all of them are marked as read
        if(!currentConversation.getMessages().isEmpty() ) {
            for(Message unreadMessage : currentConversation.listUnreadMessagesForUser(currentUser)) {
                messageManager.markAsRead(unreadMessage);
            }
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
