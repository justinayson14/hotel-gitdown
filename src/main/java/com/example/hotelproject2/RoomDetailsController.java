package com.example.hotelproject2;
import com.example.hotelproject2.models.DeluxeRoom;
import com.example.hotelproject2.models.StandardRoom;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class RoomDetailsController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    //Room Class Objects

    StandardRoom newStandard = new StandardRoom();
    DeluxeRoom NewDeluxe = new DeluxeRoom();






    public void switchToScene1(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Scene1.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }




}
