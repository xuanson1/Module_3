package com.example.case_module_3.entity;

public class PaymentType {
    private int paymentTypeId;
    private String paymentTypeName;

    // Constructor with both ID and name
    public PaymentType(int paymentTypeId, String paymentTypeName) {
        this.paymentTypeId = paymentTypeId;
        this.paymentTypeName = paymentTypeName;
    }

    // Constructor with name only
    public PaymentType(String paymentTypeName) {
        this.paymentTypeName = paymentTypeName;
    }

    // Getters and setters
    public int getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(int paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public String getPaymentTypeName() {
        return paymentTypeName;
    }

    public void setPaymentTypeName(String paymentTypeName) {
        this.paymentTypeName = paymentTypeName;
    }
}
