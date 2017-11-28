package cz.zcu.pia.valesz.core.domain;

import java.util.Date;
import java.util.List;

/**
 * Application user entity.
 */
public class User {

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
