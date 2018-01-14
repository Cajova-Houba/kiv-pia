package cz.zcu.pia.valesz.web;

import cz.zcu.pia.valesz.core.domain.User;
import cz.zcu.pia.valesz.core.domain.vo.Conversation;
import cz.zcu.pia.valesz.core.service.AuthUtils;
import cz.zcu.pia.valesz.core.service.MessageManager;
import cz.zcu.pia.valesz.core.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
     * Displays the base page with messages overview.
     * If there are messages, the newest one will be displayed. Otherwise message 'No messages' will ebe displayed.
     *
     * @param modelMap
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String displayPage(ModelMap modelMap) {

        User currentUser = authUtils.getCurrentlyLoggerUser();
        List<Conversation> conversations = messageManager.listConversations(currentUser);
        Conversation conversation;
        if(conversations.isEmpty()) {
            conversation = null;
        } else {
            Conversation firstSelected = conversations.get(0);
            conversation = messageManager.getConversation(currentUser, firstSelected.getOtherUser(currentUser));

            // if the last message in conversation was sent to the current user, mark it as read
            if(conversation.getNewestMessage().getReceiver().equals(currentUser)) {
                messageManager.markAsRead(conversation.getNewestMessage());
            }
        }

        modelMap.addAttribute("currentUser", currentUser);
        modelMap.addAttribute("conversations", conversations);
        modelMap.addAttribute("conversation", conversation);

        return "messages";
    }
}
