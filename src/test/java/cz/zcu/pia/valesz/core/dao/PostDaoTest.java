package cz.zcu.pia.valesz.core.dao;

import cz.zcu.pia.valesz.core.domain.Post;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;


public class PostDaoTest extends BaseDaoTest{

    @Autowired
    @Qualifier("postDao")
    private PostDao postDao;

    @Test
    public void testFindOne() {
        List<Post> ps = postDao.findAll();
    }
}
