package com.example.hotelproject2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class ThankYouSceneController {
    @FXML
    private Text roomNumText;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void setRoomNum(int roomNum) {
        roomNumText.setText(Integer.toString(roomNum));
    }

    /**
     * Switches to HomeScene when "Home" button is clicked
     * @param event
     * @throws IOException
     */
    public void switchToHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("HomeScene.fxml"));
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
