package com.example.hotelproject2.models;

import org.bson.codecs.pojo.annotations.BsonProperty;

public class Room {
    @BsonProperty
    private String id;
    private String roomType;
    private int roomNum;
    private String desc;
    private double price;

    public Room() {}

    public Room (String id, String roomType, int roomNum, String desc, double price) {
        this.id = id;
        this.roomType = roomType;
        this.roomNum = roomNum;
        this.desc = desc;
        this.price = price;
    }

    public void setId (String id) { this.id = id; }
    public String getId () { return id; }

    public void setRoomType (String roomType) { this.roomType = roomType; }
    public String getRoomType () {return roomType; }

    public void setRoomNum (int roomNum) { this.roomNum = roomNum; }
    public int getRoomNum () { return roomNum; }

    public void setDesc (String desc) { this.desc = desc; }
    public String getDesc () { return desc; }

    public void setPrice (double price) { this.price = price; }
    public double getPrice () { return price; }
}
