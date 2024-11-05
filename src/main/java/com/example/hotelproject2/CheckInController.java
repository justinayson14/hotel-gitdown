package com.example.hotelproject2;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.hotelproject2.models.Customers;


//Controller Class for the Check In Scene (Scene2.fxml)
public class CheckInController implements Initializable {

    //Scene switch variables
    private Stage stage;
    private Scene scene;
    private Parent root;

    //Customer ID Variable
    private int customerID;

    //Customer Name
    @FXML TextField name;

    //Guests Number Spinner variables
    @FXML
    private Spinner<Integer> guestAmtSpinner;
    @FXML
    private Label numGuestsLabel;
    int currentGuestValue;

    //Room Type Choice box variables
    @FXML
    private Label RoomTypeLabel;
    @FXML
    private ChoiceBox<String> RoomTypeChoice;
    private String[] roomTypes = {"Standard", "Deluxe", "Presidential"}; // Set the names of each roomType
    private int[] roomCosts = {150000, 300000, 1000000}; // Set the costs for each roomType
    String roomChoice;

    //# of Day Spinner Variables
    @FXML
    private Spinner<Integer> numDaysSpinner;
    @FXML
    private Label totalRateLabel;
    int currentDaysValue;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Code for setting up both Guest Number Label and Guest Spinner
        SpinnerValueFactory<Integer> numGuestsFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10);

        //Sets initial value of Spinner
        numGuestsFactory.setValue(1);
        //Connect SpinnerValueFactory to Spinner
        guestAmtSpinner.setValueFactory(numGuestsFactory);

        currentGuestValue = guestAmtSpinner.getValue();

        numGuestsLabel.setText(Integer.toString(currentGuestValue));

        guestAmtSpinner.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                currentGuestValue = guestAmtSpinner.getValue();
                numGuestsLabel.setText(Integer.toString(currentGuestValue));
            }
        });


        //Code for New Spinner
        //Code for setting up both Guest Number Label and Guest Spinner
        SpinnerValueFactory<Integer> numDaysFactory =
            new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10);
        //Sets initial value of Spinner
        numDaysFactory.setValue(1);
        //Connect SpinnerValueFactory to Spinner
        numDaysSpinner.setValueFactory(numDaysFactory);

        currentDaysValue = numDaysSpinner.getValue();

        //Total Rate
        roomChoice = RoomTypeChoice.getValue();

        numDaysSpinner.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                updateTotalRateLabel();
            }
        });





        //Choice Box for Roomtype Commands
        RoomTypeChoice.getItems().addAll(roomTypes);
        RoomTypeChoice.setOnAction(this::getRoomType);
    }

    // Gets the cost for each room type
    public int getRoomCost(String roomType) {
        int i;
        for (i = 0; i < roomTypes.length; i++)
            if (roomType == roomTypes[i] && i < roomCosts.length)
                return roomCosts[i];
  
        if (i - 1 < roomCosts.length)
            System.out.print("\nError: roomType \"" + roomType + "\" is invalid.");
        else
            System.out.print("\nError: roomType \"" + roomType + "\" has no cost.");
        return 0;
    }

    // Gets the total rate amount
    public int getTotalRate() {
        roomChoice = RoomTypeChoice.getValue();
        currentDaysValue = numDaysSpinner.getValue();
        return currentDaysValue * getRoomCost(roomChoice);
    }

    // Update the totalRateLabel if all required fields are filled
    public void updateTotalRateLabel() {
        if (RoomTypeChoice.getValue() != null)
        totalRateLabel.setText(Integer.toString(getTotalRate()));
    }

    //Sets texts for room type
    public void getRoomType(ActionEvent event){
        String myRoomTypes = RoomTypeChoice.getValue();
        RoomTypeLabel.setText(myRoomTypes);
        updateTotalRateLabel();
    }

    //Cancel Button Method - Switches back to Homepage (Scene 1)
    public void switchToScene1(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("HomeScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    //Book Room
    public void bookRoom(ActionEvent event) throws IOException {
        //switches scene to UserCheckInDetailsScene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserCheckInDetailsScene.fxml"));
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        //prints info on console
        System.out.printf("\n\nName: " + name.getText() + "\nNumber of Guests: " + currentGuestValue + "\nRoom Type: " + RoomTypeChoice.getValue() + "\n# of Days: " + numDaysSpinner.getValue() + "\nTotal Rate: " + getTotalRate());
        System.out.print("\napiKey: " + System.getenv("apiKey"));
        Customers customer = new Customers(name.getText(), currentGuestValue);
        MongoOps.insertSingle(customer);
        System.out.printf("\n\nName: " + name.getText() + "\nNumber of Guests: " + numGuestsLabel.getText() + "\nRoom Type: " + RoomTypeChoice.getValue() + "\n# of Days: " + numDaysSpinner.getValue() + "\nTotal Rate: " + getTotalRate());
    }
}
