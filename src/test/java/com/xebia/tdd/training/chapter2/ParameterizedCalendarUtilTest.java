package com.xebia.tdd.training.chapter2;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ParameterizedCalendarUtilTest {

    private Date date;
    private boolean isWeekend;

    public ParameterizedCalendarUtilTest(Date date, boolean isWeekend) {
        this.date = date;
        this.isWeekend = isWeekend;
    }

    @SuppressWarnings("deprecation")
    @Parameters
    public static Iterable<Object[]> dates() {
        return Arrays.asList(new Object[][] {
                { new Date(114, Calendar.SEPTEMBER, 27), true },
                { new Date(114, Calendar.SEPTEMBER, 28), true },
                { new Date(114, Calendar.SEPTEMBER, 23), false },
                { new Date(114, Calendar.SEPTEMBER, 26), false },
        });
    }

    @Test
    public void testIsWeekend() throws Exception {
        Assert.assertEquals(isWeekend, CalendarUtil.isWeekend(date));
    }
}
