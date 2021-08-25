import models.RentalAgreement;
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
            System.out.println("Enter Discount Percent. (as a whole number: 0-100)");
            int discountPercent = scanner.nextInt();
            System.out.println("Enter Check Out Date in the following format: mm/dd/yyyy");
            String checkoutDate = scanner.next();
            System.out.println("-------------------------------------");
            RentalAgreement rentalAgreement = ToolService.checkoutTool(toolCode, rentalDayCount, discountPercent, checkoutDate);
            rentalAgreement.printRentalAgreement();
        }
    }
}
