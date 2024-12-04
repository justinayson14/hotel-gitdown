package com.example.hotelproject2.guest;
import com.example.hotelproject2.MongoOps;
import com.example.hotelproject2.models.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;


import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 Room Details Controller Class*
 */
public class RoomDetailsController {
    @FXML
    public ChoiceBox<String> roomTypeChoiceBox;
    @FXML
    private Label roomDescText;
    @FXML
    private Label bedNumText;
    @FXML
    private Label bathNumText;
    @FXML
    private Label roomCostText;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;
    @FXML
    private Label totalCostText;
    @FXML
    private Label errorText;

    private final String[] roomTypes = {"Standard", "Deluxe", "Presidential"};
    private Customers customer;
    private double roomCost;
    private String roomType;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double totalCost;

    /**
     * This method is used to pass the customer data
     * from another controller to this controller for later use.
     * @param customer Object containing details about customer
     */
    public void getCustomer(Customers customer) {
        this.customer = customer;
    }

    /**
     Creates the interaction for the Choice Box*
     */
    @FXML
    public void initialize() {
        roomTypeChoiceBox.getItems().addAll(roomTypes);
        roomTypeChoiceBox.setValue("Standard");
        getRoomType(null);
        roomTypeChoiceBox.setOnAction(this::getRoomType);
        LocalDate minDate = LocalDate.now();
        startDatePicker.setDayCellFactory(d ->
                new DateCell() {
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        if(item.isBefore(minDate)) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }
                    }
                });
        startDatePicker.setValue(minDate);
        getStartDate(null);
        calculateTotalCost(null);
    }

    @FXML
    private void getStartDate(ActionEvent event) {
        startDate = startDatePicker.getValue();
        endDatePicker.setDayCellFactory(d ->
                new DateCell() {
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        if(item.isEqual(startDate) || item.isBefore(startDate)) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }
                    }
                });
        endDatePicker.setValue(startDate.plusDays(1));
    }

    @FXML
    private void calculateTotalCost(ActionEvent event) {
        endDate = endDatePicker.getValue();
        long diffInDays = ChronoUnit.DAYS.between(startDate, endDate);
        if (diffInDays <= 0 && startDatePicker != null)
            totalCostText.setText("Please pick end date after start date...");
        else {
            totalCost = diffInDays*roomCost;
            totalCostText.setText("$"+totalCost);
        }
    }

    /**
     * This method triggers in even where the user clicks on a option on the Choice Box
     * This will generate the Room Description, Number of beds and baths based on
     * the user's choice.
     * @param event
     */
    private void getRoomType(ActionEvent event) {
        roomType = roomTypeChoiceBox.getValue();
        switch (roomType) {
            case "Standard" -> {
                roomDescText.setText(StandardRoom.getDesc());
                bedNumText.setText(Integer.toString(StandardRoom.getNumBeds()));
                bathNumText.setText(Integer.toString(StandardRoom.getNumBaths()));
                roomCostText.setText("$"+StandardRoom.getPrice());
                roomCost = StandardRoom.getPrice();
            }
            case "Deluxe" -> {
                roomDescText.setText(DeluxeRoom.getDesc());
                bedNumText.setText(Integer.toString(DeluxeRoom.getNumBeds()));
                bathNumText.setText(Integer.toString(DeluxeRoom.getNumBaths()));
                roomCostText.setText("$"+DeluxeRoom.getPrice());
                roomCost = DeluxeRoom.getPrice();
            }
            case "Presidential" -> {
                roomDescText.setText(PresRoom.getDesc());
                bedNumText.setText(Integer.toString(PresRoom.getNumBeds()));
                bathNumText.setText(Integer.toString(PresRoom.getNumBaths()));
                roomCostText.setText("$"+PresRoom.getPrice());
                roomCost = PresRoom.getPrice();
            }
        }
    }

    /**
     * switches back to 'Customer' Scene and sends customer data to the database
     * @param event
     * @throws IOException
     */
    @FXML
    private void switchToCustomer(ActionEvent event) throws IOException {
        // sends customer data to database
        System.out.print("\nThe following customer data was received and added to the database: " + customer + "\n");
        Parent root = FXMLLoader.load(getClass().getResource("CustomerScene.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * switches to PaymentMethodScene and passes customer data along.
     * @throws IOException
     */
    @FXML
    private void switchToPayment(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PaymentScene.fxml"));
        Parent root = loader.load();
        // creates an instance of the scene's controller to pass customer data to
        PaymentSceneController controller = loader.getController();
        // prints the customer data to console and passes it along
        System.out.println("\n---\nPassing along the following customer data: " + customer + "\n---"); // prints to console
        System.out.println(roomType);
        Room room = MongoOps.queryAvailRoomByType(roomType);
        if(room != null) {
            System.out.println(room.getClass());
            Booking booking = new Booking();
            booking.setCustomerId(customer.getId());
            booking.setCheckInDate(startDate.toString());
            booking.setCheckOutDate(endDate.toString());
            booking.setRoomType(room.getClass().getSimpleName());
            booking.setRoomId(room.getId());
            booking.setTotalCost(totalCost);
            controller.initData(customer, booking); // passes it along
            // switch scene
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else
            errorText.setText("No "+roomType+" available.");
    }


}
