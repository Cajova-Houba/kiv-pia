package cz.zcu.pia.valesz.core.dao;

import cz.zcu.pia.valesz.core.domain.FriendRequest;
import cz.zcu.pia.valesz.core.domain.FriendRequestState;
import cz.zcu.pia.valesz.core.domain.User;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * DAO for friend related stuff.
 */
@NoRepositoryBean
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
}
