package cz.zcu.pia.valesz.core.dao.dummy;

import cz.zcu.pia.valesz.core.dao.UserDao;
import cz.zcu.pia.valesz.core.domain.User;

/**
 * Dummy dao for testing purposes.
 */
public class UserDaoDummy implements UserDao{

    @Override
    public User loadByUsername(String username) {
        return new User(-50L, username, "123456789");
    }

    @Override
    public User get(Long key) {
        return new User(key, "test-user", "123456789");
    }

    @Override
    public User save(User object) {
        return object;
    }

    @Override
    public void delete(Long key) {

    }
}
