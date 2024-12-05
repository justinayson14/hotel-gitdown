package com.example.hotelproject2.models;

import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.UUID;

/**
 * Holds details of a customer's booking
 */
public class Booking {
    @BsonProperty(value = "_id")
    private String id = UUID.randomUUID().toString();
    private String customerName;
    private String customerId;
    private int roomNum;
    private String roomId;
    private String roomType;
    private String checkInDate;
    private String checkOutDate;
    private Payment payment;
    private Double totalCost;

    public Booking () {}

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerId() {
        return customerId;
    }
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Payment getPayment() {
        return payment;
    }
    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public int getRoomNum() {
        return roomNum;
    }
    public void setRoomNum(int roomNum) {
        this.roomNum = roomNum;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
    public String getRoomId() { return roomId; }

    public String getRoomType() {
        return roomType;
    }
    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }
    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }
    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }
    public Double getTotalCost() {
        return totalCost;
    }
}
