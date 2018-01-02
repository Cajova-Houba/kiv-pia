package cz.zcu.pia.valesz.core.dao;

import cz.zcu.pia.valesz.core.domain.FriendRequest;
import cz.zcu.pia.valesz.core.domain.User;

import java.util.List;

/**
 * DAO for friend related stuff.
 */
public interface FriendDao extends GenericDao<FriendRequest, Long> {

    /**
     * Returns a list of friend requests to which user hasn't answered yet.,
     * @param user User to whom requests were sent.
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
