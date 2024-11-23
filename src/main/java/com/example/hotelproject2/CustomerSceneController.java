package com.example.hotelproject2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import com.example.hotelproject2.models.Customers;

public class CustomerSceneController {
  private Stage stage;
  private Scene scene;
  private Parent root;
  private Customers customer = new Customers();

  @FXML TextField firstName;
  @FXML TextField lastName;
  @FXML Spinner guestAmtSpinner;

    /**
     * Switches to HomeScene when "Cancel" button is clicked
     * @param event
     * @throws IOException
     */
    public void switchToScene1(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("HomeScene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Switches to RoomDetailsScene when "Book Room" button is clicked
     * and passes customer data along.
     * @param event
     * @throws IOException
     */
    public void switchToRoomDetailsScene(ActionEvent event) throws IOException {

        // prepare the loader
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RoomDetailsScene.fxml"));
        Parent root = loader.load();

        // create an instance of the scene's controller to pass customer data to
        RoomDetailsController controller = loader.getController();
        String customerFullName = firstName.getText() + " " + lastName.getText();
        customer.setName(customerFullName); // sets the customer's name

        // PARTY COUNT IS NOT WORKING RIGHT NOW
        //int customerPartyCount = (int)guestAmtSpinner.getValue();
        //customer.setPartyCount(customerPartyCount); // sets the customer's party count

        controller.getCustomerData(customer); // sends the customer data
        System.out.print("\nAttempting to pass along the following customer data: " + customer + "\n"); // prints the customer to console for debugging


        // switch scene
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}