package cz.zcu.pia.valesz.core.dao;

import cz.zcu.pia.valesz.core.domain.FriendRequest;
import cz.zcu.pia.valesz.core.domain.FriendRequestState;
import cz.zcu.pia.valesz.core.domain.Post;
import cz.zcu.pia.valesz.core.domain.User;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;


public class PostDaoTest extends BaseDaoTest{

    @Autowired
    @Qualifier("postDao")
    private PostDao postDao;

    @Autowired
    @Qualifier("userDao")
    private UserDao userDao;

    @Autowired
    @Qualifier("friendDao")
    private FriendDao friendDao;

    @Test
    @Ignore
    public void testFindOne() {
        List<Post> ps = postDao.findAll();
        assertFalse("No posts were found!", ps.isEmpty());

        assertNotNull("Null owner!", ps.get(0).getOwner());
    }

    @Test
    @Ignore
    public void testListPostsForUser() {
        User user = userDao.findByUsername("user1");
        List<FriendRequest> friendships = friendDao.findUsersFriendRequests(user, FriendRequestState.ACCEPTED);

        List<Post> posts = postDao.listPostsForUser(user, friendships);
        assertFalse("No posts returned!", posts.isEmpty());
    }

    @Test
    public void testGetPostFeedForUser() {
        User user = userDao.findByUsername("user1");
        List<FriendRequest> friendRequests = friendDao.findUsersFriendRequests(user, FriendRequestState.ACCEPTED);
        List<User> usersFriends = new ArrayList<>();
        for(FriendRequest usersFriendship : friendRequests) {
            usersFriends.add(usersFriendship.getOtherUser(user));
        }
        Pageable pageable = PageRequest.of(0,10,
                new Sort(Sort.Direction.DESC, "datePosted", "timePosted"));

        // use posts returned by this method as reference
        Page<Post> posts = postDao.getPostFeedForUser(user, usersFriends, pageable);
        assertNotNull("Null returned!", posts);
        assertFalse("Wrong number of posts returned!", posts.getContent().isEmpty());
    }
}
