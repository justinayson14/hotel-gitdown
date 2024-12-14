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
    /**
     * Start of the program
     * @param stage the primary stage for this application, onto which
     * the application scene can be set.
     * Applications may create other stages, if needed, but they will not be
     * primary stages.
     * @throws IOException Failure to locate fxml file
     */
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("LoginScene.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }

    /**
     * Launches Program
     * @param args Arguments from terminal
     */
    public static void main(String[] args) {
        launch();
    }
}