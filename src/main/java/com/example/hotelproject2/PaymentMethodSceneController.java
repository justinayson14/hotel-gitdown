package com.example.hotelproject2;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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

}
