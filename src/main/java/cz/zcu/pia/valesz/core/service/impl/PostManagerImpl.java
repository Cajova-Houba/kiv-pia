package cz.zcu.pia.valesz.core.service.impl;

import cz.zcu.pia.valesz.core.dao.FriendDao;
import cz.zcu.pia.valesz.core.dao.LikeDao;
import cz.zcu.pia.valesz.core.dao.PostDao;
import cz.zcu.pia.valesz.core.domain.*;
import cz.zcu.pia.valesz.core.service.PostManager;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public class PostManagerImpl implements PostManager {

    @Autowired
    @Qualifier("postDao")
    private PostDao postDao;

    @Autowired
    @Qualifier("friendDao")
    private FriendDao friendDao;

    @Autowired
    @Qualifier("likeDao")
    private LikeDao likeDao;

    @Override
    public List<Post> listPostsForUser(User user) {
        List<FriendRequest> usersFriendships = friendDao.findUsersFriendRequests(user, FriendRequestState.ACCEPTED);
        List<Post> posts = postDao.listPostsForUser(user, usersFriendships);
        return posts;
    }

    @Override
    public Post createNewPost(String text, Visibility visibility, User creator) {
        if(text.length() > Post.MAX_POST_LENGTH) {
            text = text.substring(0, Post.MAX_POST_LENGTH);
        }

        Post post = new Post(new DateTime().toDate(), text, creator);
        post.setVisibility(visibility);
        return postDao.save(post);
    }

    @Override
    public Page<Post> listPostsForUser(User user, Pageable pageRequest) {
        // load users's friends
        List<FriendRequest> usersFriendships = friendDao.findUsersFriendRequests(user, FriendRequestState.ACCEPTED);
        List<User> usersFriends = new ArrayList<>();
        for(FriendRequest usersFriendship : usersFriendships) {
            usersFriends.add(usersFriendship.getOtherUser(user));
        }

        // if the user has no friends use different method
        if(usersFriends.isEmpty()) {
            return postDao.getPostFeedForFriendlessUser(user, pageRequest);
        }

        // return page with post feed
        return postDao.getPostFeedForUser(user, usersFriends, pageRequest);
    }

    @Override
    public boolean alreadyLiked(User user, Post post) {
        return likeDao.existsByUserAndPost(user, post);
    }

    @Override
    public Post getById(long id) {
        return postDao.getOne(id);
    }

    @Override
    public void likePost(User user, Post post) {
        Like like = new Like(user, post);
        likeDao.save(like);
    }
}
