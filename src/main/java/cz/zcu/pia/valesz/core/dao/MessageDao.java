package cz.zcu.pia.valesz.core.dao;

import cz.zcu.pia.valesz.core.domain.Message;
import cz.zcu.pia.valesz.core.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * DAO for message stuff.
 */
public interface MessageDao extends GenericDao<Message, Long> {

    @Override
    @Query("SELECT m FROM Message m LEFT JOIN FETCH m.receiver LEFT JOIN FETCH m.sender WHERE m.id = :id")
    Message getOne(@Param("id") Long id);

    /**
     * Returns a list of last messages from every sender.
     *
     * @param receiver Receiver of the messages.
     * @return Conversations.
     */
    @Query("SELECT m FROM Message m LEFT JOIN FETCH m.receiver LEFT JOIN FETCH m.sender")
    List<Message> listConversations(User receiver);

    /**
     * Returns all messages between user1 and user2, chronologically ordered.
     * @param user1 First user.
     * @param user2 Second user.
     * @return List of messages between two users.
     */
    @Query("SELECT m FROM Message m LEFT JOIN FETCH m.receiver LEFT JOIN FETCH m.sender WHERE (m.receiver = :u1 AND m.sender = :u2) OR (m.receiver = :u2 AND m.sender = :u1) ORDER BY m.timestamp")
    List<Message> getConversation(@Param("u1") User user1, @Param("u2") User user2);

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
