/**
 * BEING REMOVED
 */

package com.example.hotelproject2;

import com.example.hotelproject2.models.DeluxeRoom;
import com.example.hotelproject2.models.PresRoom;
import com.example.hotelproject2.models.Room;
import com.example.hotelproject2.models.StandardRoom;
import javafx.scene.control.TextField;

/**
 * Holds the methods used for hotel management such as room creation and room deletion
 */
public class HotelController {
    /**
     * Creates room in hotel and pushes object to database in collection of its room type
     * @param roomType Type of room to create
     * @param roomNum The room number of room being created
     */
    public Room createRoom (String roomType, int roomNum) {
        Room room;

        if(roomType.equals("Deluxe")) {
            System.out.println("Created Deluxe room");
            room = new DeluxeRoom();
        }
        else if(roomType.equals("Presidential")) {
            System.out.println("Created Presidential room");
            room = new PresRoom();
        }
        else if(roomType.equals("Standard")) {
            System.out.println("Created Standard room");
            room = new StandardRoom();
        }
        else {
            System.out.println("Invalid input!");
            return null;
        }

        room.setRoomNum(roomNum);
        return room;
    }

    /**
     * Recommend room to pick based on size of group
     * @param groupSize Size of customer's party
     * @return String that states the recommended room type
     */
    public String recRoomBySize(int groupSize) {
        if(groupSize <= 2 && groupSize > 0)
            return "The recommended room is a Standard Room";
        else if(groupSize <= 4 && groupSize > 2)
            return "The recommended room is a Deluxe Room";
        else if(groupSize <= 6 && groupSize > 4)
            return "The recommended room is a Presidential Room";
        else
            return "Please separate your group into separate rooms";
    }

    /**
     * Checks if the TextField is not blank and if blank, highlights it red.
     * @param field The TextField being validated
     * @return Boolean that is false if field is blank, else true
     */
    public boolean validateField(TextField field) {
        if(field.getText().isBlank()) {
            field.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            return false;
        } else {
            field.setStyle(null);
            return true;
        }
    }
}
