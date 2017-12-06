package cz.zcu.pia.valesz.core.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Application user entity.
 */
public class User extends SocialUser {

    /**
     * Database id.
     */
    private Long id;

    /**
     * Username. Must be unique.
     */
    private String username;

    /**
     * User's email.
     */
    private String email;

    /**
     * Hash of user's password.
     */
    private String passwordHash;

    /**
     * User's birth date.
     */
    private Date birthDate;

    /**
     * User's gender.
     */
    private Gender gender;

    /**
     * User's friends.
     */
    private List<User> friends;

    /**
     * Visibility of user's profile to the rest of the world.
     */
    private Visibility profileVisibility;

    /**
     * Social identity provider. NONE if user has registered himself without any social network.
     */
    private SocialIdentityProvider socialIdentityProvider;

    /**
     * Default constructor which uses empty username, password and list of granted authorities.
     */
    public User() {
        this("", "", new ArrayList<>());
    }

    public User(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    public Visibility getProfileVisibility() {
        return profileVisibility;
    }

    public void setProfileVisibility(Visibility profileVisibility) {
        this.profileVisibility = profileVisibility;
    }


    /*
        SPRING SOCIAL METHODS
     */

    @Override
    public String getUserId() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return username.equals(user.username);
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }
}
