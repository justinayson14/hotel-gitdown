package com.example.hotelproject2.admin;

import com.example.hotelproject2.LoginSceneController;
import com.example.hotelproject2.MongoOps;
import com.example.hotelproject2.models.Booking;
import com.example.hotelproject2.models.Customers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

public class AdminHomeSceneController {
    /**
     * Initializing Variables...
     */
    private FXMLLoader loader;
    private Parent root;
    private Scene scene;
    private Stage stage;

    @FXML private TableView<Customers> custTable;
    @FXML private TableColumn<Customers, String> custIdCol;
    @FXML private TableColumn<Customers, String> custNameCol;
    @FXML private TableColumn<Customers, Integer> custPartyCol;
    @FXML private Label custCount;

    @FXML private TableView<Booking> bookTable;
    @FXML private TableColumn<Booking, String> bookIdCol;
    @FXML private TableColumn<Booking, String> bookInCol;
    @FXML private TableColumn<Booking, String> bookOutCol;
    @FXML private TableColumn<Booking, String> bookCustNameCol;
    @FXML private TableColumn<Booking, Integer> bookRoomNumCol;
    @FXML private TableColumn<Booking, String> bookRoomTypeCol;
    @FXML private TableColumn<Booking, Double> bookCostCol;
    @FXML private Label bookingRev;
    @FXML private Label bookingCount;

    /**
     *
     * @param fileName - Take fxml file by name
     * @param event
     * @throws IOException
     * Method to load FXML files into the program
     */

    private void loadScene(String fileName, ActionEvent event) throws IOException {
        loader = new FXMLLoader(getClass().getResource(fileName));
        root = loader.load();
    }

    /**
     *
     * @param event
     * @throws IOException
     * Method to show fxml file on the program.
     */
    private void showScene(ActionEvent event) throws IOException {
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     *
     * @param event
     * @throws IOException
     * When 'View Customer' button is clicked, switched to ViewCustomerScene.fxml.
     * Opens up a chart with Customer information from reservation.
     * Data documented from MongoOps database.
     */
    @FXML
    private void switchToViewCustomer(ActionEvent event) throws IOException {
        loadScene("ViewCustomersScene.fxml", event);
        AdminHomeSceneController controller = loader.getController();
        ObservableList<Customers> customers = FXCollections.observableArrayList(MongoOps.queryAllByType("Customers"));
        controller.custIdCol.setCellValueFactory(new PropertyValueFactory<Customers, String>("id"));
        controller.custNameCol.setCellValueFactory(new PropertyValueFactory<Customers, String>("name"));
        controller.custPartyCol.setCellValueFactory(new PropertyValueFactory<Customers, Integer>("partyCount"));
        controller.custTable.getItems().addAll(customers);
        controller.custCount.setText(Integer.toString(customers.size()));
        showScene(event);
    }
    /**
     *
     * @param event
     * @throws IOException
     * When 'View Rooms' button is clicked, switched to ViewRoomsScene.fxml.
     * Opens up a chart with rooms and room status (Occupied/Not Occupied).
     * Loads ViewRoomsSceneController object.
     */
    @FXML
    private void deleteCustomer(ActionEvent event) {
        MongoOps.removeCustomer(custTable.getSelectionModel().getSelectedItem());
        List<Customers> allCustomer = MongoOps.queryAllByType("Customers");
        custTable.getItems().setAll(allCustomer);
        custCount.setText(Integer.toString(allCustomer.size()));
    }

    @FXML
    private void switchToViewRooms(ActionEvent event) throws IOException {
        loadScene("ViewRoomsScene.fxml", event);
        ViewRoomsSceneController controller = loader.getController();
        controller.initTable();
        showScene(event);
    }

    /**
     *
     * @param event
     * @throws IOException
     * Method Switched to LoginScene.fxml.
     * Loads LoginSceneController object.
     */
    @FXML
    private void deleteBooking(ActionEvent event) {
        DecimalFormat df = new DecimalFormat("0.00");
        MongoOps.removeBooking(bookTable.getSelectionModel().getSelectedItem());
        List<Booking> allBooking = MongoOps.queryAllByType("Bookings");
        bookTable.getItems().setAll(allBooking);
        bookingCount.setText(Integer.toString(allBooking.size()));
        double sum = allBooking.stream().mapToDouble(Booking::getTotalCost).sum();
        bookingRev.setText(df.format(sum));
    }

    @FXML
    private void switchToLogin(ActionEvent event) throws IOException {
        LoginSceneController controller = new LoginSceneController();
        controller.switchToLoginScene(event);
    }

    /**
     *
     * @param event
     * @throws IOException
     * Method switches to AdminHomeScene.fxml
     */
    @FXML
    private void switchToAdminHome(ActionEvent event) throws IOException {
        loadScene("AdminHomeScene.fxml", event);
        showScene(event);
    }

    /**
     *
     * @param event
     * @throws IOException
     * When user clicks "View Bookings" Button, it shows information on
     * reservations made.
     * Switches to "ViewBookingsScene.fxml.
     * Loads AdminHomeSceneController object
     * Pulls "Bookings" data from MongoOps database with Observablelist object
     */
    @FXML
    private void switchToViewBookings(ActionEvent event) throws IOException {
        DecimalFormat df = new DecimalFormat("0.00");
        loadScene("ViewBookingsScene.fxml", event);
        AdminHomeSceneController controller = loader.getController();
        ObservableList<Booking> bookings = FXCollections.observableArrayList(MongoOps.queryAllByType("Bookings"));
        controller.bookIdCol.setCellValueFactory(new PropertyValueFactory<Booking, String>("id"));
        controller.bookCustNameCol.setCellValueFactory(new PropertyValueFactory<Booking, String>("customerName"));
        controller.bookRoomNumCol.setCellValueFactory(new PropertyValueFactory<Booking, Integer>("roomNum"));
        controller.bookRoomTypeCol.setCellValueFactory(new PropertyValueFactory<Booking, String>("roomType"));
        controller.bookInCol.setCellValueFactory(new PropertyValueFactory<Booking, String>("checkInDate"));
        controller.bookOutCol.setCellValueFactory(new PropertyValueFactory<Booking, String>("checkOutDate"));
        controller.bookCostCol.setCellValueFactory(new PropertyValueFactory<Booking, Double>("totalCost"));
        controller.bookTable.getItems().addAll(bookings);
        controller.bookingCount.setText(Integer.toString(bookings.size()));
        double sum = bookings.stream().mapToDouble(Booking::getTotalCost).sum();
        controller.bookingRev.setText(df.format(sum));
        showScene(event);
    }
}
