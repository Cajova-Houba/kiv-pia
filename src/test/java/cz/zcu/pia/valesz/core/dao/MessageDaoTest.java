package cz.zcu.pia.valesz.core.dao;

import cz.zcu.pia.valesz.core.domain.Gender;
import cz.zcu.pia.valesz.core.domain.Message;
import cz.zcu.pia.valesz.core.domain.MessageState;
import cz.zcu.pia.valesz.core.domain.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Date;
import java.util.UUID;

import static org.junit.Assert.assertNotNull;

public class MessageDaoTest extends BaseDaoTest {

    @Autowired
    @Qualifier("messageDao")
    private MessageDao messageDao;

    @Autowired
    @Qualifier("userDao")
    private UserDao userDao;

    @Test
    public void testGetOne() {
        String username = "user1"+ UUID.randomUUID().toString();
        User u1 = new User( "user1@kivbook.com", username, "psw",new Date(), "Pepa Uživatel", Gender.MALE);
        userDao.save(u1);

        Message message = new Message(0L, userDao.getOne(u1.getId()), userDao.getOne(u1.getId()), "asdasd",new Date(), MessageState.READ);
        message = messageDao.save(message);

        message = messageDao.getOne(message.getId());
        assertNotNull(message.getSender());
        assertNotNull(message.getReceiver());
    }
}
