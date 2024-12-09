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

public class LoginSceneController {
    /**
     * Initializing Variables...
     */
    private Stage stage;
    private Scene scene;
    private Parent root;
    private FXMLLoader loader;

    @FXML
    private PasswordField password;
    @FXML
    private Label errorText;

    /**
     *
     * @param event
     * @throws IOException
     * Gets text input from the user and checks the correct password
     * before switching to the AdminHomeScene.fxml
     */
    @FXML
    private void checkPassword(ActionEvent event) throws IOException {
        if(password.getText().equals("gitdown"))
            switchToAdminHomeScene(event);
        else
            errorText.setText("Invalid login!");
    }

    /**
     *
     * @param event
     * @throws IOException
     * When user presses the "Guest" Button, it switches to GuestHomeScene.fxml.
     * Loads GuestHomeSceneController object.
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
     *
     * @param event
     * @throws IOException
     * Method switches to AdminHomeScene.fxml.
     * Loads AdminHomeSceneController object.
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
     *
     * @param event
     * @throws IOException
     * Method switches to LoginScene.fxml.
     * Loads LoginSceneController object.
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
     *
     * @param event
     * @throws IOException
     * Method switches to AdminLoginScene.fxml.
     * Loads LoginSceneController object.
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
