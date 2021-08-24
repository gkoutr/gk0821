import service.ToolService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        {
            System.out.println("--------------Checkout---------------");
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter Tool Code");
            String toolCode = scanner.next();
            System.out.println("Enter Rental Day Count. e.g. 4");
            int rentalDayCount = scanner.nextInt();
            checkRentalDayCount(rentalDayCount);
            System.out.println("Enter Discount Percent. (as a whole number: 0-100)");
            int discountPercent = scanner.nextInt();
            checkDiscountPercent(discountPercent);
            System.out.println("Enter Check Out Date in the following format: mm/dd/yyyy");
            String checkoutDate = scanner.next();
            System.out.println("-------------------------------------");
            ToolService.checkoutTool(toolCode, rentalDayCount, discountPercent, checkoutDate);
        }
    }

    /**
     * Throws an exception if the rental day count is less than 1.
     *
     * @param rentalDayCount
     * @throws Exception
     */
    private static void checkRentalDayCount(int rentalDayCount) throws Exception {
        if (rentalDayCount < 1)
            throw new Exception("Rental day count must be greater than 1");
    }

    /**
     * Throws an exception if the discount percent is less than 0 or greater than 100
     *
     * @param discountPercent
     * @throws Exception
     */
    private static void checkDiscountPercent(int discountPercent) throws Exception {
        if (discountPercent < 0 || discountPercent > 100) {
            throw new Exception("Discount percent must be within 0-100");
        }
    }
}
