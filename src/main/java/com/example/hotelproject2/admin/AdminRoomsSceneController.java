package com.example.hotelproject2.admin;

import com.example.hotelproject2.MongoOps;
import com.example.hotelproject2.models.Room;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class AdminRoomsSceneController {

    private FXMLLoader loader;
    private Parent root;
    private Stage stage;
    private Scene scene;
    private List<Room> rooms;

    @FXML
    private void initialize() {
        rooms = MongoOps.queryAllRoomsByType("Standard");
        ObservableList<Room> observable = FXCollections.observableList(rooms);

    }

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
