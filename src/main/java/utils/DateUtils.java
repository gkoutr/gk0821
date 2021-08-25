package utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;

import static java.time.temporal.TemporalAdjusters.firstInMonth;

/**
 * Helper class for Java Date API.
 */
public class DateUtils {

    /**
     * Checks if the LocalDate object is a weekday
     *
     * @param date
     * @return
     */
    public static boolean isWeekday(LocalDate date) {
        return !(date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY);
    }

    /**
     * Checks if the LocalDate object is a weekend
     * @param date
     * @return
     */
    public static boolean isWeekend(LocalDate date) {
        return date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    /**
     * Checks if the LocalDate object is during labor day
     *
     * @param date
     * @return
     */
    public static boolean isLaborDay(LocalDate date) {
        return date.getMonth() == Month.SEPTEMBER && date.equals(date.with(firstInMonth(DayOfWeek.MONDAY)));
    }

    /**
     * Retrieves the federal holiday date of July 4th based on the year. If the date falls on a weekend, it will be observed
     * to the closest weekday (Friday if on Saturday, Monday if on Sunday)
     *
     * @param year
     * @return
     */
    public static LocalDate getIndependenceHoliday(int year) {
        int month = 7;
        LocalDate independenceDay = LocalDate.of(year, month, 4);
        switch (independenceDay.getDayOfWeek()) {
            case SUNDAY:
                return LocalDate.of(year, month, 5);
            case SATURDAY:
                return LocalDate.of(year, month, 3);
            default:
                return independenceDay;
        }
    }

    /**
     * Increments a Date object based on the number of days
     *
     * @param date
     * @param days
     * @return
     */
    public static Date addDays(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }
}
