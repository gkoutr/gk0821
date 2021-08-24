package models;

public class RentalAgreement {
    private final String toolCode;
    private final String toolBrand;
    private final int rentalDays;
    private final String checkoutDate;
    private final String dueDate;
    private final float dailyRentalCharge;
    private final int chargeDays;
    private final float preDiscountCharge;
    private final int discountPercent;
    private final float discountAmount;
    private final float finalAmount;


    private RentalAgreement(Builder builder){
        this.toolCode = builder.toolCode;
        this.toolBrand = builder.toolBrand;
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

    public void printRentalAgreement(){

    }

    public static class Builder {
        private final String toolCode;
        private final int rentalDays;
        private final int discountPercent;
        private final String checkoutDate;
        private String toolBrand;
        private String dueDate;
        private float dailyRentalCharge;
        private int chargeDays;
        private float preDiscountCharge;
        private float discountAmount;
        private float finalAmount;

        public Builder(String toolCode, int rentalDays, int discountPercent, String checkoutDate){
            this.toolCode = toolCode;
            this.rentalDays = rentalDays;
            this.discountPercent = discountPercent;
            this.checkoutDate = checkoutDate;
        }

        public Builder setToolBrand(String toolBrand) {
            this.toolBrand = toolBrand;
            return this;
        }

        public Builder setDueDate(String dueDate){
            this.dueDate = dueDate;
            return this;
        }

        public Builder setDailyRentalCharge(float dailyRentalCharge){
            this.dailyRentalCharge = dailyRentalCharge;
            return this;
        }

        public Builder setChargeDays(int chargeDays) {
            this.chargeDays = chargeDays;
            return this;
        }

        public Builder setPreDiscountCharge(float preDiscountCharge) {
            this.preDiscountCharge = preDiscountCharge;
            return this;
        }

        public Builder setDiscountAmount(float discountAmount) {
            this.discountAmount = discountAmount;
            return this;
        }

        public Builder setFinalAmount(float finalAmount) {
            this.finalAmount = finalAmount;
            return this;
        }

        public RentalAgreement build(){
            return new RentalAgreement(this);
        }
    }

}
