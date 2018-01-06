package cz.zcu.pia.valesz.core.domain;

/**
 * Possible user's gender.
 */
public enum Gender {


    MALE("male"),

    FEMALE("female"),

    MAYONNAISE("mayo");

    /**
     * Name used in web formular.
     */
    public final String webName;

    Gender(String webName) {
        this.webName = webName;
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
