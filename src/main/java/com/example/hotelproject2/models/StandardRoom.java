package com.example.hotelproject2.models;

import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.UUID;

/**
 * Holds the details of a Standard room
 */
public class StandardRoom implements Room{
    @BsonProperty(value = "_id")
    private String id = UUID.randomUUID().toString();
    private int roomNum;
    private boolean isOccupied = false;

    public StandardRoom() {};

    public StandardRoom(int roomNum) {
        this.roomNum = roomNum;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }

    public void setRoomNum(int roomNum) {
        this.roomNum = roomNum;
    }
    public int getRoomNum() {
        return roomNum;
    }

    public void setOccupied(boolean occupied) {
        this.isOccupied = occupied;
    }
    public boolean getOccupied() {
        return isOccupied;
    }

    public static String getDesc() {
        return "A cozy and comfortable room perfect for solo travelers or couples," +
                "featuring a single bed and a private bathroom.";
    }

    public static int getNumBeds() {
        return 1;
    }

    public static int getNumBaths() {
        return 1;
    }

    public static double getPrice() {
        return 1500.00;
    }
}
