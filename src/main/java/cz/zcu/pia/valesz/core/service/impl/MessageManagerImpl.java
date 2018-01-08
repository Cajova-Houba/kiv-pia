package cz.zcu.pia.valesz.core.service.impl;

import cz.zcu.pia.valesz.core.dao.MessageDao;
import cz.zcu.pia.valesz.core.dao.UserDao;
import cz.zcu.pia.valesz.core.domain.Message;
import cz.zcu.pia.valesz.core.domain.MessageState;
import cz.zcu.pia.valesz.core.domain.User;
import cz.zcu.pia.valesz.core.domain.vo.Conversation;
import cz.zcu.pia.valesz.core.service.MessageManager;
import cz.zcu.pia.valesz.core.service.UserManager;
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
        return messageDaoDummy.getNumberOfNewMessages(user);
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

    /**
     * Creates dummy conversation overview.
     * @return
     */
    private List<Conversation> createDummyConversations() {
        List<Conversation> conversations = new ArrayList<>();
        conversations.add(new Conversation( null, userManager.findById(9L), Arrays.asList(
                new Message(10L, userManager.findById(5L), userManager.findById(9L), "Dík no.", getDate(2017,1,2,8,0), MessageState.READ)
        ), false));
        conversations.add(new Conversation( null, userManager.findById(10L), Arrays.asList(
                new Message(10L,userManager.findById(10l), userManager.findById(5l), "Už jsi dneska jedl?", getDate(2016,7,6,19,10), MessageState.SENT)
        ), true));
        conversations.add(new Conversation( null, userManager.findById(11L), Arrays.asList(
                new Message(10L,userManager.findById(11l), userManager.findById(5l), "Hi, you just won 1 MILLION DOLLARS!!!", getDate(2016,7,6,19,10), MessageState.SENT)
        ), true));
        conversations.add(new Conversation( null, userManager.findById(8L), Arrays.asList(
                new Message(10L,userManager.findById(8l), userManager.findById(5l), "Tahle zpráva je už dávno přečtená.", getDate(2016,7,6,19,10), MessageState.READ)
        ), false));

        return conversations;
    }

    /**
     * Creates and dummy conversation.
     */
    private Conversation createDummyConversation() {
        List<Message> messages = new ArrayList<>();
        messages.add(new Message(-1L, userDao.getOne(9l), userDao.getOne(5l), "Čau, já jsem hustej uživatel.", getDate(2017,1,1,12,47), MessageState.READ));
        messages.add(new Message(-2L, userDao.getOne(5l), userDao.getOne(9l), "Čau, já jsem Pepa. Jak se máš?", getDate(2017,1,1,13,21), MessageState.READ));
        messages.add(new Message(-2L, userDao.getOne(9l), userDao.getOne(5l), "Ale jo, dá se. Co ty?", getDate(2017,1,1,13,25), MessageState.READ));
        messages.add(new Message(-4L, userDao.getOne(5l), userDao.getOne(9l), "No nic moc. Už musim jít, tak čau.", getDate(2017,1,1,13,30), MessageState.READ));
        messages.add(new Message(-5L, userDao.getOne(9l), userDao.getOne(5l), "Čau.", getDate(2017,1,1,13,32), MessageState.READ));
        messages.add(new Message(-6L, userDao.getOne(9l), userDao.getOne(5l), "Čau.", getDate(2017,1,2,7,30), MessageState.READ));
        messages.add(new Message(-7L, userDao.getOne(9l), userDao.getOne(5l), "Tak co, jak se máš?", getDate(2017,1,2,7,31), MessageState.READ));
        messages.add(new Message(-8L, userDao.getOne(9l), userDao.getOne(5l), "Tady ti posílám vážně dlouhou zprávu: Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.", getDate(2017,1,2,7,35), MessageState.READ));
        messages.add(new Message(-9L, userDao.getOne(9l), userDao.getOne(5l), "Čau, já jsem hsutej uživatel.", getDate(2017,1,2,12,47), MessageState.READ));
        messages.add(new Message(-10L, userDao.getOne(5l), userDao.getOne(9l), "Dík no.", getDate(2017,1,2,8,0), MessageState.READ));


        return new Conversation( userManager.loadByUsername("Hustej uživatel"), userManager.loadByUsername("Pepa Uživatel"), messages, false);
    }

    private Date getDate(int year, int month, int day, int hour, int minute) {
        return new DateTime(year, month, day, hour, minute).toDate();
    }

}
