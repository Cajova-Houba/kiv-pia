package cz.zcu.pia.valesz.core.service;


import cz.zcu.pia.valesz.core.domain.User;
import cz.zcu.pia.valesz.web.vo.ProfileUpdateForm;
import cz.zcu.pia.valesz.web.vo.UserForm;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Set;

/**
 * Interface for user-related actions.
 */
public interface UserManager extends UserDetailsService{

    // errors which may rise during registration validation or profile update validation
    String ERR_USERNAME_WRONG = "errUsernameWrong";
    String ERR_USERNAME_EXISTS = "errUsernameExists";
    String ERR_WRONG_EMAIL = "errWrongEmail";
    String ERR_WRONG_PASS = "errWrongPass";
    String ERR_PASS_DONT_MATCH = "errPassDontMatch";
    String ERR_WRONG_FULL_NAME = "errWrongFullName";
    String ERR_TOO_YOUNG_MAN = "errTooYoungMan";
    String ERR_NOT_AGENDER = "errNotAGender";
    String ERR_SHUT_UP_AND_ACCEPT = "errShutUpAndAccept";
    String ERR_WRONG_PROFILE_VISIBILITY = "errWrongProfileVisibility";


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
     * Validates data passed from registration form.
     *
     * @param toBeValidated Object containing data to be validated.
     *              username Username must be unique otherwise ERR_USERNAME_EXISTS will be added to returned set is it exists already and ERR_USERNAME_WRONG is it's null, empty, or has bad format.
     *              email Email must have right format otherwise ERR_WRONG_EMAIL will be added to returned set.
     *              password Password must have right format otherwise ERR_WRONG_PASS will be added to returned set.
     *              passwrodConf Password confirmation must the same as password otherwise REG_PAS_DONT_MATCH will be added to returned set.
     *              fullName Full name, yeah no one cares about your name, just fill it.
     *              birthDate Birth date can be null otherwise it must be at least 13 years old. ERR_TOO_YOUNG_MAN will be added to returned set if error occurs.
     *              gender Gender must have correct format otherwise ERR_NOT_AGENDER will be added to returned set.
 *                  accpetTerms Must be true otherwise ERR_SHUT_UP_AND_ACCEPT will be added to returned set.
     * @return Set with error codes.
     */
    Set<String> validateRegistration(UserForm toBeValidated);

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
    User registerUser(User toBeCreated);

    /**
     * Updates user's profile photo. The old one is deleted from database, if it's not on of the default ones.
     * @param user User which will have photo updated.
     * @param profilePhotoData New profile photo data.
     * @return User with updated profile photo.
     */
    User updateProfilePhoto(User user, byte[] profilePhotoData);

}
