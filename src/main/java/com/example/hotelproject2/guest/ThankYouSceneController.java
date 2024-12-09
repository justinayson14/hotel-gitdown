package com.example.hotelproject2.guest;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller for showing customer their room number
 * for their booked room and pushing booking data
 * to database
 */
public class ThankYouSceneController {
    @FXML private Text roomNumText;

    private Stage stage;
    private Scene scene;
    private Parent root;

    /**
     * Set room num to be displayed in this scene
     * @param roomNum Room number passed to be displayed
     */
    public void setRoomNum(int roomNum) {
        roomNumText.setText(Integer.toString(roomNum));
    }

    /**
     * Switches to HomeScene when "Home" button is clicked
     * @param event Action listener for pressing button
     * @throws IOException Failure to locate fxml file
     */
    public void switchToHome(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("GuestHomeScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Switches to ViewAmenitiesScene when "View Amenities" button is clicked
     * @param event Action listener for pressing button
     * @throws IOException Failure to locate fxml file
     */
    public void switchToAmenities(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("ViewAmenitiesScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
