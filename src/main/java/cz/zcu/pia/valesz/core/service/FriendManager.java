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
    FriendRequest rejectRequest(FriendRequest request);

    /**
     * Cancels given request. If the request isn't in pending state, exception will be thrown.
     * @param request Request to be canceled.
     * @return Canceled request object.
     */
    FriendRequest cancelRequest(FriendRequest request);

    /**
     * Returns the number of new friend requests for user.
     *
     * @param user User whose new friend requests will be counted.
     * @return Number of new requests.
     */
    int getNumberOfNewFriendRequests(User user);

    /**
     * Returns a list of friend requests to which user hasn't responded yet.
     * Profile photos are returned together with users in friend requests.
     *
     * @param user User whose new requests will be listed.
     * @return List of new requests.
     */
    List<FriendRequest> listNewFriendRequests(User user);

    /**
     * Returns a list of frind request which were sent by user and weren't accepted yet.
     * Profile photos are returned together with users in friend requests.
     *
     * @param user User whose pending requests will be returned.
     * @return Pending requests sent by user.
     */
    List<FriendRequest> listPendingRequestSentByUser(User user);

    /**
     * Lists friendships of a user. Those are accepted requests where the user is receiver or sender.
     * Profile photos are returned together with user in friend requests.
     *
     * @param user Requests with this user as receiver or sender will be listed.
     * @return Accepted requests.
     */
    List<FriendRequest> listFriendships(User user);

    /**
     * Lists friendships of a user. Those are accepted requests where the user is either receiver or sender.
     * Users are fetched with requests, but not their profile photos.
     *
     * @param user Accepted requests with this user as a receiver or sender will be listed.
     * @return Accepted requests.
     */
    List<FriendRequest> listFriendshipsWithoutProfilePhotos(User user);

    /**
     * Returns true if there's a friend request with accepted state between provided users.
     *
     * @param user1 User 1.
     * @param user2 User 2.
     * @return True if users are friends.
     */
    boolean isFriendOf(User user1, User user2);

    /**
     * Returns true if there's a friend requests (any state) between two users.
     * If user1.equals(user2), true is returned.
     *
     * @param user1 User 1.
     * @param user2 User 2.
     * @return True if there's any request between two users.
     */
    boolean connectionExists(User user1, User user2);

    /**
     * Returns friend request with this id. Sender and receiver are fetched.
     *
     * @param id Id.
     * @return Friend request or null if it doesn't exist.
     */
    FriendRequest findByIdFetchUsers(long id);
}
