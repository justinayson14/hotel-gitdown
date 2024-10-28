package com.example.hotelproject2.models;

import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.Date;
import java.util.UUID;

public class Booking {
    @BsonProperty(value = "_id")
    private String id;
    private String customerId;
    private Payment payment;
    private String roomId;
    private String checkInDate;
    private String checkOutDate;
    private double totalCost;

    public Booking () {}
    public Booking (String customerId, Payment payment, String roomId, String checkInDate, String checkOutDate, double totalCost) {
        this.id = UUID.randomUUID().toString();
        this.customerId = customerId;
        this.payment = payment;
        this.roomId = roomId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalCost = totalCost;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
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

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
    public String getRoomId() {
        return roomId;
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

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
    public double getTotalCost() {
        return totalCost;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id='" + id + '\'' +
                ", customerId='" + customerId + '\'' +
                ", payment=" + payment +
                ", roomId='" + roomId + '\'' +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", totalCost=" + totalCost +
                '}';
    }
}
