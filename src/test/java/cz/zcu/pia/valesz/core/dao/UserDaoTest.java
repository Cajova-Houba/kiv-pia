package cz.zcu.pia.valesz.core.dao;

import cz.zcu.pia.valesz.core.domain.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import static org.junit.Assert.*;

/**
 * Simple test to see if the stuff works.
 */
public class UserDaoTest extends BaseDaoTest{


    @Autowired
    @Qualifier("userDao")
    private UserDao userDao;


    @Test
    public void testFindByUsername() {
        // test data
        String username = "user1";

        // tests
        User user = userDao.findByUsername(username);
        assertNotNull("User not found!", user);
        assertEquals("Wrong username!", username, user.getUsername());
        assertNotNull("NUll profile photo!", user.getProfilePhoto());

        user = userDao.findByUsername("non-existent-user");
        assertNull("Non existent user found!", user);
    }

}
