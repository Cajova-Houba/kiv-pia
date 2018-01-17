package cz.zcu.pia.valesz.core.service.impl;

import cz.zcu.pia.valesz.core.dao.ConversationDao;
import cz.zcu.pia.valesz.core.dao.MessageDao;
import cz.zcu.pia.valesz.core.dao.UserDao;
import cz.zcu.pia.valesz.core.domain.Conversation;
import cz.zcu.pia.valesz.core.domain.Message;
import cz.zcu.pia.valesz.core.domain.MessageState;
import cz.zcu.pia.valesz.core.domain.User;
import cz.zcu.pia.valesz.core.service.MessageManager;
import cz.zcu.pia.valesz.core.service.UserManager;
import cz.zcu.pia.valesz.web.vo.ConversationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
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

    @Autowired
    @Qualifier("conversationDao")
    private ConversationDao conversationDao;

    @Override
    public int getNumberOfNewMessages(User user) {
        return messageDao.countByReceiverAndState(user, MessageState.SENT);
    }

    @Override
    public List<ConversationVO> listConversations(User receiver) {
        List<ConversationVO> conversations = new ArrayList<>();

        // load last messages and wrap them in conversation view objects
        List<Message> lastMessages = conversationDao.listLastMessagesInConversations(receiver);
        for(Message lastMessage : lastMessages) {
            User otherUser = lastMessage.getReceiver().equals(receiver) ? lastMessage.getSender() : lastMessage.getReceiver();

            // mark conversation as new if there are some unread messages for receiver
            boolean newFlag = false;
            if(!lastMessage.getSender().equals(receiver) && lastMessage.isNew()) {
                newFlag = true;
            }
            ConversationVO cvo = new ConversationVO(receiver, otherUser, Arrays.asList(lastMessage), newFlag);
            conversations.add(cvo);

        }

        // return wrapped messages
        return conversations;
    }

    @Override
    public ConversationVO getConversation(User currentUser, User otherUser) {
        List<Message> messages = messageDao.getConversation(currentUser, otherUser);
        ConversationVO c = new ConversationVO(currentUser, otherUser, messages, false);
        return c;
    }

    @Override
    public Message markAsRead(Message message) {
        message.markAsRead();
        return messageDao.save(message);
    }

    @Transactional
    @Override
    public Message sendMessage(Message message) {
        // check text length
        String tmpText = message.getText();
        if(tmpText.length() > Message.MAX_MESSAGE_LENGTH) {
            tmpText = tmpText.substring(0, Message.MAX_MESSAGE_LENGTH);
            message.setText(tmpText);
        }

        // save message so it can be linked to conversation
        message.setState(MessageState.SENT);
        message = messageDao.save(message);

        // check whether the conversation already exists
        Conversation conversation = conversationDao.getConversationBetweenUsers(message.getReceiver(), message.getSender());
        if(conversation == null) {
            Conversation c = new Conversation();
            c.setFirstUser(message.getSender());
            c.setSecondUser(message.getReceiver());
            c.setLastMessage(message);
            conversationDao.save(c);
        } else {
            conversation.setLastMessage(message);
            conversationDao.save(conversation);
        }

        return message;
    }

}
