package com.example.hotelproject2;

import com.example.hotelproject2.models.Booking;
import com.example.hotelproject2.models.Customers;
import com.example.hotelproject2.models.Payment;
import com.example.hotelproject2.models.Room;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ReservationController {

    private double calculateTotalCost (String checkIn, String checkOut, double cost) {
        LocalDate start = LocalDate.parse(checkIn);
        LocalDate end = LocalDate.parse(checkOut);
        long diffInDays = ChronoUnit.DAYS.between(start, end);
        return diffInDays * cost;
    }

    private Payment createPayment (String name, String cardNum, String cardCVC, String cardExp, String phoneNum) {
        Payment pay = new Payment();
        pay.setName(name);
        pay.setCardNum(cardNum);
        pay.setCardExp(cardExp);
        pay.setCardCVC(cardCVC);
        pay.setPhoneNum(phoneNum);
        return pay;
    }

    public void createReservation(Customers customer, String checkIn, String checkOut, String roomType, String name, String cardNum, String cardCVC, String cardExp, String phoneNum) {
        Booking reservation = new Booking();
        Room room = MongoOps.queryAvailRoomByType(roomType);
        reservation.setCustomerId(customer.getId());
        reservation.setRoomId(room.getId());
        reservation.setTotalCost(calculateTotalCost(checkIn, checkOut, room.getPrice()));
        reservation.setPayment(createPayment(name, cardNum, cardCVC, cardExp, phoneNum));
        room.setOccupied(true);

        MongoOps.insertSingle(reservation);
        MongoOps.updateRoomOccupancy(room);
    }

    public void deleteReservation(Customers customer) {}
}
