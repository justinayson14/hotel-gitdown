package com.example.hotelproject2;

import com.example.hotelproject2.models.DeluxeRoom;
import com.example.hotelproject2.models.PresRoom;
import com.example.hotelproject2.models.Room;
import com.example.hotelproject2.models.StandardRoom;

/**
 * Holds the methods used for hotel management such as room creation and room deletion
 */
public class HotelController {
    /**
     * Creates room in hotel and pushes object to database in collection of its room type
     * @param roomType Type of room to create
     * @param roomNum The room number of room being created
     */
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
