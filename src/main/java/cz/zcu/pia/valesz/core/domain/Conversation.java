package cz.zcu.pia.valesz.core.domain;

import javax.persistence.*;

/**
 * Entity which holds data about conversations between users.
 * Conversation groups together messages between users.
 *
 * The combination of firstUser and secondUser is unique.
 */
@Entity
@Table(name = "conversation")
public class Conversation {

    /**
     * Database id.
     */
    private Long id;

    /**
     * User who started the conversation.
     */
    private User firstUser;

    /**
     * Other user in conversation.
     */
    private User secondUser;

    /**
     * Last message in conversation.
     */
    private Message lastMessage;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "first_user_id", nullable = false)
    public User getFirstUser() {
        return firstUser;
    }

    public void setFirstUser(User firstUser) {
        this.firstUser = firstUser;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "second_user_id", nullable = false)
    public User getSecondUser() {
        return secondUser;
    }

    public void setSecondUser(User secondUser) {
        this.secondUser = secondUser;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "last_message_id", nullable = false)
    public Message getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(Message lastMessage) {
        this.lastMessage = lastMessage;
    }

    @Override
    public String toString() {
        return "Conversation{" +
                "id=" + id +
                ", firstUser=" + firstUser +
                ", secondUser=" + secondUser +
                ", lastMessage=" + lastMessage +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Conversation that = (Conversation) o;

        if (firstUser != null ? !firstUser.equals(that.firstUser) : that.firstUser != null) return false;
        return secondUser != null ? secondUser.equals(that.secondUser) : that.secondUser == null;
    }

    @Override
    public int hashCode() {
        int result = firstUser != null ? firstUser.hashCode() : 0;
        result = 31 * result + (secondUser != null ? secondUser.hashCode() : 0);
        return result;
    }
}
