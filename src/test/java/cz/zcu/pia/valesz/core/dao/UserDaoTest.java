package cz.zcu.pia.valesz.core.dao;

import cz.zcu.pia.valesz.core.dao.hibernate.UserDaoHibernate;
import org.springframework.beans.factory.annotation.Autowired;

public class UserDaoTest {


    @Autowired
    private UserDao userDao;


    /**
     * Creates new user, saves him and checks that he will be loaded from database.
     */
    public void testSaveUser() {
    }
}
