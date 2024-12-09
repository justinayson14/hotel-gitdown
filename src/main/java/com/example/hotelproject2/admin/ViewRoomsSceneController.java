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
    @FXML
    private Label errorText;

    private ToggleGroup roomPicks;
    private String curRoomType;


    /**
     * Table the displays list of rooms in all room type categories
     * along with room status and ID.
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

        table.getItems().addAll(rooms);
    }


    /**
     *
     * @param table - table that displays room information.
     * @param curRoomType - Room type chosen based on button clicked
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
     *
     * @param event
     * Method removes rooms from database
     */
    @FXML
    private void removeRoom(ActionEvent event) {
        Room selRoom = table.getSelectionModel().getSelectedItem();
        MongoOps.removeRoom(selRoom);
        table.getItems().setAll(MongoOps.queryAllByType(curRoomType));
    }


    /**
     *
     * @param event
     * Loads rooms based on Room type button pressed
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
     *
     * @param event
     * @throws IOException
     * Method Switches to AddRoomScene.fxml
     * Loads ViewRoomsController object.
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
