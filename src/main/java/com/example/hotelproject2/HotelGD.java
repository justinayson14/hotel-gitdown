package com.example.hotelproject2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * HotelGD class starts the program by extending to application and
 * showing the first scene, LoginScene.fxml
 */

public class HotelGD extends Application {
    public static Stage stage;
    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("LoginScene.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }

    /**
     *
     * @param args
     * Launches Program
     */

    public static void main(String[] args) {
        launch();
    }
}