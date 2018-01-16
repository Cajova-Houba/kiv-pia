package cz.zcu.pia.valesz.core.dao.impl;

import cz.zcu.pia.valesz.core.dao.PostDaoCustom;
import cz.zcu.pia.valesz.core.domain.FriendRequest;
import cz.zcu.pia.valesz.core.domain.Post;
import cz.zcu.pia.valesz.core.domain.User;
import cz.zcu.pia.valesz.core.domain.Visibility;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PostDaoCustomImpl implements PostDaoCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Post> listPostsForUser(User user, List<FriendRequest> usersFriendships) {
        String query = "SELECT p FROM "+Post.class.getSimpleName()+" p " +
                " WHERE " +
                " p.visibility IN :visibleEveryoneRegistered " +
                " OR ( " +
                "       p.visibility = :visibleOwnerFriends " +
                "       AND p.owner IN :usersFriends " +
                " ) OR (" +
                "       p.owner = :user" +
                " )" +
                " ORDER BY p.datePosted DESC, p.timePosted DESC";

        // prepare list of users friends from provided friend list
        List<User> usersFriends = new ArrayList<>();
        for(FriendRequest usersFriendship : usersFriendships) {
            usersFriends.add(usersFriendship.getOtherUser(user));
        }

        // prepare list of visibilities with EVERYONE and REGISTERED_USER
        List<Visibility> everyoneRegistered = Arrays.asList(Visibility.EVERYONE, Visibility.REGISTERED_USERS);

        // prepare query
        Query q = em.createQuery(query);
        q.setParameter("visibleEveryoneRegistered", everyoneRegistered);
        q.setParameter("usersFriends", usersFriends);
        q.setParameter("visibleOwnerFriends", Visibility.OWNER_FRIENDS);
        q.setParameter("user", user);

        return q.getResultList();
    }

//    @Override
//    public Page<Post> listPostsForUserPageable(User user, List<FriendRequest> usersFriendships, Pageable pagingInfo) {
//        String query = "SELECT p FROM "+Post.class.getSimpleName()+" p " +
//                " WHERE " +
//                " p.visibility IN :visibleEveryoneRegistered " +
//                " OR ( " +
//                "       p.visibility = :visibleOwnerFriends " +
//                "       AND p.owner IN :usersFriends " +
//                " ) OR (" +
//                "       p.visibility = :visibleOwner" +
//                "       AND p.owner = :user" +
//                " )" +
//                " ORDER BY p.datePosted DESC, p.timePosted DESC";
//
//        // prepare list of users friends from provided friend list
//        List<User> usersFriends = new ArrayList<>();
//        for(FriendRequest usersFriendship : usersFriendships) {
//            usersFriends.add(usersFriendship.getOtherUser(user));
//        }
//
//        // prepare list of visibilities with EVERYONE and REGISTERED_USER
//        List<Visibility> everyoneRegistered = Arrays.asList(Visibility.EVERYONE, Visibility.REGISTERED_USERS);
//
//        // prepare query
//        Query q = em.createQuery(query);
//        q.setParameter("visibleEveryoneRegistered", everyoneRegistered);
//        q.setParameter("usersFriends", usersFriends);
//        q.setParameter("visibleOwnerFriends", Visibility.OWNER_FRIENDS);
//        q.setParameter("visibleOwner", Visibility.OWNER);
//        q.setParameter("user", user);
//
//        Page p = PageImpl
//        return q.getResultList();
//    }
}
