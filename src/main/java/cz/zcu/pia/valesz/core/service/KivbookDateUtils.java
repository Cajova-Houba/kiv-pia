package cz.zcu.pia.valesz.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Simple class for wrapping date parsing.
 */
public class KivbookDateUtils {

    private static final Logger log = LoggerFactory.getLogger(KivbookDateUtils.class);

    /**
     * Tries to parse a date by using provided formats.
     *
     * @param date Date to be parsed.
     * @param formats Date formats to be used for parsing.
     * @return Parsed date or null it the format is wrong.
     */
    public static Date parseDate(String date, String... formats) {
        Date parsedDate = null;
        for (String format : formats) {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            try {
                parsedDate = sdf.parse(date);
            } catch (ParseException e) {
                log.trace("Date {} is not parsable with date format {}.", format);
            }
        }

        return parsedDate;
    }
}
