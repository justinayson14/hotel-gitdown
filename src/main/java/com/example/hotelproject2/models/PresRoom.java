package com.example.hotelproject2.models;

import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.UUID;

/**
 * Holds the details of a Presidential room
 */
public class PresRoom implements Room{
    @BsonProperty(value = "_id")
    private String id = UUID.randomUUID().toString();
    private int roomNum;
    private boolean isOccupied = false;

    public PresRoom () {};

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
        return "The epitome of luxury, this spacious suite boasts two separate rooms, each with its own bed and bathroom, " +
                "providing the ultimate indulgence for families or groups.";
    }

    public static int getNumBaths() {
        return 2;
    }

    public static int getNumBeds() {
        return 2;
    }

    public static double getPrice() {
        return 5000.00;
    }
}
