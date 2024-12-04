package com.example.hotelproject2.admin;

import com.example.hotelproject2.LoginSceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminHomeSceneController {
    private FXMLLoader loader;
    private Parent root;
    private Scene scene;
    private Stage stage;

    private FXMLLoader switchScene(String fileName, ActionEvent event) throws IOException {
        loader = new FXMLLoader(getClass().getResource(fileName));
        root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        return loader;
    }

    @FXML
    private void switchToAdminCustomer(ActionEvent event) throws IOException {
        FXMLLoader cLoader = switchScene("ViewCustomersScene.fxml", event);

    }

    @FXML
    private void switchToAdminRooms(ActionEvent event) throws IOException {
        loader = new FXMLLoader(getClass().getResource("AdminRoomsScene.fxml"));
        root = loader.load();
        AdminRoomsSceneController controller = loader.getController();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void switchToLogin(ActionEvent event) throws IOException {
        LoginSceneController controller = new LoginSceneController();
        controller.switchToLoginScene(event);
    }

    @FXML
    private void switchToAdminHome(ActionEvent event) throws IOException {
        loader = new FXMLLoader(getClass().getResource("AdminHomeScene.fxml"));
        root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
