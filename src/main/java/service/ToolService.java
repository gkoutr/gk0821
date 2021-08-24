package service;

import factory.ToolFactory;
import models.RentalAgreement;
import models.Tool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.TemporalAdjuster;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static java.time.temporal.TemporalAdjusters.firstInMonth;

public class ToolService {

    private static SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");

    //checkout - will return a Rental Agreement Object
    public static RentalAgreement checkoutTool(String toolCode, int rentalDayCount, int discountPercent, String checkoutDate) throws Exception {

        Tool tool = ToolFactory.getTool(toolCode);
        String date = getDueDate(checkoutDate, rentalDayCount);
        long days = getChargeDays(tool, checkoutDate, date);
        return new RentalAgreement();
    }

    //methods to calculate
    private static String getDueDate(String checkoutDate, int rentalDays) throws ParseException {
        Date date = dateFormatter.parse(checkoutDate);
        return dateFormatter.format(addDays(date, rentalDays));
    }

    /**
     * Counts the number of chargeable days, from day after checkout through the including due date, excluding 'no charge'
     * days as specified from tool type.
     *
     * @param checkoutDate
     * @param dueDate
     * @return
     * @throws ParseException
     */
    private static int getChargeDays(Tool tool, String checkoutDate, String dueDate) throws ParseException {
        //Get date the day AFTER checkout
        Date actualCheckoutDate = addDays(dateFormatter.parse(checkoutDate), 1);

        //Get date of the due date
        Date actualDueDate = dateFormatter.parse(dueDate);

        LocalDate start = actualCheckoutDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate end = actualDueDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        int dateCount = 0;
        for (LocalDate date = start; !date.isAfter(end); date = date.plusDays(1)) {
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            //checks for labor day
            if (tool.isHolidayCharge() && date.getMonth() == Month.SEPTEMBER) {
                if (date.equals(date.with(firstInMonth(DayOfWeek.MONDAY)))) {
                    continue;
                }
            }

            if (tool.isWeekendCharge() && (isWeekend(date)))
                continue;
            dateCount++;
        }
        return dateCount;
    }

    private static boolean isWeekend(LocalDate dt) {
        if (dt.getDayOfWeek() == DayOfWeek.SATURDAY && dt.getDayOfWeek() == DayOfWeek.SUNDAY)
            return true;

        return false;
    }
    
    private static Date addDays(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }
}
