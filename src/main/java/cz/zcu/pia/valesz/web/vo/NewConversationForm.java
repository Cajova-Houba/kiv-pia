package cz.zcu.pia.valesz.web.vo;

/**
 * Simple for the first message in conversation (when starting a new one).
 */
public class NewConversationForm {

    /**
     * Receiver's username.
     */
    private String receiverUsername;

    /**
     * Message text.
     */
    private String messageText;

    public String getReceiverUsername() {
        return receiverUsername;
    }

    public void setReceiverUsername(String receiverUsername) {
        this.receiverUsername = receiverUsername;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }
}
