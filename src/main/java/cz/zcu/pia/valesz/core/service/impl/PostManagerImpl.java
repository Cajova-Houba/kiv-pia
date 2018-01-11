package cz.zcu.pia.valesz.core.service.impl;

import cz.zcu.pia.valesz.core.dao.PostDao;
import cz.zcu.pia.valesz.core.domain.Post;
import cz.zcu.pia.valesz.core.domain.User;
import cz.zcu.pia.valesz.core.service.PostManager;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class PostManagerImpl implements PostManager {

    @Autowired
    @Qualifier("postDao")
    private PostDao postDao;

    @Override
    public List<Post> listPostsForUser(User user) {
        List<Post> posts = postDao.listPostsForUser(user);
        return posts;
    }

    @Override
    public Post createNewPost(String text, User creator) {
        Post post = new Post(new DateTime().toDate(), text, creator);
        return postDao.save(post);
    }
}
