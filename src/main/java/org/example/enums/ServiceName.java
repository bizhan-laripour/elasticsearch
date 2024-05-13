package org.example.enums;

/**
 * @author a.mehdizadeh on 5/6/2024
 */
public enum ServiceName {

    customerDepositList("1", "لیست سپرده های مشتری"),
    getCustomerCurrentDepositList("2", "لیست سپرده های جاری مشتری"),
    customerDepositsAmount("3", "دریافت مانده حساب"),
    getLoanList("4", "لیست تسهیلات"),
    getBailInfo("5", "اطلاعات ضمانت نامه"),
    getStatementByAccountNumber("6", "دریافت صورت حساب بر اساس شماره حساب"),
    billInquiry("7", "استعلام قبض"),
    billPayment("8", "پرداخت قبض با کارت"),
    transferChequeReport("9", "چک های واگذار شده"),
    issuedChequeReport("10", "چک های صادره"),
    getLoanInstallmentList("11", "لیست اقساط تسهیلات"),
    cardStatement("12", "دریافت صورت حساب بر اساس شماره کارت"),
    cardBalance("13", "دریافت موجودی کارت"),
    blockCardWithCardNumber("14", "مسدود کردن کارت");


    private String value;

    private String title;

    // getter method
    public String getValue() {
        return this.value;
    }

    public String getTitle() {
        return title;
    }

    // enum constructor - cannot be public or protected
    private ServiceName(String value, String title) {
        this.value = value;
        this.title = title;
    }
}
