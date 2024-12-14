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

/**
 * Controller for handling switching scenes from Admin Home scene
 * and with handling loading data in customer & booking table.
 */
public class AdminHomeSceneController {
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

    private FXMLLoader loader;
    private Parent root;
    private Scene scene;
    private Stage stage;

    /**
     * Load the fxml file and set as the Parent
     * @param fileName Name of fxml file
     * @param event Action listener of pressing button
     * @throws IOException Failure to locate fxml file
     */

    private void loadScene(String fileName, ActionEvent event) throws IOException {
        loader = new FXMLLoader(getClass().getResource(fileName));
        root = loader.load();
    }

    /**
     * Show the loaded fxml to the stage
     * @param event Action listener for pressing button
     * @throws IOException Scene is null
     */
    private void showScene(ActionEvent event) throws IOException {
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Switch to customer scene to show table of all customers in database
     * @param event Action listener for pressing button
     * @throws IOException Failure to load fxml file
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
     * Remove selected customer in database and reload
     * customer into table
     * @param event Action listener for pressing button
     */
    @FXML
    private void deleteCustomer(ActionEvent event) {
        MongoOps.removeCustomer(custTable.getSelectionModel().getSelectedItem());
        List<Customers> allCustomer = MongoOps.queryAllByType("Customers");
        custTable.getItems().setAll(allCustomer);
        custCount.setText(Integer.toString(allCustomer.size()));
    }

    /**
     * Switch to viewing table of rooms by room type and
     * initialized data in table
     * @param event Action listener for pressing button
     * @throws IOException Failure to load fxml file
     */
    @FXML
    private void switchToViewRooms(ActionEvent event) throws IOException {
        loadScene("ViewRoomsScene.fxml", event);
        ViewRoomsSceneController controller = loader.getController();
        controller.initTable();
        showScene(event);
    }

    /**
     * Delete selected booking in table
     * @param event Action listener for pressing button
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

    /**
     * Logout of admin access
     * @param event Action listener for pressing button
     * @throws IOException Failure to locate fxml file
     */
    @FXML
    private void switchToLogin(ActionEvent event) throws IOException {
        LoginSceneController controller = new LoginSceneController();
        controller.switchToLoginScene(event);
    }

    /**
     * Switch back to admin home scene
     * @param event Action listener for pressing button
     * @throws IOException Failure to locate fxml file
     */
    @FXML
    private void switchToAdminHome(ActionEvent event) throws IOException {
        loadScene("AdminHomeScene.fxml", event);
        showScene(event);
    }

    /**
     * Switch to booking scene and initialized table
     * with all bookings in database.
     * @param event
     * @throws IOException
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
