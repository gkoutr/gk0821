package service;

import factory.ToolFactory;
import models.RentalAgreement;
import models.tools.Tool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static utils.DateUtils.*;

public class ToolService {

    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yy");

    /**
     * Builds a RentalAgreement object based on the provided parameters.
     *
     * @param toolCode        - Unique identifier for a tool instance
     * @param rentalDayCount  - The number of days for which the customer wants to rent the tool
     * @param discountPercent - Discount percent that will be applied to the total (must be between 0-100)
     * @param checkoutDate    - Date of checkout
     * @return
     * @throws Exception
     */
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
                .setToolType(tool.getClass().getSimpleName())
                .setDailyRentalCharge(tool.getDailyCharge())
                .setDueDate(dueDate)
                .setFinalAmount(finalAmount)
                .build();
    }

    /**
     * Gets the due date based on the checkout date and rental days.
     *
     * @param checkoutDate - Checkout date from client in String format
     * @param rentalDays   - Number of rental days
     * @return
     * @throws ParseException
     */
    private static String getDueDate(String checkoutDate, int rentalDays) throws ParseException {
        Date date = dateFormatter.parse(checkoutDate);
        return dateFormatter.format(addDays(date, rentalDays));
    }

    /**
     * Validates the rentalDayCount and discountPercent and throws an Exception if they are invalid.
     *
     * @param rentalDayCount  - Verifies it's greater or equal to 1
     * @param discountPercent - Verifies it's between 0 - 100
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
     * @param tool         - Tool instance that will be used to determine which days are charged
     * @param checkoutDate - Start date
     * @param dueDate      - End date
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
}
