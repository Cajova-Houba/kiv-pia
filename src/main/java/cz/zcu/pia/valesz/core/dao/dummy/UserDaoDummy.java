package cz.zcu.pia.valesz.core.dao.dummy;

import cz.zcu.pia.valesz.core.dao.UserDao;
import cz.zcu.pia.valesz.core.domain.User;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;

/**
 * Dummy dao for testing purposes.
 */
public class UserDaoDummy implements UserDao {

    private String pp1FName = "/img/profile_photo.png";
    private String pp2FName = "/img/poster_photo.png";

    @Override
    public User loadByUsername(String username) {
        User u = null;
        switch (username) {
            case "Pepa Uživatel":
                u = new User(username, "123456789", new ArrayList<>());
                u.setProfilePhoto(loadImage(pp1FName));
                break;
            case "Hustej Uživatel":
                u = new User(username, "123456789", new ArrayList<>());
                u.setProfilePhoto(loadImage(pp2FName));
                break;
            default:
                u = new User(-50L, username, "123456789");
                break;
        }

        return u;
    }

    private String loadImage(String imageName) {
        try {
            return new String(Base64.getEncoder().encode(IOUtils.toByteArray(getClass().getResourceAsStream(imageName))), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
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
