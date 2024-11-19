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
    public void displayName(String name){
        insertNameLabel.setText(name);
    }

    public void displayNumGuests(String numGuests){
        insertNumGuestsLabel.setText(numGuests);
    }

    public void displayRoomType(String roomType){
        insertRoomTypeLabel.setText(roomType);
    }

    public void displayNumDays(String numDays){
        insertNumDaysLabel.setText(numDays);
    }

    public void displayPrice(String price){
        insertPriceLabel.setText(price);
    }
//switch to HomeScene
    public void switchToScene1(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("HomeScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
//switch to ThankYouScene
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
