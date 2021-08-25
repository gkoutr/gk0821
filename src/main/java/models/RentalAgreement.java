package models;

import utils.CurrencyUtils;

/**
 * RentalAgreement model that uses the Builder design pattern to construct an immutable object.
 */
public class RentalAgreement {
    private final String toolCode;
    private final String toolBrand;
    private final String toolType;
    private final int rentalDays;
    private final String checkoutDate;
    private final String dueDate;
    private final float dailyRentalCharge;
    private final int chargeDays;
    private final float preDiscountCharge;
    private final int discountPercent;
    private final float discountAmount;
    private final float finalAmount;

    private RentalAgreement(Builder builder) {
        this.toolCode = builder.toolCode;
        this.toolBrand = builder.toolBrand;
        this.toolType = builder.toolType;
        this.rentalDays = builder.rentalDays;
        this.checkoutDate = builder.checkoutDate;
        this.dueDate = builder.dueDate;
        this.dailyRentalCharge = builder.dailyRentalCharge;
        this.chargeDays = builder.chargeDays;
        this.preDiscountCharge = builder.preDiscountCharge;
        this.discountPercent = builder.discountPercent;
        this.discountAmount = builder.discountAmount;
        this.finalAmount = builder.finalAmount;
    }

    public String getToolCode() {
        return toolCode;
    }

    public String getToolBrand() {
        return toolBrand;
    }

    public String getToolType() {
        return toolType;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public String getCheckoutDate() {
        return checkoutDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public float getDailyRentalCharge() {
        return dailyRentalCharge;
    }

    public int getChargeDays() {
        return chargeDays;
    }

    public float getPreDiscountCharge() {
        return preDiscountCharge;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public float getDiscountAmount() {
        return discountAmount;
    }

    public float getFinalAmount() {
        return finalAmount;
    }

    /**
     * Prints the rental agree to the console.
     */
    public void printRentalAgreement() {
        System.out.println("----Rental Agreement----");
        System.out.println("Tool code: " + toolCode);
        System.out.println("Tool type: " + toolType);
        System.out.println("Tool brand: " + toolBrand);
        System.out.println("Rental days: " + rentalDays);
        System.out.println("Check out date: " + checkoutDate);
        System.out.println("Due date: " + dueDate);
        System.out.println("Daily rental charge: " + dailyRentalCharge);
        System.out.println("Charge days: " + chargeDays);
        System.out.println("Pre-discount charge: " + preDiscountCharge);
        System.out.println("Discount percent: " + discountPercent + "%");
        System.out.println("Discount amount: " + discountAmount);
        System.out.println("Final charge: " + finalAmount);
    }

    public static class Builder {
        private final String toolCode;
        private final String checkoutDate;
        private int rentalDays;
        private int discountPercent;
        private String toolBrand;
        private String toolType;
        private String dueDate;
        private float dailyRentalCharge;
        private int chargeDays;
        private float preDiscountCharge;
        private float discountAmount;
        private float finalAmount;

        public Builder(String toolCode, String checkoutDate) {
            this.toolCode = toolCode;
            this.checkoutDate = checkoutDate;
        }

        public Builder setRentalDays(int rentalDays) throws Exception {
            this.rentalDays = rentalDays;
            return this;
        }

        public Builder setDiscountPercent(int discountPercent) throws Exception {
            if (discountPercent < 0 || discountPercent > 100) {
                throw new Exception("Discount percent must be within 0-100");
            }
            this.discountPercent = discountPercent;
            return this;
        }

        public Builder setToolBrand(String toolBrand) {
            this.toolBrand = toolBrand;
            return this;
        }

        public Builder setToolType(String toolType) {
            this.toolType = toolType;
            return this;
        }

        public Builder setDueDate(String dueDate) {
            this.dueDate = dueDate;
            return this;
        }

        public Builder setDailyRentalCharge(float dailyRentalCharge) {
            this.dailyRentalCharge = dailyRentalCharge;
            return this;
        }

        public Builder setChargeDays(int chargeDays) {
            this.chargeDays = chargeDays;
            return this;
        }

        public Builder setPreDiscountCharge(float preDiscountCharge) {
            this.preDiscountCharge = CurrencyUtils.formatFloat(preDiscountCharge);
            return this;
        }

        public Builder setDiscountAmount(float discountAmount) {
            this.discountAmount = CurrencyUtils.formatFloat(discountAmount);
            return this;
        }

        public Builder setFinalAmount(float finalAmount) {
            this.finalAmount = CurrencyUtils.formatFloat(finalAmount);
            return this;
        }

        public RentalAgreement build() {
            return new RentalAgreement(this);
        }
    }

}
