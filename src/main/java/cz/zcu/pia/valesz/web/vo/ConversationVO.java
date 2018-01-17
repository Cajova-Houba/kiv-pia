package cz.zcu.pia.valesz.web.vo;

import cz.zcu.pia.valesz.core.domain.Message;
import cz.zcu.pia.valesz.core.domain.User;

import java.util.List;

/**
 * Wrapper for conversations.
 *
 * Conversation groups together messages between two users.
 *
 */
public class ConversationVO {

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

    public ConversationVO() {
    }

    public ConversationVO(User firstUser, User secondUser, List<Message> messages, boolean newFlag) {
        this.firstUser = firstUser;
        this.secondUser = secondUser;
        this.messages = messages;
        this.newFlag = newFlag;
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

    /**
     * If provided user object is first user, the second one is returned, otherwise the first one is returned.
     * If the provided user object doesn't match neither the first nor the second user, null is returned.
     * @param user User.
     * @return Other user.
     */
    public User getOtherUser(User user) {
        if(getFirstUser().equals(user)) {
            return getSecondUser();
        } else if (getSecondUser().equals(user)) {
            return getFirstUser();
        } else {
            return null;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConversationVO that = (ConversationVO) o;

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
