package cz.zcu.pia.valesz.web.validation;

import cz.zcu.pia.valesz.core.domain.Gender;
import cz.zcu.pia.valesz.core.service.KivbookDateUtils;
import cz.zcu.pia.valesz.core.service.UserManager;
import cz.zcu.pia.valesz.web.vo.UserForm;
import org.apache.commons.validator.routines.EmailValidator;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Date;

/**
 * Class to validate user's input from registration form.
 */
public class UserRegistrationValidator implements Validator {

    public static final int USERNAME_MIN = 3;
    public static final int USERNAME_MAX = 20;
    public static final int EMAIL_MIN = 6;
    public static final int EMAIL_MAX = 100;
    public static final int PSW_MIN = 8;
    public static final int PSW_MAX = 100;
    public static final int FULL_NAME_MIN = 5;
    public static final int FULL_NAME_MAX = 20;

    public static final int MIN_AGE = 13;


    @Autowired
    private UserManager userManager;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserForm.class.equals(clazz);
    }

    @Override
    public void validate(@Nullable Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username","user.username.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "user.email.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "user.password.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordConf", "user.password.confirm.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fullName", "user.fullName.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender", "user.gender.empty");

        UserForm userForm = (UserForm) target;
        if(userForm.getUsername() != null && userManager.loadByUsername(userForm.getUsername()) != null) {
            errors.rejectValue("username", "user.username.taken");
        }

        if(!userForm.isAcceptTerms()) {
            errors.rejectValue("acceptTerms", "user.terms.accept");
        }

        if(userForm.getEmail() != null &&  !EmailValidator.getInstance().isValid(userForm.getEmail())) {
            errors.rejectValue("email", "user.email.format");
        }

        String psw = userForm.getPassword();
        String pswConf = userForm.getPasswordConf();
        if(psw != null && pswConf != null && !psw.isEmpty() && !pswConf.isEmpty() && !psw.equals(pswConf)) {
            errors.rejectValue("passwordConf", "user.password.confirm.match");
        }

        // check date format and age
        if(userForm.getBirthDate() != null && !userForm.getBirthDate().isEmpty()) {
            Date parsedDate = KivbookDateUtils.parseDate(userForm.getBirthDate(), UserForm.DATE_FORMAT_1, UserForm.DATE_FORMAT_2);
            if(parsedDate == null) {
                errors.rejectValue("birthDate","user.birthDate.format");
            } else {
                // date was parsed, check age
                Date maxBirthDate = new DateTime().minusYears(MIN_AGE).toDate();
                if(parsedDate.after(maxBirthDate)) {
                    errors.rejectValue("birthDate", "user.birthDate.young");
                }
            }
        }

        String gender = userForm.getGender();
        if(gender != null && !gender.isEmpty() && Gender.webNameToGender(gender) == null) {
            errors.rejectValue("gender", "user.gender.value");
        }

        // check field lengths
        String errCode = "user.field.length";
        checkStringLength(errors, userForm.getUsername(), USERNAME_MIN, USERNAME_MAX, "username", errCode);
        checkStringLength(errors, userForm.getEmail(), EMAIL_MIN, EMAIL_MAX, "email", errCode);
        checkStringLength(errors, userForm.getPassword(), PSW_MIN, PSW_MAX, "password", errCode);
        checkStringLength(errors, userForm.getFullName(), FULL_NAME_MIN, FULL_NAME_MAX, "fullName", errCode);
    }

    /**
     * Checks if the string's length is in [min;max] range (inclusive).
     * If not adds errorCode to errors.
     * If the checked string is null, nothing happens.
     *
     * @param errors Object containing validation errors.
     * @param toBeChecked String to be checked.
     * @param minLength Min length inclusive.
     * @param maxLength Max length inclusive.
     * @param fieldName name of the checked field.
     * @param errCode Code of error (in message.properties).
     */
    private void checkStringLength(Errors errors, String toBeChecked, int minLength, int maxLength, String fieldName, String errCode) {
       if(toBeChecked != null) {
           if(toBeChecked.length() < minLength || toBeChecked.length() > maxLength) {
               errors.rejectValue(fieldName, errCode, new Object[] {minLength, maxLength}, "Wrong length!");
           }
       }
    }
}
