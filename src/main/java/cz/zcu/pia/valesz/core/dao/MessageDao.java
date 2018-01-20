package cz.zcu.pia.valesz.core.dao;

import cz.zcu.pia.valesz.core.domain.Message;
import cz.zcu.pia.valesz.core.domain.MessageState;
import cz.zcu.pia.valesz.core.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * DAO for messaging.
 */
public interface MessageDao extends GenericDao<Message, Long> {

    @Override
    @Query("SELECT m FROM Message m LEFT JOIN FETCH m.receiver LEFT JOIN FETCH m.sender WHERE m.id = :id")
    Message getOne(@Param("id") Long id);

    /**
     * Returns all messages between user1 and user2, chronologically ordered.
     * Profile photos are not fetched together with conversation participants.
     *
     * @param user1 First user.
     * @param user2 Second user.
     * @return List of messages between two users.
     */
    @Query("  SELECT m FROM Message m " +
            " LEFT JOIN FETCH m.receiver mr " +
            " LEFT JOIN FETCH m.sender ms " +
            " WHERE " +
            " (m.receiver = :u1 AND m.sender = :u2) " +
            " OR (m.receiver = :u2 AND m.sender = :u1) " +
            " ORDER BY m.timestamp ASC ")
    List<Message> getConversation(@Param("u1") User user1, @Param("u2") User user2);

    /**
     * Returns the number of messages for  user.
     *
     * @param user Number of new messages for this user will be returned.
     * @param state State of counted messages. Use 'SENT' to get number of unread messages.
     * @return Number of new messages.
     */
    @Query("SELECT COUNT(m.id) FROM Message m WHERE m.receiver = :user AND m.state = :state")
    int countByReceiverAndState(@Param("user") User user, @Param("state") MessageState state);
}
