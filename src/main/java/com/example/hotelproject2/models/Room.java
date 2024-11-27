package com.example.hotelproject2.models;

import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.UUID;

/**
 * Outline of a room in Hotel GitDown
 */
public interface Room {
     String getId();
     void setId(String id);

     int getRoomNum();
     void setRoomNum(int roomNum);

     boolean getOccupied();
     void setOccupied(boolean occupied);
}