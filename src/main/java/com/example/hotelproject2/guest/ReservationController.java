/**
 * BEING REMOVED
 */
package com.example.hotelproject2.guest;

import com.example.hotelproject2.MongoOps;
import com.example.hotelproject2.models.Booking;
import com.example.hotelproject2.models.Room;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Handles methods needed to make a reservation and to end a reservation
 */
public class ReservationController {
    /**
     * Calculates the amount of days between dates and multiplies it by
     * the rate per night cost of the room
     * @param checkIn date of check-in
     * @param checkOut date of check-out
     * @param cost cost of the room type
     * @return Total cost based on the amount of days stayed
     */
    public static double calculateTotalCost (String checkIn, String checkOut, double cost) {
        LocalDate start = LocalDate.parse(checkIn);
        LocalDate end = LocalDate.parse(checkOut);
        long diffInDays = ChronoUnit.DAYS.between(start, end);
        return diffInDays * cost;
    }

    /**
     * Creates reservation by finding available room by room type
     * and inserting the reservation to the database
     * @param booking Object that stores details of booking including room type of choice
     */
    public static void createReservation(Booking booking) {
        Room room = MongoOps.queryAvailRoomByType(booking.getRoomType());
        booking.setRoomId(room.getId());
        room.setOccupied(true);

        MongoOps.insertSingle(booking);
        MongoOps.checkInRoom(room.getClass().getSimpleName(), room.getId());
    }

    /**
     * Finds booking by customer's name to set their room occupancy to false
     * @param customerName Name of customer to use for searching booking
     */
    public static void endReservation(String customerName) {
        String customerId = MongoOps.queryCustomerIdByName(customerName);
        MongoOps.checkOutRoom(customerId);
    }

    /**
     * Checks if the TextField is not blank and if blank, highlights it red.
     * @param field The TextField being validated
     * @return Boolean that is false if field is blank, else true
     */
    public boolean validateFields(TextField field) {
        if(field.getText().isBlank()) {
            field.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            return false;
        } else {
            field.setStyle(null);
            return true;
        }
    }
}