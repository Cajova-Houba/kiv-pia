package cz.zcu.pia.valesz.core.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;

import javax.persistence.*;
import java.util.*;

/**
 * Application user entity.
 */
@Entity
@Table(name="user")
public class User extends SocialUser {

    /**
     * Database id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Username. Must be unique, can be used as business key.
     */
    @Column(unique = true, nullable = false)
    private String username;

    /**
     * User's email.
     */
    @Column(nullable = false)
    private String email;

    /**
     * Hash of user's password.
     */
    @Column(name = "password_hash")
    private String passwordHash;

    /**
     * User's birth date.
     */
    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    /**
     * User's gender.
     */
    @Enumerated(EnumType.STRING)
    private Gender gender;

    /**
     * User's friends.
     */
    private List<User> friends;

    /**
     * Visibility of user's profile to the rest of the world.
     */
    @Column(name = "profile_visibility")
    @Enumerated(EnumType.STRING)
    private Visibility profileVisibility;

    /**
     * Social identity provider. NONE if user has registered himself without any social network.
     */
    @Column(name = "social_identity_provider")
    @Enumerated(EnumType.STRING)
    private SocialIdentityProvider socialIdentityProvider;

    /**
     * Default constructor which uses empty username, password and list of granted authorities.
     */
    public User() {
        this("", "", Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
    }

    public User(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    /**
     * Constructor which can be used to set id. For testing purposes mostly.
     * @param id Database id.
     * @param username Username.
     * @param password Password.
     */
    public User(Long id, String username, String password) {
        this(username, password, Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
        setId(id);
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
