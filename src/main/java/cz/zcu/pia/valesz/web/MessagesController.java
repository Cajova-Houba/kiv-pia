package cz.zcu.pia.valesz.web;

import cz.zcu.pia.valesz.core.domain.User;
import cz.zcu.pia.valesz.core.domain.vo.Conversation;
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
    private UserManager userManager;

    @Autowired
    private MessageManager messageManager;

    /**
     * Displays the base page with messages overview.
     * @param modelMap
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String displayPage(ModelMap modelMap) {

        User currentUser = userManager.loadByUsername("user1");
        User otherUser = userManager.loadByUsername("user5");
        List<Conversation> conversations = messageManager.listConversations(currentUser);
        Conversation conversation = messageManager.getConversation(currentUser, otherUser);

        modelMap.addAttribute("currentUser", currentUser);
        modelMap.addAttribute("conversations", conversations);
        modelMap.addAttribute("conversation", conversation);

        return "messages";
    }
}
