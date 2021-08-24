package models;

public class Checkout {
    private final String toolCode;
    private final int rentalDayCount;
    private final int discountPercent;
    private final String checkoutDate;

    public Checkout(String toolCode, int rentalDayCount, int discountPercent, String checkoutDate) {
        this.toolCode = toolCode;
        this.rentalDayCount = rentalDayCount;
        this.discountPercent = discountPercent;
        this.checkoutDate = checkoutDate;
    }
}
