package cz.zcu.pia.valesz.core.dao;

import cz.zcu.pia.valesz.core.domain.Conversation;
import cz.zcu.pia.valesz.core.domain.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class ConversationDaoTest extends BaseDaoTest {

    @Autowired
    @Qualifier("userDao")
    private UserDao userDao;

    @Autowired
    @Qualifier("conversationDao")
    private ConversationDao conversationDao;

    @Test
    public void testListConversationsByUser() {
        User user = userDao.findByUsername("user1");

        List<Conversation> conversations = conversationDao.listConversationsByUser(user);
    }

}
