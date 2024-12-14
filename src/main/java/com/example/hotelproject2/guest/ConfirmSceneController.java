package com.example.hotelproject2.guest;

import com.example.hotelproject2.MongoOps;
import com.example.hotelproject2.models.Booking;
import com.example.hotelproject2.models.DeluxeRoom;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.DecimalFormat;

import com.example.hotelproject2.models.Customers;

public class ConfirmSceneController {
    @FXML
    private Label custNameText;
    @FXML
    private Label roomChoiceText;
    @FXML
    private Label startDateText;
    @FXML
    private Label endDateText;
    @FXML
    private Label phoneText;
    @FXML
    private Label totalText;

    private Customers customer;
    private Booking booking;

    /**
     * This method is used to pass the customer data, booking data
     * from another controller to this controller for later use.
     * @param customer Customer object from previous scene
     * @param booking Booking object from previous scene
     */
    public void initData(Customers customer, Booking booking) {
        this.customer = customer;
        this.booking = booking;
    }

    /**
     * Method helps collect data from Booking and Customer objects.
     * Sets labels to corresponding user information inputted in program.
     */

    public void displayInfo() {
        DecimalFormat df = new DecimalFormat("0.00");
        String[] name = customer.getName().split("[\\s]");
        name[0] = name[0].substring(0,1).toUpperCase() + name[0].substring(1).toLowerCase();
        name[1] = name[1].substring(0,1).toUpperCase() + name[1].substring(1).toLowerCase();
        custNameText.setText(name[0] + " " + name[1]);
        roomChoiceText.setText(booking.getRoomType());
        startDateText.setText(booking.getCheckInDate());
        endDateText.setText(booking.getCheckOutDate());
        phoneText.setText(booking.getPayment().getPhoneNum());
        totalText.setText(df.format(booking.getTotalCost()));
    }

    /**
     *
     * @param event
     * @throws IOException
     * When "Cancel" Button is pressed, program switches to PaymentScene.fxml
     * Load PaymentSceneController object.
     */

    @FXML
    private void switchToPayment(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PaymentScene.fxml"));
        Parent root = loader.load();

        PaymentSceneController controller = loader.getController();
        // Pass back objects to previous scene
        controller.initData(customer, booking);

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     *
     * @param event
     * @throws IOException
     * When "Confirm Reservation" Button is pressed, program switches to ThankYouScene.fxml.
     * Collects User Inputs and passes it on to the MongoOps Database.
     * Loads ThankYouSceneController object.
     */
    @FXML
    private void switchToThankYou(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ThankYouScene.fxml") );
        Parent root = loader.load();

        MongoOps.checkInRoom(booking.getRoomType(), booking.getRoomId());
        MongoOps.insertSingle(customer);
        MongoOps.insertSingle(booking);
        ThankYouSceneController controller = loader.getController();
        controller.setRoomNum(MongoOps.queryRoomNumById(booking.getRoomId(), booking.getRoomType()));

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.print("\n---\nRecieved the following customer data");
        System.out.println(": " + customer + "\n---");
    }

}
