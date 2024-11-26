package com.example.hotelproject2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class CheckOutController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public TextField firstName;
    @FXML
    public TextField secondName;



    /**
     * Switches to HomeScene when "Back" button is clicked
     * @param event
     * @throws IOException
     */
    public void switchToHomeScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("HomeScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Switches to HomeScene when "Back" button is clicked
     * @param event
     * @throws IOException
     */
    public void switchToCheckOutConfirmation(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("CheckOutConfirmationScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
