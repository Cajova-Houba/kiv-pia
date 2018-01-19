package cz.zcu.pia.valesz.core.service.impl;

import cz.zcu.pia.valesz.core.dao.FriendDao;
import cz.zcu.pia.valesz.core.domain.FriendRequest;
import cz.zcu.pia.valesz.core.domain.FriendRequestState;
import cz.zcu.pia.valesz.core.domain.User;
import cz.zcu.pia.valesz.core.service.FriendManager;
import cz.zcu.pia.valesz.core.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Arrays;
import java.util.List;

public class FriendManagerImpl implements FriendManager {

    @Autowired
    @Qualifier("friendDao")
    private FriendDao friendDao;

    @Autowired
    private UserManager userManager;

    @Override
    public FriendRequest sendFriendRequest(User sender, User receiver) {
        FriendRequest fr = new FriendRequest(sender, receiver);
        fr.setFriendRequestState(FriendRequestState.PENDING);
        return friendDao.save(fr);
    }

    @Override
    public FriendRequest acceptRequest(FriendRequest request) {
        return null;
    }

    @Override
    public FriendRequest declineRequest(FriendRequest request) {
        return null;
    }

    @Override
    public FriendRequest cancelRequest(FriendRequest request) {
        return null;
    }

    @Override
    public int getNumberOfNewFriendRequests(User user) {
        return listNewFriendRequests(user).size();
    }

    @Override
    public List<FriendRequest> listNewFriendRequests(User user) {

        return friendDao.findByReceiverAndFriendRequestState(user, FriendRequestState.PENDING);
    }

    @Override
    public List<FriendRequest> listFriendships(User user) {
       return friendDao.findByReceiverAndFriendRequestState(user, FriendRequestState.ACCEPTED);
    }

    @Override
    public boolean isFriendOf(User user1, User user2) {
        return friendDao.findByUsersAndState(user1, user2, FriendRequestState.ACCEPTED) != null;
    }

    @Override
    public boolean connectionExists(User user1, User user2) {
        if(user1.equals(user2)) {
            return true;
        }

        // there's at least one ACCEPTED/PENDING request => connection exists
        List<FriendRequest> requests = friendDao.findConnectionByUsers(user1, user2, Arrays.asList(FriendRequestState.ACCEPTED, FriendRequestState.PENDING));
        return !requests.isEmpty();
    }
}
