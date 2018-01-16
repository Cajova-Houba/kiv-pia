package cz.zcu.pia.valesz.core.service.impl;

import cz.zcu.pia.valesz.core.dao.MessageDao;
import cz.zcu.pia.valesz.core.dao.UserDao;
import cz.zcu.pia.valesz.core.domain.Message;
import cz.zcu.pia.valesz.core.domain.MessageState;
import cz.zcu.pia.valesz.core.domain.User;
import cz.zcu.pia.valesz.core.service.MessageManager;
import cz.zcu.pia.valesz.core.service.UserManager;
import cz.zcu.pia.valesz.web.vo.Conversation;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MessageManagerImpl implements MessageManager {

    @Autowired
    @Qualifier("messageDaoDummy")
    private MessageDao messageDaoDummy;

    @Autowired
    @Qualifier("messageDao")
    private MessageDao messageDao;

    @Autowired
    private UserManager userManager;

    @Autowired
    @Qualifier("userDao")
    private UserDao userDao;

    @Override
    public int getNumberOfNewMessages(User user) {
        return messageDao.countByReceiverAndState(user, MessageState.SENT);
    }

    @Override
    public List<Conversation> listConversations(User receiver) {
        return createDummyConversations();
    }

    @Override
    public Conversation getConversation(User currentUser, User otherUser) {
        List<Message> messages = messageDao.getConversation(currentUser, otherUser);
        Conversation c = new Conversation(currentUser, otherUser, messages, false);
        return c;
    }

    @Override
    public Message markAsRead(Message message) {
        message.markAsRead();
        return messageDao.save(message);
    }

    /**
     * Creates dummy conversation overview.
     * @return
     */
    private List<Conversation> createDummyConversations() {
        List<Conversation> conversations = new ArrayList<>();
        User current = userManager.findById(5L);
        conversations.add(new Conversation( current, userManager.findById(9L), Arrays.asList(
                messageDao.getOne(65L)
        ), false));
        conversations.add(new Conversation( current, userManager.findById(10L), Arrays.asList(
                messageDao.getOne(53L)
        ), true));
        conversations.add(new Conversation( current, userManager.findById(11L), Arrays.asList(
                messageDao.getOne(54L)
        ), true));
        conversations.add(new Conversation( current, userManager.findById(8L), Arrays.asList(
                messageDao.getOne(55L)
        ), false));

        return conversations;
    }

    private Date getDate(int year, int month, int day, int hour, int minute) {
        return new DateTime(year, month, day, hour, minute).toDate();
    }

}
