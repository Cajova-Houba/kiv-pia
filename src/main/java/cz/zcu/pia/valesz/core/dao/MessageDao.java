package cz.zcu.pia.valesz.core.dao;

import cz.zcu.pia.valesz.core.domain.Message;
import cz.zcu.pia.valesz.core.domain.User;

import java.util.List;

/**
 * DAO for message stuff.
 */
public interface MessageDao extends GenericDao<Message, Long> {

    /**
     * Returns a list of last messages from every sender.
     *
     * @param receiver Receiver of the messages.
     * @return Conversations.
     */
    List<Message> listConversations(User receiver);

    /**
     * Returns the number of new (unread) messages for  user.
     * Only 1 message per sender is counted.
     *
     * @param user Number of new messages for this user will be returned.
     * @return Number of new messages.
     */
    int getNumberOfNewMessages(User user);
}
