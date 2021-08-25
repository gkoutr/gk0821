import models.RentalAgreement;
import org.junit.Assert;
import org.junit.Test;
import service.ToolService;

/**
 * Contains test validations for the ToolService
 */
public class ToolServiceTest {

    /**
     * Test case #1
     * Tool code: JAKR
     * Checkout date: 9/3/15
     * Rental days: 5
     * Discount: 101%
     * <p>
     * Expected output: Throws an exception due to invalid discount percent (above 100).
     * <p>
     * This test will run checkoutTool and verify that an Exception with the correct message is returned.
     *
     * @throws Exception
     */
    @Test
    public void testCaseOne() throws Exception {
        //Arrange
        String toolCode = "JAKR";
        int rentalDayCount = 5;
        int discountPercent = 101;
        String checkoutDate = "09/03/15";

        //Act
        Exception exception = Assert.assertThrows(Exception.class, () -> {
            ToolService.checkoutTool(toolCode, rentalDayCount, discountPercent, checkoutDate);
        });

        //Assert
        String expectedMessage = "Discount percent must be within 0-100";
        String actualMessage = exception.getMessage();
        Assert.assertTrue(actualMessage.equals(expectedMessage));

    }

    @Test
    public void testCaseTwo() throws Exception {
        //Arrange
        String toolCode = "LADW";
        int rentalDayCount = 3;
        int discountPercent = 10;
        String checkoutDate = "07/02/20";

        //Act
        RentalAgreement rentalAgreement = ToolService.checkoutTool(toolCode, rentalDayCount, discountPercent, checkoutDate);

        //Assert
        Assert.assertTrue("Tool code does not equal LADW (Ladder)", rentalAgreement.getToolCode().equals("LADW"));
        Assert.assertTrue("Tool Brand does not equal 'Werner'", rentalAgreement.getToolBrand().equals("Werner"));
        Assert.assertTrue("Checkout date does not equal '7/02/20'", rentalAgreement.getCheckoutDate().equals("07/02/20"));
        Assert.assertTrue("Rentals days does not equal 3", rentalAgreement.getRentalDays() == 3);
        Assert.assertTrue("Discount percent days does not equal 10", rentalAgreement.getDiscountPercent() == 10);
        Assert.assertTrue("Due date is not the correct date: '07/05/20'", rentalAgreement.getDueDate().equals("07/05/20"));
        Assert.assertTrue("Ladder daily charge does not equal 1.99", rentalAgreement.getDailyRentalCharge() == 1.99f);
        Assert.assertTrue("Charge days does not equal 2", rentalAgreement.getChargeDays() == 2);
        Assert.assertTrue("Pre-discount charge does not equal 3.98", rentalAgreement.getPreDiscountCharge() == 3.98f);
        Assert.assertTrue("Discount amount charge does not equal 0.40", rentalAgreement.getDiscountAmount() == 0.40f);
        Assert.assertTrue("Final charge does not equal 3.58", rentalAgreement.getFinalAmount() == 3.58f);
    }

    @Test
    public void testCaseThree() throws Exception {
        //Arrange
        String toolCode = "CHNS";
        int rentalDayCount = 5;
        int discountPercent = 25;
        String checkoutDate = "07/02/15";

        //Act
        RentalAgreement rentalAgreement = ToolService.checkoutTool(toolCode, rentalDayCount, discountPercent, checkoutDate);

        //Assert
        Assert.assertTrue("Tool code does not equal CHNS (Chainsaw)", rentalAgreement.getToolCode().equals("CHNS"));
        Assert.assertTrue("Tool Brand does not equal 'Stihl'", rentalAgreement.getToolBrand().equals("Stihl"));
        Assert.assertTrue("Checkout date does not equal '7/02/15'", rentalAgreement.getCheckoutDate().equals("07/02/15"));
        Assert.assertTrue("Rentals days does not equal 5", rentalAgreement.getRentalDays() == 5);
        Assert.assertTrue("Discount percent days does not equal 25", rentalAgreement.getDiscountPercent() == 25);
        Assert.assertTrue("Due date is not the correct date: '07/07/15'", rentalAgreement.getDueDate().equals("07/07/15"));
        Assert.assertTrue("Chainsaw daily charge does not equal 1.49", rentalAgreement.getDailyRentalCharge() == 1.49f);
        Assert.assertTrue("Charge days does not equal 3", rentalAgreement.getChargeDays() == 3);
        Assert.assertTrue("Pre-discount charge does not equal 4.47", rentalAgreement.getPreDiscountCharge() == 4.47f);
        Assert.assertTrue("Discount amount charge does not equal 1.12", rentalAgreement.getDiscountAmount() == 1.12f);
        Assert.assertTrue("Final charge does not equal 3.35", rentalAgreement.getFinalAmount() == 3.35f);
    }

    @Test
    public void testCaseFour() throws Exception {
        //Arrange
        String toolCode = "JAKD";
        int rentalDayCount = 6;
        int discountPercent = 0;
        String checkoutDate = "9/3/15";

        //Act
        RentalAgreement rentalAgreement = ToolService.checkoutTool(toolCode, rentalDayCount, discountPercent, checkoutDate);

        //Assert
        Assert.assertTrue("Tool code does not equal JAKD (Jackhammer)", rentalAgreement.getToolCode().equals("JAKD"));
        Assert.assertTrue("Tool Brand does not equal 'DeWalt'", rentalAgreement.getToolBrand().equals("DeWalt"));
        Assert.assertTrue("Checkout date does not equal '9/3/15'", rentalAgreement.getCheckoutDate().equals("9/3/15"));
        Assert.assertTrue("Rentals days does not equal 6", rentalAgreement.getRentalDays() == 6);
        Assert.assertTrue("Discount percent days does not equal 0", rentalAgreement.getDiscountPercent() == 0);
        Assert.assertTrue("Due date is not the correct date: '09/09/15'", rentalAgreement.getDueDate().equals("09/09/15"));
        Assert.assertTrue("Jackhammer daily charge does not equal 2.99", rentalAgreement.getDailyRentalCharge() == 2.99f);
        Assert.assertTrue("Charge days does not equal 3", rentalAgreement.getChargeDays() == 3);
        Assert.assertTrue("Pre-discount charge does not equal 8.97", rentalAgreement.getPreDiscountCharge() == 8.97f);
        Assert.assertTrue("Discount amount charge does not equal 0", rentalAgreement.getDiscountAmount() == 0);
        Assert.assertTrue("Final charge does not equal 8.97", rentalAgreement.getFinalAmount() == 8.97f);

    }

    @Test
    public void testCaseFive() throws Exception {
        //Arrange
        String toolCode = "JAKR";
        int rentalDayCount = 9;
        int discountPercent = 0;
        String checkoutDate = "7/2/15";

        //Act
        RentalAgreement rentalAgreement = ToolService.checkoutTool(toolCode, rentalDayCount, discountPercent, checkoutDate);

        //Assert
        Assert.assertTrue("Tool code does not equal JAKR (Jackhammer)", rentalAgreement.getToolCode().equals("JAKR"));
        Assert.assertTrue("Tool Brand does not equal 'Rigid'", rentalAgreement.getToolBrand().equals("Rigid"));
        Assert.assertTrue("Checkout date does not equal '7/2/15'", rentalAgreement.getCheckoutDate().equals("7/2/15"));
        Assert.assertTrue("Rentals days does not equal 9", rentalAgreement.getRentalDays() == 9);
        Assert.assertTrue("Discount percent days does not equal 0", rentalAgreement.getDiscountPercent() == 0);
        Assert.assertTrue("Due date is not the correct date: '07/11/15'", rentalAgreement.getDueDate().equals("07/11/15"));
        Assert.assertTrue("Jackhammer daily charge does not equal 2.99", rentalAgreement.getDailyRentalCharge() == 2.99f);
        Assert.assertTrue("Charge days does not equal 5", rentalAgreement.getChargeDays() == 5);
        Assert.assertTrue("Pre-discount charge does not equal 14.95", rentalAgreement.getPreDiscountCharge() == 14.95f);
        Assert.assertTrue("Discount amount charge does not equal 0", rentalAgreement.getDiscountAmount() == 0);
        Assert.assertTrue("Final charge does not equal 14.95", rentalAgreement.getFinalAmount() == 14.95f);
    }


    @Test
    public void testCaseSix() throws Exception {
        //Arrange
        String toolCode = "JAKR";
        int rentalDayCount = 4;
        int discountPercent = 50;
        String checkoutDate = "7/2/20";

        //Act
        RentalAgreement rentalAgreement = ToolService.checkoutTool(toolCode, rentalDayCount, discountPercent, checkoutDate);

        //Assert
        Assert.assertTrue("Tool code does not equal JAKR (Jackhammer)", rentalAgreement.getToolCode().equals("JAKR"));
        Assert.assertTrue("Tool Brand does not equal 'Rigid'", rentalAgreement.getToolBrand().equals("Rigid"));
        Assert.assertTrue("Checkout date does not equal '7/2/20'", rentalAgreement.getCheckoutDate().equals("7/2/20"));
        Assert.assertTrue("Rentals days does not equal 4", rentalAgreement.getRentalDays() == 4);
        Assert.assertTrue("Discount percent does not equal 50", rentalAgreement.getDiscountPercent() == 50);
        Assert.assertTrue("Due date is not the correct date: '07/06/20'", rentalAgreement.getDueDate().equals("07/06/20"));
        Assert.assertTrue("Jackhammer daily charge does not equal 2.99", rentalAgreement.getDailyRentalCharge() == 2.99f);
        Assert.assertTrue("Charge days does not equal 1", rentalAgreement.getChargeDays() == 1);
        Assert.assertTrue("Pre-discount charge does not equal 2.99", rentalAgreement.getPreDiscountCharge() == 2.99f);
        Assert.assertTrue("Discount amount charge does not equal 1.50", rentalAgreement.getDiscountAmount() == 1.50);
        Assert.assertTrue("Final charge does not equal 1.50", rentalAgreement.getFinalAmount() == 1.50f);
    }

}
