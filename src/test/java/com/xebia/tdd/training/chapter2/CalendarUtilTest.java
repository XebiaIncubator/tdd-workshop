package com.xebia.tdd.training.chapter2;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

public class CalendarUtilTest {

    @SuppressWarnings("deprecation")
    @Test
    public void testIsWeekend() throws Exception {
        boolean isWeekend = CalendarUtil.isWeekend(new Date(114, Calendar.SEPTEMBER, 28));
        Assert.assertTrue(isWeekend);
    }

    @SuppressWarnings("deprecation")
    @Test
    public void testGetDatesFromStringOfDates() throws Exception {
        Date fromDate = new Date(114, Calendar.SEPTEMBER, 20);
        Date toDate = new Date(114, Calendar.SEPTEMBER, 22);
        List<Date> expectedDates = new ArrayList<Date>();
        expectedDates.add(fromDate);
        expectedDates.add(toDate);
        List<Date> datesFromStringOfDates = CalendarUtil.getDatesFromStringOfDates("20Sep2014,22Sep2014");
        Assert.assertEquals(expectedDates, datesFromStringOfDates);
    }

}
