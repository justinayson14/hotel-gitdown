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

public class PaymentMethodSceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML private TextField namePaymentTextField;
    @FXML private TextField addressPaymentTextField;
    @FXML private TextField zipCodePaymentTextField;
    @FXML private TextField phoneNumPaymentTextField;
    @FXML private TextField cardNumPaymentTextField;
    @FXML private TextField monthCardExpPaymentTextField;
    @FXML private TextField yearCardExpPaymentTextField;
    @FXML private TextField cardCVCPaymentTextField;

    public void switchToConfirmation(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ConfirmationScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToRoomDetails(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("RoomDetailsScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
