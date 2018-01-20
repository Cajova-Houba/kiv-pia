package cz.zcu.pia.valesz.core.service.impl;

import cz.zcu.pia.valesz.core.dao.KivbookImageDao;
import cz.zcu.pia.valesz.core.dao.UserDao;
import cz.zcu.pia.valesz.core.domain.KivbookImage;
import cz.zcu.pia.valesz.core.domain.User;
import cz.zcu.pia.valesz.core.service.KivbookDateUtils;
import cz.zcu.pia.valesz.core.service.UserManager;
import cz.zcu.pia.valesz.web.vo.ProfileUpdateForm;
import cz.zcu.pia.valesz.web.vo.UserForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;

import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;

/**
 * This class serves as a user manager and as a spring user / social user details service.
 */
public class UserManagerImpl implements UserManager, SocialUserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(UserManagerImpl.class);

    @Autowired
    @Qualifier("userDao")
    private UserDao userDao;

    @Autowired
    @Qualifier("kivbookImageDao")
    private KivbookImageDao kivbookImageDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User loadByUsername(String username, boolean fetchProfilePhoto) {
        if(fetchProfilePhoto) {
            User user = userDao.findByUsernameFetchProfilePhoto(username);
            if(user == null) {
                log.warn("User with username {} not found!", username);
                throw new UsernameNotFoundException("User with username "+username+" not found!");
            }

            return user;
        } else {
            return loadByUsername(username);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if(user == null) {
            log.warn("User with username {} not found!", username);
            throw new UsernameNotFoundException("User with username "+username+" not found!");
        }

        return user;
    }

    @Override
    public SocialUserDetails loadUserByUserId(String id) throws UsernameNotFoundException {
        // User class implements both UserDetails and SocialUserDetails so it should be ok
        return (SocialUserDetails) loadUserByUsername(id);
    }

    @Override
    public User loadByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userDao.existsByUsername(username);
    }

    @Override
    public User updateUserProfile(User toBeUpdated, ProfileUpdateForm updateData) {
        toBeUpdated.setEmail(updateData.getEmail());
        toBeUpdated.setFullName(updateData.getFullName());
        try {
            toBeUpdated.setBirthDate(SimpleDateFormat.getInstance().parse(updateData.getBirthDate()));
        } catch (ParseException e) {
            log.error("{} isn't valid date format. Keeping original value instead.", updateData.getBirthDate());
        }
        toBeUpdated.setProfileVisibility(updateData.getProfileVisibility());
        return userDao.save(toBeUpdated);
    }

    @Override
    public User registerUser(UserForm registrationData) {
        Date birthDate;
        if(registrationData.getBirthDate() == null || registrationData.getBirthDate().isEmpty()) {
            birthDate = null;
        } else {
            birthDate = KivbookDateUtils.parseDate(registrationData.getBirthDate(), UserForm.DATE_FORMAT_1, UserForm.DATE_FORMAT_2);
            if(birthDate == null) {
                log.error("Birth date with wrong format passed to registration method! Using null instead.");
            }
        }
        User toBeCreated = new User(registrationData, birthDate);
        String password = toBeCreated.getPasswordHash();
        toBeCreated.setPasswordHash(passwordEncoder.encode(password));
        KivbookImage defaultImage = kivbookImageDao.getOne(kivbookImageDao.getMinId());
        toBeCreated.setProfilePhoto(defaultImage);
        return userDao.save(toBeCreated);
    }

    @Override
    public User findById(Long id) {
        Optional<User> user = userDao.findById(id);
        return user.isPresent() ? user.get() : null;
    }

    @Override
    public User updateProfilePhoto(User user, byte[] profilePhotoData) {
        User userWithProfilePhoto = userDao.findByUsernameFetchProfilePhoto(user.getUsername());

        // create new profile photo
        KivbookImage oldProfilePhoto = userWithProfilePhoto.getProfilePhoto();
        KivbookImage newProfilePhoto = new KivbookImage();
        newProfilePhoto.setImageData(new String(Base64.getEncoder().encode(profilePhotoData), Charset.forName("ASCII")));
        newProfilePhoto = kivbookImageDao.save(newProfilePhoto);

        // set it to user
        userWithProfilePhoto.setProfilePhoto(newProfilePhoto);
        userWithProfilePhoto = userDao.save(userWithProfilePhoto);

        // optionally delete the old profile photo
        if(!oldProfilePhoto.hasDefaultId()) {
            // old photo doesn't have default id, check if it's used by anyone else
            if(userDao.countUsersByProfilePhoto(oldProfilePhoto) == 0) {
                // delete old photo
                kivbookImageDao.delete(oldProfilePhoto);
            }
        }

        return userWithProfilePhoto;
    }
}
