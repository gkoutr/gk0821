package models;

public class Checkout {
    private String toolCode;
    private int rentalDayCount;
    private int discountPercent;
    private String checkoutDate;

    public Checkout(String toolCode, int rentalDayCount, int discountPercent, String checkoutDate) {
        this.toolCode = toolCode;
        this.rentalDayCount = rentalDayCount;
        this.discountPercent = discountPercent;
        this.checkoutDate = checkoutDate;
    }
}
