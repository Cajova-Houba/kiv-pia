package cz.zcu.pia.valesz.web.vo;

import cz.zcu.pia.valesz.core.domain.Gender;
import cz.zcu.pia.valesz.core.domain.User;
import cz.zcu.pia.valesz.core.domain.Visibility;
import org.joda.time.LocalDate;
import org.joda.time.Years;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Model for profile updating form.
 */
public class ProfileUpdateForm {
    private String username;
    private String email;
    private String fullName;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date birthDate;
    private Gender gender;
    private Visibility profileVisibility;

    public ProfileUpdateForm() {
    }

    /**
     * Fills form model with current user data.
     * @param currentUserData
     */
    public ProfileUpdateForm(User currentUserData) {
        setUsername(currentUserData.getUsername());
        setEmail(currentUserData.getEmail());
        setFullName(currentUserData.getFullName());
        setBirthDate(currentUserData.getBirthDate());
        setGender(currentUserData.getGender());
        setProfileVisibility(currentUserData.getProfileVisibility());
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public Visibility getProfileVisibility() {
        return profileVisibility;
    }

    public void setProfileVisibility(Visibility profileVisibility) {
        this.profileVisibility = profileVisibility;
    }

    /**
     * Returns age as a string. If birth date is null '-' is returned.
     * @return
     */
    public String getAge() {
        if(getBirthDate() == null) {
            return "-";
        }
        LocalDate bd = LocalDate.fromDateFields(birthDate);
        LocalDate now = LocalDate.now();
        Years age = Years.yearsBetween(bd, now);

        return age.toString();
    }

    /**
     * Returns gender value in a displayable format.
     * @return
     */
    public String getDisplayGender() {
        Gender g = getGender();
        if(g == null) {
            return "-";
        } else {
            return g.displayName;
        }
    }

    public String getDisplayVisibility() {
        if(getProfileVisibility() == null) {
            return "-";
        } else {
            return getProfileVisibility().displayName;
        }
    }

    public Visibility[] getPossibleVisibilities() {
        return Visibility.values();
    }

    @Override
    public String toString() {
        return "ProfileUpdateForm{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", fullName='" + fullName + '\'' +
                ", birthDate=" + birthDate +
                ", gender=" + gender +
                ", profileVisibility=" + profileVisibility +
                '}';
    }
}
