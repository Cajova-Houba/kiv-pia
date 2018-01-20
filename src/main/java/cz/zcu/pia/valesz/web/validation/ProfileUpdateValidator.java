package cz.zcu.pia.valesz.web.validation;

import cz.zcu.pia.valesz.core.service.KivbookDateUtils;
import cz.zcu.pia.valesz.web.vo.ProfileUpdateForm;
import cz.zcu.pia.valesz.web.vo.UserForm;
import org.apache.commons.validator.routines.EmailValidator;
import org.joda.time.DateTime;
import org.springframework.lang.Nullable;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Date;

/**
 * Validator for data obtained from user via profile update form.
 */
public class ProfileUpdateValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return ProfileUpdateForm.class.equals(aClass);
    }

    @Override
    public void validate(@Nullable Object o, Errors errors) {

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "user.email.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fullName", "user.fullName.empty");
        ValidationUtils.rejectIfEmpty(errors, "profileVisibility", "user.profileVisibility.empty");

        ProfileUpdateForm puf = (ProfileUpdateForm) o;
        if(!EmailValidator.getInstance().isValid(puf.getEmail())) {
            errors.rejectValue("email", "user.email.format");
        }

        // check format
        if(puf.getBirthDate() != null && !puf.getBirthDate().isEmpty()) {
            Date parsedDate = KivbookDateUtils.parseDate(puf.getBirthDate(), UserForm.DATE_FORMAT_1, UserForm.DATE_FORMAT_2);
            if(parsedDate == null ){
                errors.rejectValue("birthDate", "user.birthDate.format");
            } else {
                Date maxBirthDate = new DateTime().minusYears(13).toDate();
                if(parsedDate.after(maxBirthDate)) {
                    errors.rejectValue("birthDate", "user.birthDate.young");
                }
            }
        }
    }
}
