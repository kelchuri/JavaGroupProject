/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmu.quiz;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author kavya
 */


public class LoginPageController implements Initializable {

    @FXML
    TextField userId;
    @FXML
    PasswordField password;
    @FXML
    Button loginId;
    @FXML
    TextField lnameId;
    @FXML
    TextField fnameId;
    @FXML
    TextField emailId;

    private MainApp application;

    public void setApp(MainApp application) {
        this.application = application;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    @FXML
    public void processLogin(ActionEvent event) throws IOException, Throwable {
        System.out.println(userId.getText());
        System.out.println(password.getText());
        int result = LPDB.checkCredentials(userId.getText(), password.getText());
        if (result == 1) {
            Stage takeQuizStage;
            takeQuizStage = ((Stage) userId.getScene().getWindow());
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/InstructorCSVUpload.fxml"));

            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/instructorcsvupload.css");

            takeQuizStage.setTitle("Choose the CSV FIle");
            takeQuizStage.setScene(scene);
            takeQuizStage.show();

        }
        if (result == 0) {
            System.out.println("No credentials entered");
            // 0 is returned if the username is incorrect or the password does not match.
        }
    }

    @FXML
    public void processSignup(ActionEvent event) {
        System.out.println(fnameId.getText());
        System.out.println(lnameId.getText());
        System.out.println(emailId.getText());
    }

}
