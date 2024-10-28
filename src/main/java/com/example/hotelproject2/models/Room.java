package com.example.hotelproject2.models;

import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.UUID;

public interface Room {
     String getId();
     void setId(String id);

     String getRoomType();
     void setRoomType(String roomType);

     int getRoomNum();
     void setRoomNum(int roomNum);

     String getDesc();
     void setDesc(String desc);

     int getNumBeds();
     void setNumBeds(int numBeds);

     int getNumBaths();
     void setNumBaths(int numBaths);

     double getPrice();
     void setPrice(double price);

     boolean getOccupied();
     void setOccupied(boolean occupied);
}
