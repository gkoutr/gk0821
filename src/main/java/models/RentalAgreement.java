package models;

public class RentalAgreement {
    private String toolCode;
    private String toolBrand;
    private int rentalDays;
    private String checkoutDate;
    private String dueDate;
    private float dailyRentalCharge;
    private int chargeDays;
    private float preDiscountCharge;
    private int discountPercent;
    private float discountAmount;

    public RentalAgreement() {

    }

    public RentalAgreement(String toolCode, String toolBrand, int rentalDays, String checkoutDate, String dueDate, float dailyRentalCharge, int chargeDays, float preDiscountCharge, int discountPercent, float discountAmount, float finalCharge) {
        this.toolCode = toolCode;
        this.toolBrand = toolBrand;
        this.rentalDays = rentalDays;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
        this.dailyRentalCharge = dailyRentalCharge;
        this.chargeDays = chargeDays;
        this.preDiscountCharge = preDiscountCharge;
        this.discountPercent = discountPercent;
        this.discountAmount = discountAmount;
        this.finalCharge = finalCharge;
    }

    private float finalCharge;


}
