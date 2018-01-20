package cz.zcu.pia.valesz.core.service;


import cz.zcu.pia.valesz.core.domain.User;
import cz.zcu.pia.valesz.web.vo.ProfileUpdateForm;
import cz.zcu.pia.valesz.web.vo.UserForm;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Interface for user-related actions.
 */
public interface UserManager extends UserDetailsService{

    /**
     * Returns user with this id.
     * @param id Id.
     * @return User.
     */
    User findById(Long id);

    /**
     * Loads user and optionally also his profile photo.
     *
     * @param username Username.
     * @param fetchProfilePhoto If true, user's profile photo will be loaded also.
     * @return User with his profile photo or null if no user is found.
     */
    User loadByUsername(String username, boolean fetchProfilePhoto);

    /**
     * Loads user by his username.
     * @param username Username.
     * @return User or null if no user is found.
     */
    User loadByUsername(String username);

    /**
     * Returns true if user with this username already exists.
     * @param username Username.
     * @return True if the user with this username already exists.
     */
    boolean existsByUsername(String username);

    /**
     * Updates profile with new data. It is assumed that everything was already validated.
     *
     * @param toBeUpdated User to be updated.
     * @param updateData New data.
     * @return Updated user.
     */
    User updateUserProfile(User toBeUpdated, ProfileUpdateForm updateData);

    /**
     * Registers new user. It is assumed that user parameters were already validated before.
     * Password is hashed before saving.
     * First photo (if any) is assigned as a profile one.
     *
     * @param toBeCreated User to be registered.
     * @return Registered user.
     */
    User registerUser(UserForm toBeCreated);

    /**
     * Updates user's profile photo. The old one is deleted from database, if it's not on of the default ones.
     * @param user User which will have photo updated.
     * @param profilePhotoData New profile photo data.
     * @return User with updated profile photo.
     */
    User updateProfilePhoto(User user, byte[] profilePhotoData);

}
