package cz.zcu.pia.valesz.core.service;

import cz.zcu.pia.valesz.core.domain.FriendRequest;
import cz.zcu.pia.valesz.core.domain.User;

import java.util.List;

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

    /**
     * Returns the number of new friend requests for currently logged user.
     * @return Number of new requests.
     */
    int getNumberOfNewFriendRequests();

    /**
     * Returns a list of friend requests to which user hasn't responded yet.,
     * @param user User whose new requests will be listed.
     * @return List of new requests.
     */
    List<FriendRequest> listNewFriendRequests(User user);

    /**
     * Lists friendships of a user. Those are accepted requests where the user is receiver.
     * @param user Requests with this user as receiver will be listed.
     * @return Accepted requests.
     */
    List<FriendRequest> listFriendships(User user);
}
