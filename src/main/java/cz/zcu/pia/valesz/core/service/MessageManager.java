package cz.zcu.pia.valesz.core.service;

import cz.zcu.pia.valesz.core.domain.Message;
import cz.zcu.pia.valesz.core.domain.User;
import cz.zcu.pia.valesz.web.vo.ConversationVO;

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
     * Returns a list of last messages from every sender wrapped in ConversationVO.
     * This method will also fetch profile photos of conversation participants.
     *
     * @param receiver Receiver of the messages.
     * @return Conversations.
     */
    List<ConversationVO> listConversations(User receiver);

    /**
     * List conversation between two users.
     *
     * @param currentUser Current user.
     * @param otherUser Other user.
     * @return Conversation between two users or null if there isn't any.
     */
    ConversationVO getConversation(User currentUser, User otherUser);

    /**
     * Marks message as read, saves it and returns it.
     * @param message Message to be marked as read.
     * @return Read message.
     */
    Message markAsRead(Message message);

    /**
     * Saves new message and marks it as last in the conversation.
     * If the conversation doesn't exist, new one will be created.
     * If the message text is longer than allowed maximum, it will be cut to match the allowed length.
     *
     * @param message Message to be saved.
     * @return Saved message.
     */
    Message sendMessage(Message message);
}
