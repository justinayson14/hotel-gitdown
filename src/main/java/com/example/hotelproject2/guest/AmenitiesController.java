package com.example.hotelproject2.guest;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller for handling switching back to home scene
 * from amenities scene
 */
public class AmenitiesController {
    /**
     * Switches to HomeScene when "Home" button is clicked
     * @param event Action listener for pressing button
     * @throws IOException Failure to locate
     */
    public void switchToHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("GuestHomeScene.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



}
