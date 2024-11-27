package com.example.hotelproject2;

import com.example.hotelproject2.models.Booking;
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

public class PaymentMethodSceneController {
    @FXML
    private TextField namePaymentTextField;
    @FXML
    private TextField addressPaymentTextField;
    @FXML
    private TextField zipCodePaymentTextField;
    @FXML
    private TextField phoneNumPaymentTextField;
    @FXML
    private TextField cardNumPaymentTextField;
    @FXML
    private TextField monthCardExpPaymentTextField;
    @FXML
    private TextField yearCardExpPaymentTextField;
    @FXML
    private TextField cardCVCPaymentTextField;

    private Stage stage;
    private Scene scene;
    private Parent root;
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

    public void switchToConfirmation(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ConfirmationScene.fxml"));
        Parent root = loader.load();

        // creates an instance of the scene's controller to pass customer data to
        ConfirmationSceneController controller = loader.getController();

        // prints the customer data to console and passes it along
        System.out.println("\n---\nPassing along the following customer data: " + customer + "\n---"); // prints to console
        controller.getCustomerData(customer); // passes it along

        // switch scene
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToRoomDetails(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RoomDetailsScene.fxml"));
        Parent root = loader.load();

        // creates an instance of the scene's controller to pass customer data to
        RoomDetailsController controller = loader.getController();

        // prints the customer data to console and passes it along
        System.out.println("\n---\nPassing along the following customer data: " + customer + "\n---"); // prints to console
        controller.getCustomer(customer); // passes it along

        // switch scene
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
