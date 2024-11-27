package com.example.hotelproject2.models;

import org.bson.codecs.pojo.annotations.BsonIgnore;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.UUID;

/**
 * Holds the details of a Deluxe room
 */
public class DeluxeRoom implements Room{
    @BsonProperty(value = "_id")
    private String id = UUID.randomUUID().toString();
    private int roomNum;
    private boolean isOccupied = false;

    public DeluxeRoom () {};

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
        return "Designed for those seeking extra space and comfort, " +
                "the Deluxe Room offers two beds and a private bathroom, ideal for couples, friends, or small families.";
    }

    public static int getNumBaths() {
        return 1;
    }

    public static int getNumBeds() {
        return 2;
    }

    public static double getPrice() {
        return 3500.00;
    }
}
