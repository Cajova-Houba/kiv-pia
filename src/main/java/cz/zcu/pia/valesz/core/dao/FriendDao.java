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
    @Query("  SELECT fr FROM FriendRequest fr " +
            " LEFT JOIN FETCH fr.receiver rec " +
            " LEFT JOIN FETCH fr.sender sen " +
            " LEFT JOIN FETCH rec.profilePhoto " +
            " LEFT JOIN FETCH sen.profilePhoto " +
            " WHERE " +
            " fr.friendRequestState = :friendRequestState " +
            " AND fr.receiver = :receiver")
    List<FriendRequest> findByReceiverAndFriendRequestState(@Param("receiver") User receiver,@Param("friendRequestState") FriendRequestState friendRequestState);

    /**
     * Returns a list of friend requests where user is receiver or sender.
     *
     * @param user User - sender or receiver.
     * @param friendRequestState Particular state.
     * @return Users friend requests.
     */
    @Query("  SELECT fr FROM FriendRequest fr " +
            " LEFT JOIN FETCH fr.receiver " +
            " LEFT JOIN FETCH fr.sender " +
            " WHERE " +
            " fr.friendRequestState = :friendRequestState " +
            " AND (fr.receiver = :user OR fr.sender = :user)")
    List<FriendRequest> findUsersFriendRequests(@Param("user") User user, @Param("friendRequestState") FriendRequestState friendRequestState);

    /**
     * Returns friend request (if it exists) for two users with particualr state.
     *
     * @param user1 User 1.
     * @param user2 User 2.
     * @param friendRequestState State of the request.
     * @return Friend request.
     */
    @Query("  SELECT fr FROM FriendRequest fr " +
            " LEFT JOIN FETCH fr.receiver " +
            " LEFT JOIN FETCH fr.sender " +
            " WHERE " +
            " fr.friendRequestState = :friendRequestState " +
            " AND (" +
            " (fr.receiver = :user1 AND fr.sender = :user2) " +
            "  OR (fr.receiver = :user2 AND fr.sender = :user1)" +
            " )")
    FriendRequest findByUsersAndState(@Param("user1") User user1, @Param("user2") User user2, @Param("friendRequestState") FriendRequestState friendRequestState);
}
