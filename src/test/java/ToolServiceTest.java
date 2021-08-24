import models.RentalAgreement;
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
     *
     * @throws Exception
     */
    @Test
    public void testCaseOne() throws Exception {
        String toolCode = "JAKR";
        String brand = "WERNER";
        int rentalDayCount = 5;
        int discountPercent = 101;
        String checkoutDate = "09/03/2015";
        RentalAgreement rentalAgreement = ToolService.checkoutTool(toolCode, rentalDayCount, discountPercent, checkoutDate);
    }

}
