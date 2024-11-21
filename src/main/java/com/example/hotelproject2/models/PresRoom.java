package com.example.hotelproject2.models;

import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.UUID;

public class PresRoom implements Room{
    @BsonProperty(value = "_id")
    private String id = UUID.randomUUID().toString();
    private String roomType = "PresRoom";
    private int roomNum;
    private String desc = "The epitome of luxury, this spacious suite boasts two separate rooms, each with its own bed and bathroom, " +
            "\nproviding the ultimate indulgence for families or groups.";
    private double price = 5000.00;
    private int numBeds = 2;
    private int numBaths = 2;
    private boolean isOccupied;

    public PresRoom () {};
    public PresRoom (int roomNum) {
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
        return "PresRoom{" +
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
