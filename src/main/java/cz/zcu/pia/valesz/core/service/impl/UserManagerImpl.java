package cz.zcu.pia.valesz.core.service.impl;

import cz.zcu.pia.valesz.core.dao.UserDao;
import cz.zcu.pia.valesz.core.domain.Gender;
import cz.zcu.pia.valesz.core.domain.User;
import cz.zcu.pia.valesz.core.service.UserManager;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * This class serves as a user manager and as a spring user / social user details service.
 */
public class UserManagerImpl implements UserManager, UserDetailsService, SocialUserDetailsService, AuthenticationProvider {

    private static final Logger log = LoggerFactory.getLogger(UserManagerImpl.class);


    @Autowired
    @Qualifier("userDaoDummy")
    private UserDao userDaoDummy;

    @Autowired
    @Qualifier("userDao")
    private UserDao userDao;

    @Override
    public User getCurrentlyLoggerUser() {
        return userDaoDummy.findByUsername("Pepa Uživatel");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDaoDummy.findByUsername(username);
        if(user == null) {
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
        return userDaoDummy.findByUsername(username);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        log.debug("Authenticating {0},{1}.",name,password);
        User u = userDaoDummy.findByUsername(name);
        if (u != null && u.getPasswordHash().equals(password)) {
        } else {
            log.debug("Credentials {0},{1} are bad!",name, password);
            throw new BadCredentialsException("Bad credentials!");
        }
        // use the credentials
        // and authenticate against the third-party system
        return new UsernamePasswordAuthenticationToken(
                name, password, new ArrayList<>());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userDaoDummy.existsByUsername(username);
    }

    @Override
    public Set<String> validateRegistration(String username, String email, String password, String passwordConf, String fullName, Date birthDate, String gender, boolean acceptTerms) {
        Set<String> errors = new HashSet<>();

        // validation process:
        // check that valid (unique) username was provided
        // check that valid email (format) was provided
        // check that password isn't empty and password == password-conf
        // check age (13+)
        // check that correct gender was entered
        // check that terms are accepted
        if(username == null || username.isEmpty()) {
            log.debug("Username '{}' is wrong.", username);
            errors.add(REG_USERNAME_WRONG);
        }

        if(userDao.existsByUsername(username)) {
            log.debug("Username '{}' already exists.", username);
            errors.add(REG_USERNAME_EXISTS);
        }

        // todo: email check
        if(email == null || email.isEmpty()) {
            log.debug("Email '{}' is wrong.", email);
            errors.add(REG_WRONG_EMAIL);
        }

        if(password == null || password.isEmpty()) {
            log.debug("Password '{}' is wrong.", password);
            errors.add(REG_WRONG_PASS);
        }

        if( !password.equals(passwordConf)) {
            log.debug("Passwords '{}' and '{}' don't match.", password, passwordConf);
            errors.add(REG_PASS_DONT_MATCH);
        }

        if(fullName == null || fullName.isEmpty()) {
            log.debug("Full name '{}' is wrong.", fullName);
            errors.add(REG_WRONG_FULL_NAME);
        }

        // todo: check age, move min age to some constant
        Date maxBirthDate = new DateTime().minusYears(13).toDate();
        if(birthDate == null || birthDate.after(maxBirthDate)) {
            log.debug("Date of birth '{}' is wrong.", birthDate);
            errors.add(REG_TOO_YOUNG_MAN);
        }

        Gender realGender = Gender.webNameToGender(gender);
        if(realGender == null) {
            log.debug("Gender '{}' is wrong.", gender);
            errors.add(REG_NOT_A_GENDER);
        }

        if(!acceptTerms) {
            log.debug("Terms not accepted.");
            errors.add(REG_SHUT_UP_AND_ACCEPT);
        }

        return errors;
    }

    @Override
    public User registerUser(User toBeCreated) {
        return userDao.save(toBeCreated);
    }
}
