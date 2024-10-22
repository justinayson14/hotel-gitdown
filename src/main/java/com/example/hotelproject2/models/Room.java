package com.example.hotelproject2.models;

public class Room {
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
    

}
