package cz.zcu.pia.valesz.core.domain;

import cz.zcu.pia.valesz.web.vo.UserForm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
public class User implements UserDetails {

    /**
     * Database id.
     */
    private Long id;

    /**
     * User's email.
     */
    private String email;

    /**
     * Unique username. Can be used as businesss key.
     */
    private String username;

    /**
     * Hash of user's password.
     */
    private String passwordHash;

    /**
     * User's birth date.
     */
    private Date birthDate;

    /**
     * User's full name. This will be displayed in most places.
     */
    private String fullName;

    /**
     * User's gender.
     */
    private Gender gender;

    /**
     * Visibility of user's profile to the rest of the world.
     */
    private Visibility profileVisibility;

    /**
     * Social identity provider. NONE if user has registered himself without any social network.
     * Not used.
     */
    private SocialIdentityProvider socialIdentityProvider;

    /**
     * User's profile photo.
     */
    private KivbookImage profilePhoto;

    /**
     * User's authorities. Not actually persisted. Used for Spring Security to work properly.
     */
    private Collection<? extends GrantedAuthority> authorities;

    /**
     * Default constructor which uses empty username, password and list of granted authorities.
     */
    public User() {
        this("default", "default", Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
    }

    public User(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.passwordHash = password;
        this.authorities = authorities;
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

    /**
     * Creates new user object from userForm model. It is expected that userForm was already validated.
     *
     * @param userForm Model containing data from user form.
     */
    public User(UserForm userForm, Date birthDate) {
        this(userForm.getEmail(), userForm.getUsername(), userForm.getPassword(), birthDate, userForm.getFullName(), Gender.webNameToGender(userForm.getGender()));
    }

    /**
     * Constructor for user used in registration. Profile visibility is set to default.
     * It is expected that everything was validated before.
     *
     * @param email User's email.
     * @param username User's username.
     * @param passwordHash Hash of user's password.
     * @param birthDate User's birth date (may be null).
     * @param fullName User's full name.
     * @param gender User's gender.
     */
    public User(String email, String username, String passwordHash, Date birthDate, String fullName, Gender gender) {
        this.email = email;
        this.username = username;
        this.passwordHash = passwordHash;
        this.birthDate = birthDate;
        this.fullName = fullName;
        this.gender = gender;
        this.profileVisibility = Visibility.REGISTERED_USERS;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    @Column(unique = true, nullable = false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    @Transient
    public String getPassword() {
        return getPasswordHash();
    }

    @Column(name = "password_hash", nullable = false)
    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
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

    @Column(name = "profile_visibility", nullable = false)
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_photo_id")
    public KivbookImage getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(KivbookImage profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    @Column(name = "full_name", length = 500, nullable = false)
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    @Transient
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    @Transient
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @Transient
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @Transient
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @Transient
    public boolean isEnabled() {
        return true;
    }

    @Override
    @Transient
    public String getName() {
        return getUsername();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return username != null ? username.equals(user.username) : user.username == null;
    }

    @Override
    public int hashCode() {
        return username != null ? username.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", fullName='" + fullName + '\'' +
                ", profileVisibility=" + profileVisibility +
                '}';
    }
}
