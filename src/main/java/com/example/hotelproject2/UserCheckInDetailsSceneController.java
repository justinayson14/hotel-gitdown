package com.example.hotelproject2;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;

public class UserCheckInDetailsSceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    Label nameLabel;

    public void displayName(String nameInput){
        nameLabel.setText(nameInput);
    }
}
