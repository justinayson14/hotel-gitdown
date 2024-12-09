package com.example.hotelproject2.admin;

import com.example.hotelproject2.HotelController;
import com.example.hotelproject2.MongoOps;
import com.example.hotelproject2.models.DeluxeRoom;
import com.example.hotelproject2.models.PresRoom;
import com.example.hotelproject2.models.Room;
import com.example.hotelproject2.models.StandardRoom;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for handling switching scenes, displaying text,
 * and adding/removing rooms in View Rooms button of Admin scene
 */
public class ViewRoomsSceneController {

    @FXML private TableColumn<Room, String> roomIdCol;
    @FXML private TableColumn<Room, Integer> roomNumCol;
    @FXML private TableColumn<Room, Boolean> roomOccCol;
    @FXML private TableView<Room> table;
    @FXML private ToggleButton stdBtn;
    @FXML private ToggleButton deluxeBtn;
    @FXML private ToggleButton presBtn;
    @FXML private ChoiceBox<String> roomChoice;
    @FXML private TextField roomNum;
    @FXML private Label errorText;

    private FXMLLoader loader;
    private Parent root;
    private Stage stage;
    private Scene scene;
    private List<Room> rooms;
    private ToggleGroup roomPicks;
    private String curRoomType;

    /**
     * Initialize the table with list of Standard Rooms
     * from the database
     */
    @FXML
    public void initTable() {
        roomPicks = new ToggleGroup();
        stdBtn.setToggleGroup(roomPicks);
        deluxeBtn.setToggleGroup(roomPicks);
        presBtn.setToggleGroup(roomPicks);
        stdBtn.setSelected(true);
        loadRooms(null);
        roomIdCol.setCellValueFactory(new PropertyValueFactory<Room, String>("id"));
        roomNumCol.setCellValueFactory(new PropertyValueFactory<Room, Integer>("roomNum"));
        roomOccCol.setCellValueFactory(new PropertyValueFactory<Room, Boolean>("occupied"));
    }

    /**
     * Initialize add rooms prompt
     * @param table Table from the ViewRooms scene.
     * @param curRoomType Initialize drop box with current room type displayed
     */
    @FXML
    private void initDiag(TableView<Room> table, String curRoomType) {
        roomChoice.getItems().addAll("Standard", "Deluxe", "Presidential");
        roomChoice.setValue(curRoomType);
        this.table = table;
    }

    /**
     *
     * @param event
     * Method allows admin user to input room details for a new room in to
     * the database.
     */
    @FXML
    private void addRoom(ActionEvent event) {
        HotelController r = new HotelController();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        boolean isAllFilled = r.validateField(roomNum);
        if(isAllFilled) {
            boolean isUniqueRoomNum = MongoOps.checkRoomNum(Integer.parseInt(roomNum.getText()));
            if(isUniqueRoomNum) {
                errorText.setStyle(null);
                switch (roomChoice.getValue()) {
                    case "Standard" -> MongoOps.insertSingle(new StandardRoom(Integer.parseInt(roomNum.getText())));
                    case "Deluxe" -> MongoOps.insertSingle(new DeluxeRoom(Integer.parseInt(roomNum.getText())));
                    case "Presidential" -> MongoOps.insertSingle(new PresRoom(Integer.parseInt(roomNum.getText())));
                }
                table.getItems().setAll(MongoOps.queryAllByType(roomChoice.getValue()));
                stage.close();
            } else
                errorText.setText("Room number already exists...");
        }
    }

    /**
     * Remove selected room in table
     * @param event Action listener for button click
     */
    @FXML
    private void removeRoom(ActionEvent event) {
        Room selRoom = table.getSelectionModel().getSelectedItem();
        MongoOps.removeRoom(selRoom);
        table.getItems().setAll(MongoOps.queryAllByType(curRoomType));
    }


    /**
     * Load rooms into table based on button toggled.
     * Only oen room type can be displayed at a time.
     * @param event Action listener for button click
     */
    @FXML
    private void loadRooms(ActionEvent event) {
        List<Room> room = new ArrayList<>();
        if(stdBtn.isSelected()) {
            curRoomType = "Standard";
            rooms = MongoOps.queryAllByType(curRoomType);
            deluxeBtn.setSelected(false);
            presBtn.setSelected(false);
        } else if(deluxeBtn.isSelected()) {
            curRoomType = "Deluxe";
            rooms = MongoOps.queryAllByType(curRoomType);
            stdBtn.setSelected(false);
            presBtn.setSelected(false);
        } else if(presBtn.isSelected()) {
            curRoomType = "Presidential";
            rooms = MongoOps.queryAllByType(curRoomType);
            stdBtn.setSelected(false);
            deluxeBtn.setSelected(false);
        }
        table.getItems().setAll(rooms);
    }

    /**
     * Show adding room dialogue over current ViewRooms scene
     * @param event Action listener of pressing button
     * @throws IOException Failure to find fxml file of scene
     */
    @FXML
    private void showAddDialogue(ActionEvent event) throws IOException {
        loader = new FXMLLoader(getClass().getResource("AddRoomScene.fxml"));
        root = loader.load();
        ViewRoomsSceneController controller = loader.getController();
        controller.initDiag(table, curRoomType);
        stage = new Stage();
        scene = new Scene(root, 320, 365);
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }

    /**
     * Switch back to Admin Home Scene
     * @param event Action listener for pressing button
     * @throws IOException Failure to locate fxml file
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
