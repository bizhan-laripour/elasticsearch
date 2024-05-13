package org.example.util;

import com.github.sbahmani.jalcal.util.DateException;
import com.github.sbahmani.jalcal.util.JalCal;

import java.text.FieldPosition;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author a.mehdizadeh on 5/5/2024
 */
public class CalenderUtil {


    public static Date jalaliToGregorian(String date) throws DateException, ParseException {
        Date d = JalCal.jalaliToGregorian(date);
        Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateString =simpleDateFormat.format(d);
        return simpleDateFormat.parse(dateString);
    }

    public static String gregorianToJalali(Date date) {
        return JalCal.gregorianToJalaliDate(date , false);
    }

    public static String convertJalaliDateWithFormat(String date) throws DateException {
        Date d = JalCal.jalaliToGregorian(date);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(d);
    }


}
