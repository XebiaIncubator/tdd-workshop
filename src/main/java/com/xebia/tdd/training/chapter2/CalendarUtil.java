package com.xebia.tdd.training.chapter2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CalendarUtil {

    /**
     * Check if the date is a Weekend or not
     * 
     * @param date
     * @return
     */
    public static boolean isWeekend(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return (dayOfWeek == Calendar.SUNDAY || dayOfWeek == Calendar.SATURDAY);
    }

    /**
     * Converts a string of dates separated by commas(,) to a List<Date>
     * 
     * @param stringOfDates
     * @return
     * @throws ParseException
     */
    public static List<Date> getDatesFromStringOfDates(String stringOfDates) throws ParseException {
        List<Date> dates = new ArrayList<Date>();
        String[] dateStrings = stringOfDates.split(",");
        for (String dateString : dateStrings) {
            dates.add(new SimpleDateFormat("ddMMMyyyy", Locale.ENGLISH).parse(dateString));
        }
        return dates;
    }

}
