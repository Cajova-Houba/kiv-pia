package cz.zcu.pia.valesz.core.dao.dummy;

import cz.zcu.pia.valesz.core.dao.MessageDao;
import cz.zcu.pia.valesz.core.domain.Message;
import cz.zcu.pia.valesz.core.domain.User;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class MessageDaoDummy implements MessageDao {

    private List<Message> messageRepository = new ArrayList<>();


    @Override
    public List<Message> listConversations(User receiver) {

        return null;
    }

    @Override
    public void delete(Message deleted) {

    }

    @Override
    public List<Message> findAll() {
        return null;
    }

    @Override
    public Optional<Message> findOne(Long id) {
        return null;
    }

    public Message get(Long key) {
        for(Message m : messageRepository) {
            if(m.getId() == key) {
                return m;
            }
        }
        return null;
    }

    @Override
    public Message save(Message object) {
        messageRepository.add(object);
        return object;
    }

//    @Override
    public void delete(Long key) {
        Iterator<Message> msgIt = messageRepository.iterator();
        while(msgIt.hasNext()) {
            if(msgIt.next().getId() == key) {
                msgIt.remove();
            }
        }
    }

    @Override
    public int getNumberOfNewMessages(User user) {
        return 3;
    }
}
