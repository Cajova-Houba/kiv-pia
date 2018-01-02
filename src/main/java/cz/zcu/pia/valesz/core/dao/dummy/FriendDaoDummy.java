package cz.zcu.pia.valesz.core.dao.dummy;

import cz.zcu.pia.valesz.core.dao.FriendDao;
import cz.zcu.pia.valesz.core.domain.FriendRequest;
import cz.zcu.pia.valesz.core.domain.User;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FriendDaoDummy implements FriendDao {

    private static List<FriendRequest> friendRequestsRepository = new ArrayList<>();

    @Override
    public List<FriendRequest> listNewFriendRequests(User user) {
        List<FriendRequest> requests = new ArrayList<>();
        for(FriendRequest fr : friendRequestsRepository) {
            if(fr.getReceiver().equals(user) && fr.isNew()) {
                requests.add(fr);
            }
        }
        return requests;
    }

    @Override
    public FriendRequest get(Long key) {
        for(FriendRequest fr : friendRequestsRepository) {
            if(fr.getId() == key) {
                return fr;
            }
        }
        return null;
    }

    @Override
    public FriendRequest save(FriendRequest object) {
        friendRequestsRepository.add(object);
        return object;
    }

    @Override
    public void delete(Long key) {
        Iterator<FriendRequest> frIterator = friendRequestsRepository.iterator();
        while (frIterator.hasNext()) {
            if(frIterator.next().getId() == key) {
                frIterator.remove();
            }
        }
    }

    @Override
    public List<FriendRequest> listFriendships(User user) {
        List<FriendRequest> requests = new ArrayList<>();
        for(FriendRequest fr : friendRequestsRepository) {
            if(fr.getReceiver().equals(user) && fr.isAccepted()) {
                requests.add(fr);
            }
        }
        return requests;
    }
}
