package cz.zcu.pia.valesz.core.service;

/**
 * Service for message management.
 */
public interface MessageManager {

    /**
     * Returns the number of new (unread) messages for currently logged user.
     * @return Number of new messages.
     */
    int getNumberOfNewMessages();
}
