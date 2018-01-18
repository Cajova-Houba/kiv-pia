package cz.zcu.pia.valesz.web.vo;

import org.springframework.format.annotation.DateTimeFormat;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 * A model object for user registration form.
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
    private String gender;
    private String profilePhoto;
    private boolean acceptTerms;

    public UserForm() {
        setUsername("");
        setEmail("");
        setPassword("");
        setPasswordConf("");
        setFullName("Bobby McJohnson");
        setGender("male");
        setBirthDate(new Date());
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
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public boolean isAcceptTerms() {
        return acceptTerms;
    }

    public void setAcceptTerms(boolean acceptTerms) {
        this.acceptTerms = acceptTerms;
    }
}
