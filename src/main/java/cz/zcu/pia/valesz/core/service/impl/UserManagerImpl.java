package cz.zcu.pia.valesz.core.service.impl;

import cz.zcu.pia.valesz.core.dao.KivbookImageDao;
import cz.zcu.pia.valesz.core.dao.UserDao;
import cz.zcu.pia.valesz.core.domain.Gender;
import cz.zcu.pia.valesz.core.domain.KivbookImage;
import cz.zcu.pia.valesz.core.domain.User;
import cz.zcu.pia.valesz.core.service.UserManager;
import cz.zcu.pia.valesz.web.vo.ProfileUpdateForm;
import cz.zcu.pia.valesz.web.vo.UserForm;
import org.apache.commons.validator.routines.EmailValidator;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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
    public Set<String> validateRegistration(UserForm toBeValidated) {
        Set<String> errors = new HashSet<>();

        String username = toBeValidated.getUsername(),
               email = toBeValidated.getEmail(),
               password = toBeValidated.getPassword(),
               passwordConf = toBeValidated.getPasswordConf(),
               fullName = toBeValidated.getFullName(),
               gender = toBeValidated.getGender();
        Date birthDate = toBeValidated.getBirthDate();
        boolean acceptTerms = toBeValidated.isAcceptTerms();

        // validation process:
        // check that valid (unique) username was provided
        // check that valid email (format) was provided
        // check that password isn't empty and password == password-conf
        // check age (13+)
        // check that correct gender was entered
        // check that terms are accepted
        if(username == null || username.isEmpty()) {
            log.debug("Username '{}' is wrong.", username);
            errors.add(ERR_USERNAME_WRONG);
        }

        if(userDao.existsByUsername(username)) {
            log.debug("Username '{}' already exists.", username);
            errors.add(ERR_USERNAME_EXISTS);
        }

        if(email == null || email.isEmpty() || !EmailValidator.getInstance().isValid(email)) {
            log.debug("Email '{}' is wrong.", email);
            errors.add(ERR_WRONG_EMAIL);
        }

        if(password == null || password.isEmpty()) {
            log.debug("Password '{}' is wrong.", password);
            errors.add(ERR_WRONG_PASS);
        }

        if( !password.equals(passwordConf)) {
            log.debug("Passwords '{}' and '{}' don't match.", password, passwordConf);
            errors.add(ERR_PASS_DONT_MATCH);
        }

        if(fullName == null || fullName.isEmpty()) {
            log.debug("Full name '{}' is wrong.", fullName);
            errors.add(ERR_WRONG_FULL_NAME);
        }

        // todo: check age, move min age to some constant
        Date maxBirthDate = new DateTime().minusYears(13).toDate();
        if(birthDate != null && birthDate.after(maxBirthDate)) {
            log.debug("Date of birth '{}' is wrong.", birthDate);
            errors.add(ERR_TOO_YOUNG_MAN);
        }

        Gender realGender = Gender.webNameToGender(gender);
        if(realGender == null) {
            log.debug("Gender '{}' is wrong.", gender);
            errors.add(ERR_NOT_AGENDER);
        }

        if(!acceptTerms) {
            log.debug("Terms not accepted.");
            errors.add(ERR_SHUT_UP_AND_ACCEPT);
        }

        return errors;
    }

    @Override
    public User updateUserProfile(User toBeUpdated, ProfileUpdateForm updateData) {
        toBeUpdated.setEmail(updateData.getEmail());
        toBeUpdated.setFullName(updateData.getFullName());
        toBeUpdated.setBirthDate(updateData.getBirthDate());
        toBeUpdated.setProfileVisibility(updateData.getProfileVisibility());
        return userDao.save(toBeUpdated);
    }

    @Override
    public User registerUser(User toBeCreated) {
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
}
