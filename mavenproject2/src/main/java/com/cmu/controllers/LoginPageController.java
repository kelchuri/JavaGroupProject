/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmu.controllers;

import com.cmu.handlers.Login_Page_Database_Controller;
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
    
    private Login_Page_Database_Controller login_Page_Database_Controller;

    private MainApp application;

    public void setApp(MainApp application) {
        this.application = application;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      login_Page_Database_Controller = new Login_Page_Database_Controller();
   

    }

    @FXML
    public void processLogin(ActionEvent event) throws IOException, Throwable {
        System.out.println(userId.getText());
        System.out.println(password.getText());
        String result = login_Page_Database_Controller.checkCredentials(userId.getText(), password.getText());
        if (result.equals("student")) {
            Stage takeQuizStage;
            takeQuizStage = ((Stage) userId.getScene().getWindow());
                        StudentScreenController.setStage(takeQuizStage);

            Parent root = FXMLLoader.load(getClass().getResource("/fxml/StudentScreen.fxml"));

            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/instructorcsvupload.css");

            takeQuizStage.setTitle("Student Screen");
            takeQuizStage.setScene(scene);
            takeQuizStage.show();

        } else if (result.equals("instructor")) {
            Stage takeQuizStage;
            takeQuizStage = ((Stage) userId.getScene().getWindow());
            InstructorScreen.setStage(takeQuizStage);
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/InstructorScreen.fxml"));

            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/instructorcsvupload.css");

            takeQuizStage.setTitle("Instructor Screen");
            takeQuizStage.setScene(scene);
            takeQuizStage.show();

        }
        else {
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