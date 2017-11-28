package cz.zcu.pia.valesz.core.domain;

/**
 * Possible states of friend request.
 */
public enum FriendRequestState {

    /**
     * Default state.
     */
    CREATED,

    /**
     * Request is waiting to be either accepted or rejected by user.
     */
    PENDING,

    /**
     * Accepted request.
     */
    ACCEPTED,

    /**
     * Rejected request.
     */
    REJECTED,

    /**
     * When the sender cancels request. Possible only if the current state is PENDING.
     */
    CANCELED,
}
