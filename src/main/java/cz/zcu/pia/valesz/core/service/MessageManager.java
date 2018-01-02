package cz.zcu.pia.valesz.core.service;

import cz.zcu.pia.valesz.core.domain.User;
import cz.zcu.pia.valesz.core.domain.vo.Conversation;

import java.util.List;

/**
 * Service for message management.
 */
public interface MessageManager {

    /**
     * Returns the number of new (unread) messages for  user.
     * @param user Number of new messages for this user will be returned.
     * @return Number of new messages.
     */
    int getNumberOfNewMessages(User user);

    /**
     * Returns a list of last messages from every sender.
     *
     * @param receiver Receiver of the messages.
     * @return Conversations.
     */
    List<Conversation> listConversations(User receiver);

    /**
     * List conversation between two users.
     *
     * @param currentUser Current user.
     * @param otherUser Other user.
     * @return Conversation between two users.
     */
    Conversation getConversation(User currentUser, User otherUser);
}
