package com.example.hotelproject2.models;

import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.UUID;

public class DeluxeRoom implements Room{
    @BsonProperty(value = "_id")
    private String id;
    private String roomType;
    private int roomNum;
    private String desc;
    private double price;
    private int numBeds;
    private int numBaths;
    private boolean isOccupied;

    public DeluxeRoom () {};
    public DeluxeRoom (int roomNum) {
        this.id = UUID.randomUUID().toString();
        this.roomType = "Deluxe";
        this.desc = "Designed for those seeking extra space and comfort, the Deluxe Room offers two beds and a private bathroom, ideal for couples, friends, or small families.";
        this.price = 3500.00;
        this.numBeds = 2;
        this.numBaths = 1;
        this.isOccupied = false;
        this.roomNum = roomNum;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }
    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }
    @Override
    public String getRoomType() {
        return roomType;
    }

    @Override
    public void setRoomNum(int roomNum) {
        this.roomNum = roomNum;
    }
    @Override
    public int getRoomNum() {
        return roomNum;
    }

    @Override
    public void setDesc(String desc) {
        this.desc = desc;
    }
    @Override
    public String getDesc() {
        return desc;
    }

    @Override
    public void setNumBaths(int numBaths) {
        this.numBaths = numBaths;
    }
    @Override
    public int getNumBaths() {
        return numBaths;
    }

    @Override
    public void setNumBeds(int numBeds) {
        this.numBeds = numBeds;
    }
    @Override
    public int getNumBeds() {
        return numBeds;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }
    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setOccupied(boolean occupied) {
        this.isOccupied = occupied;
    }
    @Override
    public boolean getOccupied() {
        return isOccupied;
    }

    @Override
    public String toString() {
        return "DeluxeRoom{" +
                "id='" + id + '\'' +
                ", roomType='" + roomType + '\'' +
                ", roomNum=" + roomNum +
                ", desc='" + desc + '\'' +
                ", price=" + price +
                ", numBeds=" + numBeds +
                ", numBaths=" + numBaths +
                ", isOccupied=" + isOccupied +
                '}';
    }
}