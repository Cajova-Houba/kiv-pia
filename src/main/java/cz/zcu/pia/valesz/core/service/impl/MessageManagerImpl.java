package cz.zcu.pia.valesz.core.service.impl;

import cz.zcu.pia.valesz.core.service.MessageManager;

public class MessageManagerImpl implements MessageManager {

    @Override
    public int getNumberOfNewMessages() {
        return 3;
    }
}
