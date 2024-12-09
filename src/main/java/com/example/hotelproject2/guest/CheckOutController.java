package com.example.hotelproject2.guest;

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

public class CheckOutController {
    /**
     * Initializing Variables...
     */
    @FXML
    private TextField firstNameText;
    @FXML
    private TextField lastNameText;
    @FXML
    private Label errorText;
    @FXML
    private Label roomIdText;

    private Stage stage;
    private Scene scene;
    private Parent root;
    private final ReservationController r = new ReservationController();

    /**
     * Switches to HomeScene when "Back" button is clicked.
     * @param event
     * @throws IOException
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
     *
     * @param event
     * @throws IOException
     * Checks if User inputed their first name and last name on the textfields
     * when confirm button is clicked.
     * Does not switch to CheckOutConfirmScene.fxml until fields are filled.
     */
    @FXML
    private void handleSwitch(ActionEvent event) throws IOException {
        boolean isAllFilled = r.validateFields(firstNameText) && r.validateFields(lastNameText);
        if(isAllFilled)
            switchToCheckOutConfirm(event);
    }

    /**
     *
     * @param custId Customer ID
     * @param roomId Room ID
     * @return True or False if booking is found
     * @throws IOException
     * Method to check if Booking exists.
     */
    private boolean checkIfFound(String custId, String roomId) throws IOException {
        if(custId == null || roomId == null) {
            errorText.setStyle("-fx-text-fill: red;");
            if(custId == null)
                errorText.setText("Customer not found");
            else
                errorText.setText("No booking found");
            return false;
        }
        errorText.setText(null);
        errorText.setStyle(null);
        return true;
    }

    /**
     *
     * @param event
     * @throws IOException
     * Checks if Booking within MongoOps database,
     * Checks out customer's booking and switches to CheckOutConfirmScene.fxml
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
