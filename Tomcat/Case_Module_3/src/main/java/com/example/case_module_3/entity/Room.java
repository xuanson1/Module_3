package com.example.case_module_3.entity;

import java.time.LocalDate;

public class Room {
    private int roomId;
    private String tenantName;
    private String phoneNumber;
    private LocalDate starDate;
    private int paymentTypeId;
    private String notes;
    private PaymentType paymentType;


    public Room(String tenantName, String phoneNumber, LocalDate starDate, int paymentTypeId, String notes, PaymentType paymentType) {
        this.tenantName = tenantName;
        this.phoneNumber = phoneNumber;
        this.starDate = starDate;
        this.paymentTypeId = paymentTypeId;
        this.notes = notes;
        this.paymentType = paymentType;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getStarDate() {
        return starDate;
    }

    public void setStarDate(LocalDate starDate) {
        this.starDate = starDate;
    }

    public int getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(int paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }
}
