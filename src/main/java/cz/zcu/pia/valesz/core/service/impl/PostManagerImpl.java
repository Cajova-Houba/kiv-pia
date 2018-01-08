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
    private UserDao userDaoDummy;

    @Autowired
    @Qualifier("userDao")
    private UserDao userDao;

    @Autowired
    @Qualifier("postDaoDummy")
    private PostDao postDaoDummy;

    @Autowired
    @Qualifier("postDao")
    private PostDao postDao;

    @Override
    public List<Post> listPostsForUser() {
        List<Post> posts = postDao.listPostsForUser(userDao.getOne(5l));
//        if (posts.isEmpty()) {
//            User u1 = userDao.getOne(5L);
//            Post p1 = new Post(new DateTime(2017, 10, 12, 0, 0).toDate(), "Tohle je fakt dobrá sociální síť.", u1);
//            p1.setId(0L);
//            User u2 = userDao.getOne(9L);
//            Post p2 = new Post(new DateTime(2017, 10, 2, 0, 0).toDate(), "Hej lidi, taky máte ten pocit, jako bychom žili na planetě opic?", u2);
//            p2.setId(1L);
//            postDaoDummy.save(p1);
//            postDaoDummy.save(p2);
//            posts = postDaoDummy.listPostsForUser();
//        }
        return posts;
    }

    @Override
    public Post createNewPost(String text) {
        User currentUser = userDaoDummy.findByUsername("Pepa Uživatel");
        Post post = new Post(new DateTime().toDate(), text, currentUser);
        post.setId(postDaoDummy.listPostsForUser(new User()).size()+1L);
        return postDaoDummy.save(post);
    }
}
