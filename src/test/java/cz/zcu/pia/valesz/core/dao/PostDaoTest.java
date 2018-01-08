package cz.zcu.pia.valesz.core.dao;

import cz.zcu.pia.valesz.core.domain.Post;
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

    @Test
    public void testFindOne() {
        List<Post> ps = postDao.findAll();
        assertFalse("No posts were found!", ps.isEmpty());

        assertNotNull("Null owner!", ps.get(0).getOwner());
    }
}
