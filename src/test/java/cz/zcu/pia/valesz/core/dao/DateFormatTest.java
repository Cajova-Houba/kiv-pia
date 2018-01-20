package cz.zcu.pia.valesz.core.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Test case to test supported date formats.
 */
@RunWith(JUnit4.class)
public class DateFormatTest {

    public static final String DATE = "1980-03-14";
    public static final String DATE_2 = "1980-3-14";
    public static final String DATE_3 = "1980- 3- 14";
    public static final String DATE_4 = "14.03.1980";
    public static final String DATE_5 = "9.3.1980";
    public static final String DATE_6 = "9. 3. 1980";

    @Test
    public void testDateFormat1() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d = sdf.parse(DATE);
        System.out.println(d);
        d = sdf.parse(DATE_2);
        System.out.println(d);
        d = sdf.parse(DATE_3);
        System.out.println(d);
    }

    @Test
    public void testDateFormat2() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Date d = sdf.parse(DATE_4);
        System.out.println(d);
        d = sdf.parse(DATE_5);
        System.out.println(d);
        d = sdf.parse(DATE_6);
        System.out.println(d);
    }
}
