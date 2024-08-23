package com.example.final_module_3.entity;

public class PaymentType {
    private int paymentTypeId;
    private String paymentTypeName;

    public PaymentType() {}

    public PaymentType(String paymentTypeName) {
        this.paymentTypeName = paymentTypeName;
    }

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

    @Override
    public String toString() {
        return "PaymentType{" +
                "paymentTypeId=" + paymentTypeId +
                ", paymentTypeName='" + paymentTypeName + '\'' +
                '}';
    }
}

