package cz.zcu.pia.valesz.core.dao;

import cz.zcu.pia.valesz.core.domain.User;
import cz.zcu.pia.valesz.core.domain.Visibility;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.UUID;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Simple integration test to see if the stuff works.
 */
public class UserDaoTest extends BaseDaoTest{


    @Autowired
    @Qualifier("userDao")
    private UserDao userDao;

    /**
     * Insert some test data.
     */
    @Before
    public void setUp() {
        User u1 = new User(-1L, "user1@kivbook.com", "user1", "psw", "Pepa Uživatel", Visibility.REGISTERED_USERS);
        if(!userDao.existsByUsername(u1.getUsername())) {
            userDao.save(u1);
        }
    }

    @Test
    public void testFindByUsername() {
        // test data
        String username = "user1"+ UUID.randomUUID().toString();
        User u1 = new User(-1L, "user1@kivbook.com", username, "psw", "Pepa Uživatel", Visibility.REGISTERED_USERS);
        u1 = userDao.save(u1);

        // tests
        User user = userDao.findByUsername(username);
        assertNotNull("User not found!", user);

        user = userDao.findByUsername("non-existent-user");
        assertNull("Non existent user found!", user);

        // cleanup
//        userDao.delete(u1);
    }

    /**
     * Creates new user, saves him and checks that he will be loaded from database.
     */
    public void testSaveUser() {
    }
}
