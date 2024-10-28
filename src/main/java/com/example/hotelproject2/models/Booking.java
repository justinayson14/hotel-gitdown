package com.example.hotelproject2.models;

import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.UUID;

public class Booking {
    @BsonProperty(value = "_id")
    private String id;
    private String customerId;
    private Payment payment;
    private String roomId;
    private int lengthBooked;
    private double totalCost;

    public Booking () {}
    public Booking (String customerId, Payment payment, String roomId, int lengthBooked, double totalCost) {
        this.id = UUID.randomUUID().toString();
        this.customerId = customerId;
        this.payment = payment;
        this.roomId = roomId;
        this.lengthBooked = lengthBooked;
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

    public void setLengthBooked(int lengthBooked) {
        this.lengthBooked = lengthBooked;
    }
    public int getLengthBooked() {
        return lengthBooked;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
    public double getTotalCost() {
        return totalCost;
    }
}
