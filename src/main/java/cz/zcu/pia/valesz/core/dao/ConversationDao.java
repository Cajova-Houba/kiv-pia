package cz.zcu.pia.valesz.core.dao;

import cz.zcu.pia.valesz.core.domain.Conversation;
import cz.zcu.pia.valesz.core.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * DAO for fetching conversations.
 */
public interface ConversationDao extends GenericDao<Conversation, Long> {

    /**
     * Returns conversation object between user 1 and user 2.
     *
     * @param user1 User1 - doesn't have to be firstUser.
     * @param user2 User2 - doesn't have to be secondUser.
     * @return Conversation if it exists.
     */
    @Query("  SELECT c FROM Conversation c " +
            " LEFT JOIN FETCH c.firstUser " +
            " LEFT JOIN FETCH c.secondUser " +
            " WHERE " +
            " (c.firstUser = :user1 AND c.secondUser = :user2)" +
            " OR (c.firstUser = :user2 AND c.secondUser = :user1)")
    Conversation getConversationBetweenUsers(@Param("user1") User user1, @Param("user2") User user2);

    /**
     * Returns a list of conversations between two users. Conversations participants are fetched
     * together with their profile photos.
     * Profiles photos are fetched together with user objects.
     * Conversation are ordered chronologically (the one with the newest message is the first).
     *
     * @param receiver Receiver/sender of the messages.
     * @return List of last messages from conversations user had ever took part in.
     */
    @Query("  SELECT c FROM Conversation c " +
            " LEFT JOIN FETCH c.lastMessage lm" +
            " LEFT JOIN FETCH c.firstUser fu " +
            " LEFT JOIN FETCH c.secondUser su " +
            " LEFT JOIN FETCH fu.profilePhoto " +
            " LEFT JOIN FETCH su.profilePhoto " +
            " WHERE " +
            " c.firstUser = :user OR c.secondUser = :user" +
            " ORDER BY lm.timestamp DESC ")
    List<Conversation> listConversationsByUser(@Param("user") User receiver);
}
