package com.example.hotelproject2.models;

import java.util.*;

public class Hotel {
    private final String name;
    ArrayList <Room> roomList = new ArrayList<>();
    //list bookings
    private final String address;
    private int totalRooms;

    public Hotel (String name, String address, int totalRooms) {
        this.name = name;
        this.totalRooms = totalRooms;
        this.address = address;
    }

    public void setTotalRooms (int totalRooms) { this.totalRooms = totalRooms; }
    public int getTotalRooms () { return totalRooms; }

    public String getName () { return name; }

    public String getAddress () { return address; }
}