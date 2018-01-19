package cz.zcu.pia.valesz.core.dao;

import org.junit.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Ignore
public class MessageDaoTest extends BaseDaoTest {

    @Autowired
    @Qualifier("messageDao")
    private MessageDao messageDao;

    @Autowired
    @Qualifier("userDao")
    private UserDao userDao;


}
