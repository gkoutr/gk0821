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

    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yy");

    public static RentalAgreement checkoutTool(String toolCode, int rentalDayCount, int discountPercent, String checkoutDate) throws Exception {
        validateCheckoutData(rentalDayCount, discountPercent);
        Tool tool = ToolFactory.getTool(toolCode);
        String dueDate = getDueDate(checkoutDate, rentalDayCount);
        int chargeDays = getChargeDays(tool, checkoutDate, dueDate);
        float preDiscountCharge = chargeDays * tool.getDailyCharge();
        float discountAmount = preDiscountCharge * (discountPercent * .01f);
        float finalAmount = preDiscountCharge - discountAmount;
        return new RentalAgreement.Builder(toolCode, checkoutDate)
                .setRentalDays(rentalDayCount)
                .setDiscountPercent(discountPercent)
                .setChargeDays(chargeDays)
                .setDiscountAmount(discountAmount)
                .setPreDiscountCharge(preDiscountCharge)
                .setToolBrand(tool.getBrand())
                .setDailyRentalCharge(tool.getDailyCharge())
                .setDueDate(dueDate)
                .setFinalAmount(finalAmount)
                .build();
    }

    private static String getDueDate(String checkoutDate, int rentalDays) throws ParseException {
        Date date = dateFormatter.parse(checkoutDate);
        return dateFormatter.format(addDays(date, rentalDays));
    }

    /**
     * Validates the rentalDayCount and discountPercent and throws an Exception if they are invalid.
     *
     * @param rentalDayCount - verifies it's greater or equal to 1
     * @param discountPercent - verifies it's between 0 - 100
     *
     * @throws Exception
     */
    public static void validateCheckoutData(int rentalDayCount, int discountPercent) throws Exception {
        if (rentalDayCount < 1)
            throw new Exception("Rental day count must be greater than 1");
        if (discountPercent < 0 || discountPercent > 100)
            throw new Exception("Discount percent must be within 0-100");
    }

    /**
     * Counts the number of chargeable days, from day after checkout through the including due date, excluding 'no charge'
     * days as specified from tool type. This will convert the checkoutDate and dueDate from a String -> Date, then from a
     * Date -> LocalDate. We then loop through each date starting from the CheckoutDate -> DueDate and check if each day is a chargeable day.
     * This will depend on the Tool weekday/weekend/holiday charge.
     *
     * @param checkoutDate
     * @param dueDate
     * @return
     * @throws ParseException
     */
    private static int getChargeDays(Tool tool, String checkoutDate, String dueDate) throws ParseException {
        //Convert checkoutDate to a Date and add a day to it
        Date actualCheckoutDate = addDays(dateFormatter.parse(checkoutDate), 1);

        //Convert dueDate to a Date
        Date actualDueDate = dateFormatter.parse(dueDate);

        //Converts both dates to a LocalDate
        LocalDate start = actualCheckoutDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate end = actualDueDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate independenceDayObserved = getIndependenceHoliday(start.getYear());
        int chargeCount = 0;
        for (LocalDate date = start; !date.isAfter(end); date = date.plusDays(1)) {
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
        return date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    private static boolean isLaborDay(LocalDate date) {
        return date.getMonth() == Month.SEPTEMBER && date.equals(date.with(firstInMonth(DayOfWeek.MONDAY)));
    }

    private static LocalDate getIndependenceHoliday(int year) {
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
