package com.example.hotelproject2;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import com.example.hotelproject2.models.Customers;

public class ConfirmationSceneController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    private Customers customer = new Customers();

    /**
     * This method is used to pass the customer data
     * from another controller to this controller for later use.
     * @param customer
     */
    public void getCustomerData(Customers customer) {
        this.customer = customer;
    }

    public void switchToHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("HomeScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToThankYou(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ThankYouScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
        System.out.print("\n---\nRecieved the following customer data");
        /*MongoOps.insertSingle(customer);
        System.out.print(" and sent it to the database");*/
        System.out.println(": " + customer + "\n---");
    }

}
