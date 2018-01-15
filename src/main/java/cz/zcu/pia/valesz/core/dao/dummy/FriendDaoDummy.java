package cz.zcu.pia.valesz.core.dao.dummy;

import cz.zcu.pia.valesz.core.dao.FriendDao;
import cz.zcu.pia.valesz.core.domain.FriendRequest;
import cz.zcu.pia.valesz.core.domain.FriendRequestState;
import cz.zcu.pia.valesz.core.domain.User;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public class FriendDaoDummy extends Dummygeneric<FriendRequest, Long> implements FriendDao {

    private static List<FriendRequest> friendRequestsRepository = new ArrayList<>();

    @Override
    public List<FriendRequest> findUsersFriendRequests(User user, FriendRequestState friendRequestState) {
        return null;
    }

    @Override
    public List<FriendRequest> findByReceiverAndFriendRequestState(User receiver, FriendRequestState friendRequestState) {
        switch (friendRequestState) {
            case PENDING:
                return listNewFriendRequests(receiver);
            case ACCEPTED:
                return listFriendships(receiver);
            default:
                return new ArrayList<>();
        }
    }

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
    public void delete(FriendRequest deleted) {

    }

    @Override
    public List<FriendRequest> findAll() {
        return null;
    }

    @Override
    public Optional<FriendRequest> findOne(Long id) {
        return Optional.ofNullable(get(id));
    }

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

//    @Override
    public void delete(Long key) {
        Iterator<FriendRequest> frIterator = friendRequestsRepository.iterator();
        while (frIterator.hasNext()) {
            if(frIterator.next().getId() == key) {
                frIterator.remove();
            }
        }
    }

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
