package cz.zcu.pia.valesz.core.service;

import cz.zcu.pia.valesz.core.domain.FriendRequest;
import cz.zcu.pia.valesz.core.domain.User;

/**
 * Interface for friend-related actions.
 */
public interface FriendManager {

    /**
     * Sends new friend request to receiver.
     *
     * @param sender User which is requesting the friendship.
     * @param receiver User which will accept / reject friendship.
     * @return Created request object.
     */
    FriendRequest sendFriendRequest(User sender, User receiver);

    /**
     * Accepts given friend request.
     *
     * @param request Request to be accepted.
     * @return Accepted request object.
     */
    FriendRequest acceptRequest(FriendRequest request);

    /**
     * Declines given friend request.
     * @param request Request to be declined.
     * @return Declined request object.
     */
    FriendRequest declineRequest(FriendRequest request);

    /**
     * Cancels given request. If the request isn't in pending state, exception will be thrown.
     * @param request Request to be canceled.
     * @return Canceled request object.
     */
    FriendRequest cancelRequest(FriendRequest request);
}
