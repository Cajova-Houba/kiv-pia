package cz.zcu.pia.valesz.core.service.impl;

import cz.zcu.pia.valesz.core.dao.FriendDao;
import cz.zcu.pia.valesz.core.domain.FriendRequest;
import cz.zcu.pia.valesz.core.domain.FriendRequestState;
import cz.zcu.pia.valesz.core.domain.User;
import cz.zcu.pia.valesz.core.service.FriendManager;
import cz.zcu.pia.valesz.core.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class FriendManagerImpl implements FriendManager {

    @Autowired
    @Qualifier("friendDaoDummy")
    private FriendDao friendDao;

    @Autowired
    private UserManager userManager;

    @Override
    public FriendRequest sendFriendRequest(User sender, User receiver) {
        return null;
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
    public int getNumberOfNewFriendRequests() {
        return listNewFriendRequests(userManager.getCurrentlyLoggerUser()).size();
    }

    @Override
    public List<FriendRequest> listNewFriendRequests(User user) {
        List<FriendRequest> frs = friendDao.listNewFriendRequests(user);
        if(frs.isEmpty()) {
            createDummyRequests();
            frs.addAll(friendDao.listNewFriendRequests(user));
        }

        return frs;
    }

    @Override
    public List<FriendRequest> listFriendships(User user) {
        List<FriendRequest> friends = friendDao.listFriendships(user);
        if(friends.isEmpty()) {
            createDummyFriends();
            friends.addAll(friendDao.listFriendships(user));
        }

        return friends;
    }

    /**
     * Creates dummy requests and saves them..
     */
    private void createDummyRequests() {
        friendDao.save(new FriendRequest(1L, userManager.loadByUsername("Nový kamarád"), userManager.getCurrentlyLoggerUser(), FriendRequestState.PENDING));
        friendDao.save(new FriendRequest(0L, userManager.loadByUsername("Nová kamarádka"), userManager.getCurrentlyLoggerUser(), FriendRequestState.PENDING));
    }

    /**
     * Create dummy friendships
     */
    private void createDummyFriends() {
        friendDao.save(new FriendRequest(-1L, userManager.loadByUsername("Kámoš"), userManager.getCurrentlyLoggerUser(), FriendRequestState.ACCEPTED));
        friendDao.save(new FriendRequest(-2L, userManager.loadByUsername("Hustej Uživatel"), userManager.getCurrentlyLoggerUser(), FriendRequestState.ACCEPTED));
        friendDao.save(new FriendRequest(-1L, userManager.loadByUsername("Maminečka"), userManager.getCurrentlyLoggerUser(), FriendRequestState.ACCEPTED));
        friendDao.save(new FriendRequest(-2L, userManager.loadByUsername("Někdo úplně jinej"), userManager.getCurrentlyLoggerUser(), FriendRequestState.ACCEPTED));
        friendDao.save(new FriendRequest(-1L, userManager.loadByUsername("Spolužák Petr"), userManager.getCurrentlyLoggerUser(), FriendRequestState.ACCEPTED));
        friendDao.save(new FriendRequest(-2L, userManager.loadByUsername("Kamarádka Míša"), userManager.getCurrentlyLoggerUser(), FriendRequestState.ACCEPTED));
        friendDao.save(new FriendRequest(-1L, userManager.loadByUsername("Kolega Pavel"), userManager.getCurrentlyLoggerUser(), FriendRequestState.ACCEPTED));
        friendDao.save(new FriendRequest(-2L, userManager.loadByUsername("Klára Nováková"), userManager.getCurrentlyLoggerUser(), FriendRequestState.ACCEPTED));
    }
}
