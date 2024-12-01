package com.example.hotelproject2;

import com.example.hotelproject2.models.Booking;
import com.example.hotelproject2.models.Payment;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

import com.example.hotelproject2.models.Customers;

public class PaymentSceneController {
    @FXML
    private TextField nameText;
    @FXML
    private TextField addressText;
    @FXML
    private TextField zipText;
    @FXML
    private TextField phoneText;
    @FXML
    private TextField cardNumText;
    @FXML
    private TextField monthExpText;
    @FXML
    private TextField yearExpText;
    @FXML
    private TextField cvcText;

    private Customers customer;
    private Booking booking;

    /**
     * This method is used to pass the customer data
     * from another controller to this controller for later use.
     * @param customer Customer object from previous scene
     * @param booking Booking object from previous scene
     */
    public void initData(Customers customer, Booking booking) {
        this.customer = customer;
        this.booking = booking;
    }

    @FXML
    private void handleConfirmationButton(ActionEvent event) throws IOException {
        ReservationController r = new ReservationController();
        boolean isAllFilled = r.validateFields(nameText) && r.validateFields(addressText) &&
                r.validateFields(zipText) && r.validateFields(phoneText) && r.validateFields(cardNumText) &&
                r.validateFields(monthExpText) && r.validateFields(yearExpText) && r.validateFields(cvcText);
        if(isAllFilled) {
            switchToConfirmation(event);
        }
    }

    private void switchToConfirmation(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ConfirmScene.fxml"));
        Parent root = loader.load();

        // creates an instance of the scene's controller to pass customer data to
        ConfirmSceneController controller = loader.getController();

        // prints the customer data to console and passes it along
        System.out.println("\n---\nPassing along the following customer data: " + customer + "\n---");
        Payment payment = new Payment();
        payment.setName(nameText.getText());
        payment.setCardNum(cardNumText.getText());
        payment.setCardExp(monthExpText.getText()+yearExpText.getText());
        payment.setCardCVC(cvcText.getText());
        payment.setPhoneNum(phoneText.getText());
        booking.setPayment(payment);
        controller.initData(customer, booking); // passes it along
        controller.displayInfo();

        // switch scene
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void switchToRoomDetails(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RoomDetailsScene.fxml"));
        Parent root = loader.load();

        // creates an instance of the scene's controller to pass customer data to
        RoomDetailsController controller = loader.getController();

        // prints the customer data to console and passes it along
        System.out.println("\n---\nPassing along the following customer data: " + customer + "\n---"); // prints to console
        controller.getCustomer(customer); // passes it along

        // switch scene
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
