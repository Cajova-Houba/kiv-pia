package cz.zcu.pia.valesz.web.vo;

import cz.zcu.pia.valesz.core.domain.Gender;
import cz.zcu.pia.valesz.core.domain.User;
import cz.zcu.pia.valesz.core.domain.Visibility;
import org.joda.time.LocalDate;
import org.joda.time.Years;
import org.springframework.format.annotation.DateTimeFormat;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 * A model object for user forms and profile display.
 */
@XmlRootElement
public class UserForm implements Serializable {

    private String username;
    private String email;
    private String password;
    private String passwordConf;
    private String fullName;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date birthDate;
    private String stringBirthDate;
    private String gender;
    private Visibility profileVisibility;
    private boolean acceptTerms;

    public UserForm() {
        setUsername("");
        setEmail("");
        setPassword("");
        setPasswordConf("");
        setFullName("Bobby McJohnson");
        setGender("male");
        setBirthDate(new Date());
        setProfileVisibility(Visibility.REGISTERED_USERS);
        setAcceptTerms(false);
    }

    /**
     * Creates a form model for user object.
     * password and passwordConf are set to passwordHash and should not be used.
     * acceptTerms is set to false;
     *
     * @param user
     */
    public UserForm(User user) {
        setUsername(user.getUsername());
        setEmail(user.getEmail());
        setPassword(user.getPasswordHash());
        setPasswordConf(user.getPasswordHash());
        setFullName(user.getFullName());
        setGender(user.getGender().webName);
        if(user.getBirthDate() != null) {
            setBirthDate(user.getBirthDate());
        }
        setProfileVisibility(user.getProfileVisibility());
        setAcceptTerms(false);
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConf() {
        return passwordConf;
    }

    public void setPasswordConf(String passwordConf) {
        this.passwordConf = passwordConf;
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
        if(birthDate != null) {
            setStringBirthDate(birthDate.toString());
        }
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Visibility getProfileVisibility() {
        return profileVisibility;
    }

    public void setProfileVisibility(Visibility profileVisibility) {
        this.profileVisibility = profileVisibility;
    }

    public String getStringBirthDate() {
        return stringBirthDate;
    }

    public void setStringBirthDate(String stringBirthDate) {
        this.stringBirthDate = stringBirthDate;
    }

    public boolean isAcceptTerms() {
        return acceptTerms;
    }

    public void setAcceptTerms(boolean acceptTerms) {
        this.acceptTerms = acceptTerms;
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
        Gender g = Gender.webNameToGender(getGender());
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

}
