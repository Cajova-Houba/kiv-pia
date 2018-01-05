package cz.zcu.pia.valesz.core.dao;

import cz.zcu.pia.valesz.core.domain.Message;
import cz.zcu.pia.valesz.core.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * DAO for message stuff.
 */
@NoRepositoryBean
public interface MessageDao extends GenericDao<Message, Long> {

    /**
     * Returns a list of last messages from every sender.
     *
     * @param receiver Receiver of the messages.
     * @return Conversations.
     */
    @Query("SELECT m FROM Message m")
    List<Message> listConversations(User receiver);

    /**
     * Returns the number of new (unread) messages for  user.
     * Only 1 message per sender is counted.
     *
     * @param user Number of new messages for this user will be returned.
     * @return Number of new messages.
     */
    @Query("SELECT COUNT(id) FROM Message ")
    int getNumberOfNewMessages(User user);
}
