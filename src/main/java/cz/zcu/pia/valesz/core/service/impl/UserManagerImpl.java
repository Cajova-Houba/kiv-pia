package cz.zcu.pia.valesz.core.service.impl;

import cz.zcu.pia.valesz.core.dao.UserDao;
import cz.zcu.pia.valesz.core.domain.User;
import cz.zcu.pia.valesz.core.service.UserManager;
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

/**
 * This class serves as a user manager and as a spring user / social user details service.
 */
public class UserManagerImpl implements UserManager, UserDetailsService, SocialUserDetailsService, AuthenticationProvider {


    @Autowired
    @Qualifier("userDaoDummy")
    private UserDao userDao;

    @Override
    public User getCurrentlyLoggerUser() {
        return userDao.loadByUsername("Pepa Uživatel");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.loadByUsername(username);
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
        return userDao.loadByUsername(username);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        System.out.println("Authenticating "+name+","+password);
        User u = userDao.loadByUsername(name);
        if (u != null && u.getPassword().equals(password)) {
        } else {
            System.out.println("Credentials "+name+","+password+" are bad!");
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
}
