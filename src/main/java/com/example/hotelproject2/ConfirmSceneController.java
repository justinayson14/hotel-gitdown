package com.example.hotelproject2;

import com.example.hotelproject2.models.Booking;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

import com.example.hotelproject2.models.Customers;

public class ConfirmationSceneController {
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

    public void displayInfo() {
        custNameText.setText(customer.getName());
        roomChoiceText.setText(booking.getRoomType());
        startDateText.setText(booking.getCheckInDate());
        endDateText.setText(booking.getCheckOutDate());
        phoneText.setText(booking.getPayment().getPhoneNum());
        totalText.setText(booking.getTotalCost().toString());
    }

    @FXML
    private void switchToPayment(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PaymentMethodScene.fxml"));
        Parent root = loader.load();

        PaymentMethodSceneController controller = loader.getController();
        // Pass back objects to previous scene
        controller.initData(customer, booking);

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void switchToThankYou(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ThankYouScene.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
        System.out.print("\n---\nRecieved the following customer data");
        System.out.println(": " + customer + "\n---");
    }

}
