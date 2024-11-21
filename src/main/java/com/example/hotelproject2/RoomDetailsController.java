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
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;

/**
 Room Details Controller Class*
 */
public class RoomDetailsController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
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
    private Label roomDescLabel;
    @FXML
    private Label bedNumLabel;
    @FXML
    private Label bathNumLabel;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private DatePicker endDatePicker;

    /**
    New Standard Room, New Deluxe Room and New Presidential Room class objects
     *
     */

    StandardRoom newStandard = new StandardRoom(1);
    DeluxeRoom newDeluxe = new DeluxeRoom(1);
    PresRoom newPres = new PresRoom(1);

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
     * This method triggers in even where the user clicks on a option on the Choice Box
     * This will generate the Room Description, Number of beds and baths based on
     * the user's choice.
     */
    private void getRoomType(ActionEvent event) {
        String myRoomTypes = roomTypeChoiceBox.getValue();
        if(roomTypeChoiceBox.getValue().equals("Standard")){
            roomDescLabel.setText(newStandard.getDesc());
            bedNumLabel.setText(Integer.toString(newStandard.getNumBeds()));
            bathNumLabel.setText(Integer.toString(newStandard.getNumBaths()));

        } else if(roomTypeChoiceBox.getValue().equals("Deluxe")){
            roomDescLabel.setText(newDeluxe.getDesc());
            bedNumLabel.setText(Integer.toString(newDeluxe.getNumBeds()));
            bathNumLabel.setText(Integer.toString(newDeluxe.getNumBaths()));

        } else if(roomTypeChoiceBox.getValue().equals("Presidential")){
            roomDescLabel.setText(newPres.getDesc());
            bedNumLabel.setText(Integer.toString(newPres.getNumBeds()));
            bathNumLabel.setText(Integer.toString(newPres.getNumBaths()));
        }


    }










    /**
     *
     * @param event
     * @throws IOException
     * switches back to 'Customer' Scene
     */
    public void switchToCustomerInfo(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }




}
