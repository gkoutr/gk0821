package service;

import factory.ToolFactory;
import models.RentalAgreement;
import models.tools.Tool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import static java.time.temporal.TemporalAdjusters.firstInMonth;

public class ToolService {

    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");

    //checkout - will return a Rental Agreement Object
    public static RentalAgreement checkoutTool(String toolCode, int rentalDayCount, int discountPercent, String checkoutDate) throws Exception {

        Tool tool = ToolFactory.getTool(toolCode);
        String dueDate = getDueDate(checkoutDate, rentalDayCount);
        int chargeDays = getChargeDays(tool, checkoutDate, dueDate);
        float preDiscountCharge = chargeDays * tool.getDailyCharge();
        float discountAmount = preDiscountCharge * (discountPercent * .01f);
        float finalAmount = preDiscountCharge - discountAmount;
        return new RentalAgreement.Builder(toolCode, rentalDayCount, discountPercent, checkoutDate)
                .setChargeDays(chargeDays)
                .setDiscountAmount(discountAmount)
                .setPreDiscountCharge(preDiscountCharge)
                .setToolBrand(tool.getBrand())
                .setDailyRentalCharge(tool.getDailyCharge())
                .setDueDate(dueDate)
                .setFinalAmount(finalAmount)
                .build();
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
        LocalDate independenceDayObserved = getIndependenceHoliday(start.getYear());
        int chargeCount = 0;
        for (LocalDate date = start; !date.isAfter(end); date = date.plusDays(1)) {
            //checks for labor day
            if (!tool.isHolidayCharge() && (date.equals(independenceDayObserved) || isLaborDay(date))) {
                continue;
            }

            if (!tool.isWeekdayCharge() && isWeekday(date))
                continue;

            if (!tool.isWeekendCharge() && (isWeekend(date)))
                continue;

            chargeCount++;
        }
        return chargeCount;
    }

    public static boolean isWeekday(LocalDate date) {
        return !(date.getDayOfWeek() == DayOfWeek.SATURDAY && date.getDayOfWeek() == DayOfWeek.SUNDAY);
    }

    private static boolean isWeekend(LocalDate date) {
        return date.getDayOfWeek() == DayOfWeek.SATURDAY && date.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    private static boolean isLaborDay(LocalDate date) {
        return date.getMonth() == Month.SEPTEMBER && date.equals(date.with(firstInMonth(DayOfWeek.MONDAY)));
    }

    private static LocalDate getIndependenceHoliday(int year) {
        //If July 4th is on Sunday, then holiday is Monday
        //If July 4th is on Saturday, then holiday is Friday
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

    private static Date addDays(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }
}
