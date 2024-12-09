package com.example.hotelproject2.admin;

import com.example.hotelproject2.MongoOps;
import com.example.hotelproject2.models.Room;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class ViewRoomsSceneController {
    /**
     * Initializing Variables...
     */

    private FXMLLoader loader;
    private Parent root;
    private Stage stage;
    private Scene scene;
    private List<Room> rooms;

    @FXML
    private TableColumn<Room, String> roomIdCol;
    @FXML
    private TableColumn<Room, Integer> roomNumCol;
    @FXML
    private TableColumn<Room, Boolean> roomOccCol;
    @FXML
    private TableView<Room> table;
    @FXML
    private ToggleButton stdBtn;
    @FXML
    private ToggleButton deluxeBtn;
    @FXML
    private ToggleButton presBtn;
    @FXML
    private ChoiceBox<String> roomChoice;
    @FXML
    private TextField roomNum;

    /**
     * Calls the MongoOps database for the "Standard" Room Type.
     * Shows data on the rooms through a observable arraylist.
     */
    @FXML
    public void initialize() {
        rooms = MongoOps.queryAllByType("Standard");
        ObservableList<Room> obList = FXCollections.observableArrayList(rooms);
        roomIdCol.setCellValueFactory(new PropertyValueFactory<Room, String>("id"));
        roomNumCol.setCellValueFactory(new PropertyValueFactory<Room, Integer>("roomNum"));
        roomOccCol.setCellValueFactory(new PropertyValueFactory<Room, Boolean>("occupied"));

        table.getItems().addAll(rooms);
    }

    /**
     *
     * @param event
     * @throws IOException
     * Method switches to AddRoomScene.fxml.
     */
    @FXML
    private void showAddDialogue(ActionEvent event) throws IOException {
        Stage addStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        loader = new FXMLLoader(getClass().getResource("AddRoomScene.fxml"));
        root = loader.load();
        stage = new Stage();
        scene = new Scene(root, 320, 365);
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }

    /**
     *
     * @param event
     * @throws IOException
     * Method switches to AdminHomeScene.fxml.
     * Load AdminHomeSceneController object.
     */
    @FXML
    private void switchToAdminHomeScene(ActionEvent event) throws IOException {
        loader = new FXMLLoader(getClass().getResource("AdminHomeScene.fxml"));
        root = loader.load();
        AdminHomeSceneController controller = loader.getController();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
