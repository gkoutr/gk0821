package utils;

import org.junit.Assert;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static utils.DateUtils.*;

public class DateUtilsTest {

    @Test
    public void testValidWeekend(){
        LocalDate saturday = LocalDate.of(2021, 8, 28);
        Assert.assertTrue("LocalDate object is not on saturday", isWeekend(saturday));

        LocalDate sunday = LocalDate.of(2021, 8, 29);
        Assert.assertTrue("LocalDate object is not on sunday", isWeekend(sunday));
    }

    @Test
    public void testInvalidWeekend(){
        LocalDate wednesday = LocalDate.of(2021, 8, 25);
        Assert.assertFalse(isWeekend(wednesday));
    }

    @Test
    public void testValidWeekday(){
        LocalDate wednesday = LocalDate.of(2021, 8, 25);
        Assert.assertTrue(isWeekday(wednesday));
    }


    @Test
    public void testInvalidWeekday(){
        LocalDate saturday = LocalDate.of(2021, 8, 28);
        Assert.assertFalse(isWeekday(saturday));
    }

    @Test
    public void testValidLaborDay(){
        LocalDate laborDay = LocalDate.of(2021, 9, 6);
        Assert.assertTrue(isLaborDay(laborDay));
    }

    @Test
    public void testInvalidLaborDay(){
        LocalDate notLaborDay = LocalDate.of(2021, 9, 10);
        Assert.assertFalse(isLaborDay(notLaborDay));
    }

    @Test
    public void testIndependenceDay(){
        LocalDate independenceDay = getIndependenceHoliday(2019);
        Assert.assertTrue(independenceDay.getDayOfMonth() == 4);
    }

    @Test
    public void testIndependenceDayObserved(){
        LocalDate monday = getIndependenceHoliday(2021);
        Assert.assertTrue(monday.getDayOfWeek() == DayOfWeek.MONDAY);
    }


}
