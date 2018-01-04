package cz.zcu.pia.valesz.core.domain.vo;

import cz.zcu.pia.valesz.core.domain.Message;
import cz.zcu.pia.valesz.core.domain.User;

import java.util.List;

/**
 * Conversation groups together messages between two users.
 *
 * It is user as a wrapper in view layer.
 */
public class Conversation {

    /**
     * Database id.
     */
    private Long id;

    /**
     * The user who is displaying this conversation.
     */
    private User firstUser;

    /**
     * The other user (his name will be displayed as the conversation's label).
     */
    private User secondUser;

    /**
     * Messages in conversation. It should be ordered so that messages.get(0) will return the oldest message and
     * messages.last() will return the newest message.
     */
    private List<Message> messages;

    /**
     * Whether should be conversation displayed as new.
     */
    private boolean newFlag;

    public Conversation() {
    }

    public Conversation(Long id, User firstUser, User secondUser, List<Message> messages, boolean newFlag) {
        this.id = id;
        this.firstUser = firstUser;
        this.secondUser = secondUser;
        this.messages = messages;
        this.newFlag = newFlag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getFirstUser() {
        return firstUser;
    }

    public void setFirstUser(User firstUser) {
        this.firstUser = firstUser;
    }

    public User getSecondUser() {
        return secondUser;
    }

    public void setSecondUser(User secondUser) {
        this.secondUser = secondUser;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public boolean isNewFlag() {
        return newFlag;
    }

    public void setNewFlag(boolean newFlag) {
        this.newFlag = newFlag;
    }

    public Message getNewestMessage() {
        return messages.get(messages.size()-1);
    }

    public Message getOldestMessage() {
        return messages.get(0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Conversation that = (Conversation) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
