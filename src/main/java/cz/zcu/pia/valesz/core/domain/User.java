package cz.zcu.pia.valesz.core.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.social.security.SocialUser;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Application user entity.
 */
@Entity
@Table(name="user")
public class User extends SocialUser {

    /**
     * Database id.
     */
    private Long id;

    /**
     * User's email.
     */
    private String email;

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
     * User's profile photo stored as byte array.
     */
    private String profilePhoto;

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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    @Column(unique = true, nullable = false)
    public String getUsername() {
        return super.getUsername();
    }

    @Column(name = "password_hash")
    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Column(nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Enumerated(EnumType.STRING)
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @OneToMany(fetch = FetchType.LAZY)
    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    @Column(name = "profile_visibility")
    @Enumerated(EnumType.STRING)
    public Visibility getProfileVisibility() {
        return profileVisibility;
    }

    public void setProfileVisibility(Visibility profileVisibility) {
        this.profileVisibility = profileVisibility;
    }

    @Column(name = "social_identity_provider")
    @Enumerated(EnumType.STRING)
    public SocialIdentityProvider getSocialIdentityProvider() {
        return socialIdentityProvider;
    }

    public void setSocialIdentityProvider(SocialIdentityProvider socialIdentityProvider) {
        this.socialIdentityProvider = socialIdentityProvider;
    }

    @Lob
    @Column(name = "profile_photo", length = 100000)
    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    @Transient
    public String getFullName() {
        return getUsername();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return getUsername().equals(user.getUsername());
    }

    @Override
    public int hashCode() {
        return getUsername().hashCode();
    }
}
