package cz.zcu.pia.valesz.core.domain;

/**
 * Possible user's gender.
 */
public enum Gender {


    MALE("male", "Male"),

    FEMALE("female", "Female"),

    MAYONNAISE("mayo", "Mayonnaise");

    /**
     * Name used in web formular.
     */
    public final String webName;

    /**
     * Displayable name.
     */
    public final String displayName;

    Gender(String webName, String displayName) {
        this.webName = webName;
        this.displayName = displayName;
    }

    /**
     * Converts gender web name to Gender object.
     * @param webName Web name to be converted.
     * @return Gender or null if invalid name is provided.
     */
    public static Gender webNameToGender(String webName) {

        if(MALE.webName.equals(webName)) {
            return MALE;
        } else if (FEMALE.webName.equals(webName)) {
            return FEMALE;
        } else if (MAYONNAISE.webName.equals(webName)) {
            return MAYONNAISE;
        } else {
            return null;
        }
    }
}
