package cz.zcu.pia.valesz.core.dao;

import cz.zcu.pia.valesz.core.domain.FriendRequest;
import cz.zcu.pia.valesz.core.domain.FriendRequestState;
import cz.zcu.pia.valesz.core.domain.Post;
import cz.zcu.pia.valesz.core.domain.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

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
    public void testFindOne() {
        List<Post> ps = postDao.findAll();
        assertFalse("No posts were found!", ps.isEmpty());

        assertNotNull("Null owner!", ps.get(0).getOwner());
    }

    @Test
    public void testListPostsForUser() {
        User user = userDao.findByUsername("user1");
        List<FriendRequest> friendships = friendDao.findUsersFriendRequests(user, FriendRequestState.ACCEPTED);

        List<Post> posts = postDao.listPostsForUser(user, friendships);
        assertFalse("No posts returned!", posts.isEmpty());
    }
}
