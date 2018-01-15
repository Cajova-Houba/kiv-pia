package cz.zcu.pia.valesz.core.dao;

import cz.zcu.pia.valesz.core.domain.FriendRequest;
import cz.zcu.pia.valesz.core.domain.FriendRequestState;
import cz.zcu.pia.valesz.core.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * DAO for friend related stuff.
 */
public interface FriendDao extends GenericDao<FriendRequest, Long> {

    /**
     * Returns a list of friend request by receiver with particular state.
     *
     * @param receiver Receiver of friend requests.
     * @param friendRequestState State of requests. Use PENDING to find all new friend requests by receiver and ACCEPTED to
     *                           find all friendships of a receiver.
     * @return List of friend requests.
     */
    List<FriendRequest> findByReceiverAndFriendRequestState(User receiver, FriendRequestState friendRequestState);

    /**
     * Returns a list of friend requests where user is receiver or sender.
     *
     * @param user User - sender or receiver.
     * @param friendRequestState Particular state.
     * @return Users friend requests.
     */
    @Query("SELECT fr FROM FriendRequest fr WHERE fr.friendRequestState = :friendRequestState AND (fr.receiver = :user OR fr.sender = :user)")
    List<FriendRequest> findUsersFriendRequests(@Param("user") User user, @Param("friendRequestState") FriendRequestState friendRequestState);
}
