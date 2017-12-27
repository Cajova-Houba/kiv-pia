package cz.zcu.pia.valesz.core.service.impl;

import cz.zcu.pia.valesz.core.domain.FriendRequest;
import cz.zcu.pia.valesz.core.domain.User;
import cz.zcu.pia.valesz.core.service.FriendManager;

public class FriendManagerImpl implements FriendManager {

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
        return 2;
    }
}
