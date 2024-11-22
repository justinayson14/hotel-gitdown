package com.example.hotelproject2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import java.io.IOException;

public class UserCheckInDetailsSceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML private Label insertNameLabel;
    @FXML private Label insertNumGuestsLabel;
    @FXML private Label insertRoomTypeLabel;
    @FXML private Label insertNumDaysLabel;
    @FXML private Label insertPriceLabel;

    //displays given String to corresponding Label

    /**
     * Displays name inputted from CustomerScene onto UserCheckInDetailsScene
     * @param name
     */
    public void displayName(String name){
        insertNameLabel.setText(name);
    }

    /**
     * Displays number of guests inputted from CustomerScene onto UserCheckInDetailsScene
     * @param numGuests
     */
    public void displayNumGuests(String numGuests){
        insertNumGuestsLabel.setText(numGuests);
    }

    /**
     * Displays room type inputted from CustomerScene onto UserCheckInDetailsScene
     * @param roomType
     */
    public void displayRoomType(String roomType){
        insertRoomTypeLabel.setText(roomType);
    }

    /**
     * Displays number of days reserved from CustomerScene to UserCheckInDetailsScene
     * @param numDays
     */
    public void displayNumDays(String numDays){
        insertNumDaysLabel.setText(numDays);
    }

    /**
     * Displays price of reservation from CustomerScene to UserCheckInDetailsScene
     * @param price
     */
    public void displayPrice(String price){
        insertPriceLabel.setText(price);
    }

    /**
     * Switches to HomeScene when "Cancel" button is clicked
     * @param event
     * @throws IOException
     */
    public void switchToScene1(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("HomeScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Switches to ThankYouScene when "Confirm Booking" button is pressed
     * @param event
     * @throws IOException
     */
    public void switchToThankYou(ActionEvent event) throws IOException {
        // add the customer data to customer database - DOES NOT WORK AT THE MOMENT
        //CheckInController controller = new CheckInController();
        //MongoOps.insertSingle(controller.customer);

        // switch to ThankYouScene
        Parent root = FXMLLoader.load(getClass().getResource("ThankYouScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
