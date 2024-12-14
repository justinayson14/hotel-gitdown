package com.example.hotelproject2;

import com.example.hotelproject2.admin.AdminHomeSceneController;
import com.example.hotelproject2.guest.GuestHomeSceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller for handling logging in Admin scene and
 * accessing program as a Guests
 */
public class LoginSceneController {
    @FXML private PasswordField password;
    @FXML private Label errorText;

    private Stage stage;
    private Scene scene;
    private Parent root;
    private FXMLLoader loader;

    /**
     * Gets text input from the user and checks the correct password
     * before switching to the AdminHomeScene.fxml
     * @param event Action listener for pressing button
     * @throws IOException Failure to locate fxml file
     */
    @FXML
    private void checkPassword(ActionEvent event) throws IOException {
        if(password.getText().equals("gitdown"))
            switchToAdminHomeScene(event);
        else
            errorText.setText("Invalid login!");
    }

    /**
     * When user presses the "Guest" Button, it switches to GuestHomeScene.fxml.
     * Loads GuestHomeSceneController object.
     * @param event Action listener for pressing button
     * @throws IOException Failure to locate fxml file
     */
    @FXML
    private void switchToHomeScene(ActionEvent event) throws IOException {
        loader = new FXMLLoader(getClass().getResource("guest/GuestHomeScene.fxml"));
        root = loader.load();
        GuestHomeSceneController controller = loader.getController();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    /**
     * Method switches to AdminHomeScene.fxml.
     * Loads AdminHomeSceneController object.
     * @param event Action listener for pressing button
     * @throws IOException Failure to locate fxml file
     */
    private void switchToAdminHomeScene(ActionEvent event) throws IOException {
        loader = new FXMLLoader(getClass().getResource("admin/AdminHomeScene.fxml"));
        root = loader.load();
        AdminHomeSceneController controller = loader.getController();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    /**
     * Method switches to LoginScene.fxml.
     * Loads LoginSceneController object.
     * @param event Action listener for pressing button
     * @throws IOException Failure to locate fxml file
     */
    @FXML
    public void switchToLoginScene(ActionEvent event) throws IOException {
        loader = new FXMLLoader(getClass().getResource("LoginScene.fxml"));
        root = loader.load();
        LoginSceneController controller = loader.getController();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    /**
     * Method switches to AdminLoginScene.fxml.
     * Loads LoginSceneController object.
     * @param event Action listener for pressing button
     * @throws IOException Failure to locate fxml file
     */
    @FXML
    private void switchToAdminLoginScene(ActionEvent event) throws IOException {
        loader = new FXMLLoader(getClass().getResource("admin/AdminLoginScene.fxml"));
        root = loader.load();
        LoginSceneController controller = loader.getController();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
