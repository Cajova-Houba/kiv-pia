package cz.zcu.pia.valesz.core.service.impl;

import cz.zcu.pia.valesz.core.dao.PostDao;
import cz.zcu.pia.valesz.core.dao.UserDao;
import cz.zcu.pia.valesz.core.domain.Post;
import cz.zcu.pia.valesz.core.domain.User;
import cz.zcu.pia.valesz.core.service.PostManager;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class PostManagerImpl implements PostManager {

    @Autowired
    @Qualifier("userDaoDummy")
    private UserDao userDao;

    @Autowired
    @Qualifier("postDaoDummy")
    private PostDao postDao;

    @Override
    public List<Post> listPostsForUser() {
        List<Post> posts = postDao.listPostsForUser();
        if (posts.isEmpty()) {
            User u1 = userDao.findByUsername("Pepa Uživatel");
            Post p1 = new Post(new DateTime(2017, 10, 12, 0, 0).toDate(), "Tohle je fakt dobrá sociální síť.", u1);
            p1.setId(0L);
            User u2 = userDao.findByUsername("Hustej Uživatel");
            Post p2 = new Post(new DateTime(2017, 10, 2, 0, 0).toDate(), "Hej lidi, taky máte ten pocit, jako bychom žili na planetě opic?", u2);
            p2.setId(1L);
            postDao.save(p1);
            postDao.save(p2);
            posts = postDao.listPostsForUser();
        }
        return posts;
    }

    @Override
    public Post createNewPost(String text) {
        User currentUser = userDao.findByUsername("Pepa Uživatel");
        Post post = new Post(new DateTime().toDate(), text, currentUser);
        post.setId(postDao.listPostsForUser().size()+1L);
        return postDao.save(post);
    }
}
