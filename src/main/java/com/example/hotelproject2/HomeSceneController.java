package com.example.hotelproject2;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeSceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    /**
     * Switches to CustomerScene when "Check In" button is clicked
     * @param event
     * @throws IOException
     */
    public void switchToCustomer(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("CustomerScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    /**
     * Switches to CheckOutScene.fxml when "Check Out" button is clicked
     * @param event
     * @throws IOException
     */
    public void switchToCheckOut(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("CheckOutScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    /**
     * Switches to ViewAmenitiesScene when "View Amenities" button is clicked
     * @param event
     * @throws IOException
     */
    public void switchToAmenities(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ViewAmenitiesScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
