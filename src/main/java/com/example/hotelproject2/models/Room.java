package com.example.hotelproject2.models;

import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.UUID;

public class Room {
    @BsonProperty(value = "_id")
    private String id;
    private String roomType;
    private int roomNum;
    private String desc;
    private double price;
    private boolean isOccupied;

    public Room() {}

    public Room (String roomType, int roomNum, String desc, double price, boolean isOccupied) {
        this.id = UUID.randomUUID().toString();
        this.roomType = roomType;
        this.roomNum = roomNum;
        this.desc = desc;
        this.price = price;
        this.isOccupied = isOccupied;
    }

    public String getId () { return id; }

    public void setRoomType (String roomType) { this.roomType = roomType; }
    public String getRoomType () {return roomType; }

    public void setRoomNum (int roomNum) { this.roomNum = roomNum; }
    public int getRoomNum () { return roomNum; }

    public void setDesc (String desc) { this.desc = desc; }
    public String getDesc () { return desc; }

    public void setPrice (double price) { this.price = price; }
    public double getPrice () { return price; }

    public void setOccupied (boolean isOccupied) { this.isOccupied = isOccupied; }
    public boolean getOccupied () { return this.isOccupied; }
}
