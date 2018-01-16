package cz.zcu.pia.valesz.core.service.impl;

import cz.zcu.pia.valesz.core.dao.FriendDao;
import cz.zcu.pia.valesz.core.dao.PostDao;
import cz.zcu.pia.valesz.core.domain.*;
import cz.zcu.pia.valesz.core.service.PostManager;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class PostManagerImpl implements PostManager {

    @Autowired
    @Qualifier("postDao")
    private PostDao postDao;

    @Autowired
    @Qualifier("friendDao")
    private FriendDao friendDao;

    @Override
    public List<Post> listPostsForUser(User user) {
        List<FriendRequest> usersFriendships = friendDao.findUsersFriendRequests(user, FriendRequestState.ACCEPTED);
        List<Post> posts = postDao.listPostsForUser(user, usersFriendships);
        return posts;
    }

    @Override
    public Post createNewPost(String text, Visibility visibility, User creator) {
        Post post = new Post(new DateTime().toDate(), text, creator);
        post.setVisibility(visibility);
        return postDao.save(post);
    }
}
