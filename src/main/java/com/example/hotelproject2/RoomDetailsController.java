package com.example.hotelproject2;
import com.example.hotelproject2.models.DeluxeRoom;
import com.example.hotelproject2.models.PresRoom;
import com.example.hotelproject2.models.StandardRoom;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.hotelproject2.models.Customers;

/**
 Room Details Controller Class*
 */
public class RoomDetailsController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Customers customer;

    /**
     Choice Box Variable for Room type*
     */
    @FXML
    public ChoiceBox<String> roomTypeChoiceBox;

    /**
     Room Type String Array*
     */
    private String[] roomTypes = {"Standard", "Deluxe", "Presidential"};

    /**
     Label variables for Room Description, Number of Beds, Number of Baths*
     */
    @FXML
    private Label roomDescText;
    @FXML
    private Label bedNumText;
    @FXML
    private Label bathNumText;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private DatePicker endDatePicker;

    /**
    New Standard Room, New Deluxe Room and New Presidential Room class objects
     *
     */

    StandardRoom newStandard = new StandardRoom();
    DeluxeRoom newDeluxe = new DeluxeRoom();
    PresRoom newPres = new PresRoom();

   /* private double calculateTotalCost(String startDatePicker, String endDatePicker, double cost) {
        LocalDate start = LocalDate.parse(startDatePicker);
        LocalDate end = LocalDate.parse(endDatePicker);
        long diffInDays = ChronoUnit.DAYS.between(start, end);
        return (double)diffInDays * cost;
    } */

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        roomTypeChoiceBox.getItems().addAll(roomTypes);
        roomTypeChoiceBox.setOnAction(this::getRoomType);
    }

    /**
     * 
     * @param event
     * This method is used to pass the customer data
     * from another scene to this scene for later use.
     */
    public void getCustomerData(Customers customer) {
        this.customer = customer;
    }

    /**
     *
     * @param event
     * This method triggers in even where the user clicks on a option on the Choice Box
     * This will generate the Room Description, Number of beds and baths based on
     * the user's choice.
     */
    private void getRoomType(ActionEvent event) {
        String pickedRoom = roomTypeChoiceBox.getValue();
        switch (pickedRoom) {
            case "Standard" -> {
                roomDescText.setText(newStandard.getDesc());
                bedNumText.setText(Integer.toString(newStandard.getNumBeds()));
                bathNumText.setText(Integer.toString(newStandard.getNumBaths()));
            }
            case "Deluxe" -> {
                roomDescText.setText(newDeluxe.getDesc());
                bedNumText.setText(Integer.toString(newDeluxe.getNumBeds()));
                bathNumText.setText(Integer.toString(newDeluxe.getNumBaths()));
            }
            case "Presidential" -> {
                roomDescText.setText(newPres.getDesc());
                bedNumText.setText(Integer.toString(newPres.getNumBeds()));
                bathNumText.setText(Integer.toString(newPres.getNumBaths()));
            }
        }
    }

    /**
     *
     * @param event
     * @throws IOException
     * switches back to 'Customer' Scene and
     * sends customer data to the database.
     */
    public void switchToCustomerInfo(ActionEvent event) throws IOException {
        //sending customer data to database
        System.out.print("\nThe following customer data was recieved and added to the database: " + customer + "\n");
        MongoOps.insertSingle(customer);
        
        //switching to 'Customer' Scene
        Parent root = FXMLLoader.load(getClass().getResource("ThankYouScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
