package cz.zcu.pia.valesz.core.service;


import cz.zcu.pia.valesz.core.domain.User;
import cz.zcu.pia.valesz.web.vo.UserForm;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Date;
import java.util.Set;

/**
 * Interface for user-related actions.
 */
public interface UserManager extends UserDetailsService{

    // errors which may rise during registration validation
    String REG_USERNAME_WRONG = "errUsernameWrong";
    String REG_USERNAME_EXISTS = "errUsernameExists";
    String REG_WRONG_EMAIL = "errWrongEmail";
    String REG_WRONG_PASS = "errWrongPass";
    String REG_PASS_DONT_MATCH = "errPassDontMatch";
    String REG_WRONG_FULL_NAME = "errWrongFullName";
    String REG_TOO_YOUNG_MAN = "errTooYoungMan";
    String REG_NOT_A_GENDER = "errNotAGender";
    String REG_SHUT_UP_AND_ACCEPT = "errShutUpAndAccept";

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
     * Validates registration values and returns empty set if everything is ok.
     *
     * @param username Username must be unique otherwise REG_USERNAME_EXISTS will be added to returned set is it exists already
     *                 and REG_USERNAME_WRONG is it's null, empty, or has bad format.
     * @param email Email must have right format otherwise REG_WRONG_EMAIL will be added to returned set.
     * @param password Password must have right format otherwise REG_WRONG_PASS will be added to returned set.
     * @param passwrodConf Password confirmation must the same as password otherwise REG_PAS_DONT_MATCH will be added to returned set.
     * @param fullName Full name, yeah no one cares about your name, just fill something.
     * @param birthDate Birth date must be at least 13 years old otherwise REG_TOO_YOUNG_MAN will be added to returned set.
     * @param gender Gender must have correct format otherwise REG_NOT_A_GENDER will be added to returned set.
     * @param acceptTerms Must be true otherwise REG_SHUT_UP_AND_ACCEPT will be added to returned set.
     * @return Set containing errors. Empty set if everything is ok.
     */
    @Deprecated
    Set<String> validateRegistration(String username,
                             String email,
                             String password,
                             String passwrodConf,
                             String fullName,
                             Date birthDate,
                             String gender,
                             boolean acceptTerms
    );

    /**
     * Validates data passed from registration form.
     *
     * @param toBeValidated Object containing data to be validated.
     * username Username must be unique otherwise REG_USERNAME_EXISTS will be added to returned set is it exists already and REG_USERNAME_WRONG is it's null, empty, or has bad format.
     * email Email must have right format otherwise REG_WRONG_EMAIL will be added to returned set.
     * password Password must have right format otherwise REG_WRONG_PASS will be added to returned set.
     * passwrodConf Password confirmation must the same as password otherwise REG_PAS_DONT_MATCH will be added to returned set.
     * fullName Full name, yeah no one cares about your name, just fill something.
     * birthDate Birth date must be at least 13 years old otherwise REG_TOO_YOUNG_MAN will be added to returned set.
     * gender Gender must have correct format otherwise REG_NOT_A_GENDER will be added to returned set.
     * accpetTerms Must be true otherwise REG_SHUT_UP_AND_ACCEPT will be added to returned set.
     * @return
     */
    Set<String> validateRegistration(UserForm toBeValidated);

    /**
     * Registers new user. It is assumed that user parameters were already validated before.
     * Password is hashed before saving.
     *
     * @param toBeCreated User to be registered.
     * @return Registered user.
     */
    User registerUser(User toBeCreated);

}
