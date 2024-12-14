package com.example.hotelproject2.guest;

import com.example.hotelproject2.HotelController;
import com.example.hotelproject2.MongoOps;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller for handling checking out customer and
 * switching scenes from customer checkout scene.
 */
public class CheckOutController {
    @FXML private TextField firstNameText;
    @FXML private TextField lastNameText;
    @FXML private Label errorText;
    @FXML private Label roomIdText;

    private Stage stage;
    private Scene scene;
    private Parent root;

    /**
     * Switches to HomeScene when "Back" button is clicked.
     * @param event Action listener for pressing button
     * @throws IOException Failure to load fxml file
     */
    @FXML
    private void switchToHomeScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("GuestHomeScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Checks if User inputted their first name and last name on the text fields
     * when confirm button is clicked.
     * Does not switch to CheckOutConfirmScene.fxml until fields are filled.
     * @param event Action listener for pressing button
     * @throws IOException Failure to load fxml file
     */
    @FXML
    private void handleSwitch(ActionEvent event) throws IOException {
        HotelController r = new HotelController();
        boolean isAllFilled = r.validateField(firstNameText) && r.validateField(lastNameText);
        if(isAllFilled)
            switchToCheckOutConfirm(event);
    }

    /**
     * Checks if Booking within MongoOps database,
     * Checks out customer's booking and switches to CheckOutConfirmScene.fxml
     * @param event Action listener for pressing button
     * @throws IOException Failure to load fxml file
     */
    private void switchToCheckOutConfirm(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CheckOutConfirmScene.fxml"));
        root = loader.load();

        CheckOutController controller = loader.getController();
        String customerName = firstNameText.getText().toLowerCase() + ' ' + lastNameText.getText().toLowerCase();
        String customerId = MongoOps.queryCustomerIdByName(customerName);
        if(customerId == null) {
            errorText.setText("Customer not found");
            errorText.setStyle("-fx-text-fill: red;");
        } else {
            String roomId = MongoOps.checkOutRoom(customerId);
            controller.roomIdText.setText(roomId);
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

}
