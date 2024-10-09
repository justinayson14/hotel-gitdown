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


//Controller Class for the Check In Scene (Scene2.fxml)
public class CheckInController implements Initializable {

    //Scene switch variables
    private Stage stage;
    private Scene scene;
    private Parent root;

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
    private String[] roomTypes = {"Standard", "Deluxe", "Presidential"};
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
                roomChoice = RoomTypeChoice.getValue();
                currentDaysValue = numDaysSpinner.getValue();
                if(roomChoice.equals("Standard")){
                    int totalRateAmt = currentDaysValue * 150;
                    totalRateLabel.setText(Integer.toString(currentDaysValue));
                }
            }
        });





        //Choice Box for Roomtype Commands
        RoomTypeChoice.getItems().addAll(roomTypes);
        RoomTypeChoice.setOnAction(this::getRoomType);
    }


    //Sets texts for room type
    public void getRoomType(ActionEvent event){
        String myRoomTypes = RoomTypeChoice.getValue();
        RoomTypeLabel.setText(myRoomTypes);

    }






    //Cancel Button Method - Switches back to Homepage (Scene 1)
    public void switchToScene1(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Scene1.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /*
    public void submitBooking(){

    }
     */
}
