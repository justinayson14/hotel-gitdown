package com.example.hotelproject2;

import com.example.hotelproject2.models.Booking;
import com.example.hotelproject2.models.Payment;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

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
     * Sets character limits for all TextFields on PaymentScene
     */
    public void initialize() {
        setCharLimit(nameText, 50);
        setCharLimit(addressText, 50);
        setCharLimit(zipText,5);
        setCharLimit(phoneText, 10);
        setCharLimit(cardNumText,16);
        setCharLimit(monthExpText,2);
        setCharLimit(yearExpText,4);
        setCharLimit(cvcText,3);
    }
    /**
     * Creates a max character limit for a given TextField
     * @param textField
     * @param maxChar
     */
    private void setCharLimit(TextField textField, int maxChar) {
        textField.textProperty().addListener((_, _, newValue) -> {
            if (newValue.length() > maxChar) {
                textField.setText(newValue.substring(0, maxChar));
            }
        });
    }
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

    private boolean checkIfFilled(TextField field) {
        if(field.getText().isBlank()) {
            field.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            return false;
        } else {
            field.setStyle(null);
            return true;
        }
    }

    @FXML
    private void handleConfirmationButton(ActionEvent event) throws IOException {
        boolean isAllFilled = checkIfFilled(nameText) && checkIfFilled(addressText) &&
                checkIfFilled(zipText) && checkIfFilled(phoneText) && checkIfFilled(cardNumText) &&
                checkIfFilled(monthExpText) && checkIfFilled(yearExpText) && checkIfFilled(cvcText);
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
