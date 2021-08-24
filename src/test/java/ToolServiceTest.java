import org.junit.Test;
import service.ToolService;

public class ToolServiceTest {

    @Test
    public void testCheckoutTool() throws Exception {
        String toolCode = "LADW";
        String brand = "WERNER";
        int rentalDayCount = 5;
        int discountPercent = 50;
        String checkoutDate = "08/18/2021";
        ToolService.checkoutTool(toolCode, rentalDayCount, discountPercent, checkoutDate);
    }
}
