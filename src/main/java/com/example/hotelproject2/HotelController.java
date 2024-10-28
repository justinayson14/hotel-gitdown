package com.example.hotelproject2;

import com.example.hotelproject2.models.DeluxeRoom;
import com.example.hotelproject2.models.PresRoom;
import com.example.hotelproject2.models.Room;
import com.example.hotelproject2.models.StandardRoom;

public class HotelController {

    public void createRoom (String roomType, int roomNum) {
        Room room;
        switch (roomType) {
            case "Standard":
                room = new StandardRoom(roomNum);
                break;
            case "Deluxe":
                room = new DeluxeRoom(roomNum);
                break;
            case "Presidential":
                room = new PresRoom(roomNum);
                break;
            default:
                room = null;
                System.out.println("INVALID Room type!!!");
                System.exit(1);
        }

        // insert new room to database
        MongoOps.insertSingle(room);
    }


}
