package com.example.hotelproject2.guest;

import com.example.hotelproject2.HotelController;
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

import java.io.IOException;

import com.example.hotelproject2.models.Customers;

/**
 * Controller for handling all inputs in Payment scene and
 * passing them to the next scene
 */
public class PaymentSceneController {
    @FXML private TextField nameText;
    @FXML private TextField addressText;
    @FXML private TextField zipText;
    @FXML private TextField phoneText;
    @FXML private TextField cardNumText;
    @FXML private TextField monthExpText;
    @FXML private TextField yearExpText;
    @FXML private TextField cvcText;

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

        setOnlyLetters(nameText);

        setOnlyNumbers(zipText);
        setOnlyNumbers(phoneText);
        setOnlyNumbers(cardNumText);
        setOnlyNumbers(monthExpText);
        setOnlyNumbers(yearExpText);
        setOnlyNumbers(cvcText);
    }

    /**
     * Limits user input for a given TextField to only letter characters
     * @param textfield Field for inputting address and card name
     */
    private void setOnlyLetters(TextField textfield){
        textfield.setTextFormatter(new javafx.scene.control.TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("[a-zA-Z ]*")) { // Allow only letters
                return change;
            }
            return null; // Reject change
        }));
    }

    /**
     * Limits user input for a given TextField to only number characters
     * @param textfield Field for inputting card info and zip code
     */
    private void setOnlyNumbers(TextField textfield){
        textfield.setTextFormatter(new javafx.scene.control.TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*")) { // Allow only numbers
                return change;
            }
            return null; // Reject change
        }));
    }

    /**
     * Creates a max character limit for a given TextField
     * @param textField Fields for inputting payment info
     * @param maxChar Number of characters allowed
     */
    private void setCharLimit(TextField textField, int maxChar) {
        textField.textProperty().addListener((_, _, newValue) -> {
            if(newValue.length() > maxChar) {
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

    /**
     * Checks if User inputted name, address, phone and card info in textfields
     * when "Pay for Room" button is clicked.
     * Does not switch to ConfirmScene.fxml until fields are filled.
     * @param event Action listener for pressing button
     * @throws IOException Failure to load fxml file
     */
    @FXML
    private void handleSwitch(ActionEvent event) throws IOException {
        HotelController r = new HotelController();
        boolean isAllFilled = r.validateField(nameText) && r.validateField(addressText) &&
                r.validateField(zipText) && r.validateField(phoneText) && r.validateField(cardNumText) &&
                r.validateField(monthExpText) && r.validateField(yearExpText) && r.validateField(cvcText);
        if(isAllFilled) {
            switchToConfirmation(event);
        }
    }

    /**
     * Method Switches to ConfirmScene.fxml.
     * Loads ConfirmSceneController object.
     * Passes customer payment info into console.
     * @param event Action listener for pressing button
     * @throws IOException Failure to load fxml file
     */
    private void switchToConfirmation(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ConfirmScene.fxml"));
        Parent root = loader.load();

        ConfirmSceneController controller = loader.getController();
        Payment payment = new Payment();
        payment.setName(nameText.getText());
        payment.setCardNum(cardNumText.getText());
        payment.setCardExp(monthExpText.getText()+yearExpText.getText());
        payment.setCardCVC(cvcText.getText());
        payment.setPhoneNum(phoneText.getText());
        booking.setPayment(payment);
        controller.initData(customer, booking);
        controller.displayInfo();

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Method switches to RoomDetailsScene.fxml when "Change Room" button is pressed
     * @param event Action listener for pressing button
     * @throws IOException Failure to load fxml file
     */
    @FXML
    private void switchToRoomDetails(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RoomDetailsScene.fxml"));
        Parent root = loader.load();

        RoomDetailsController controller = loader.getController();
        controller.getCustomer(customer);

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
