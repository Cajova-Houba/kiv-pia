package cz.zcu.pia.valesz.core.service.impl;

import cz.zcu.pia.valesz.core.dao.UserDao;
import cz.zcu.pia.valesz.core.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;

/**
 * This class serves as a user manager and as a spring user / social user details service.
 */
public class UserManagerImpl implements UserManager, UserDetailsService, SocialUserDetailsService {


    @Autowired
    private UserDao userDao;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    @Override
    public SocialUserDetails loadUserByUserId(String id) throws UsernameNotFoundException {
        // User class implements both UserDetails and SocialUserDetails so it should be ok
        return (SocialUserDetails) loadUserByUsername(id);
    }
}
