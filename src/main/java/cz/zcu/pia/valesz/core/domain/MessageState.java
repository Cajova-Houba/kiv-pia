package cz.zcu.pia.valesz.core.domain;

/**
 * Possible states of a message.
 */
public enum MessageState {

    /**
     * Default state.
     */
    CREATED,

    /**
     * Message was created and sent to receiver who haven't read it yet.
     */
    SENT,

    /**
     * Receiver has read the message.
     */
    READ
}
