package cz.zcu.pia.valesz.core.dao;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import cz.zcu.pia.valesz.core.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Simple integration test to see if the stuff works.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DatabaseSetup("test-users.xml")
public class UserDaoTest {


    @Autowired
    private UserDao userDao;


    @Test
    public void testFindByUsername() {
        User user = userDao.findByUsername("user1");
        assertNotNull("User not found!", user);

        user = userDao.findByUsername("non-existent-user");
        assertNull("Non existent user found!", user);
    }

    /**
     * Creates new user, saves him and checks that he will be loaded from database.
     */
    public void testSaveUser() {
    }
}
