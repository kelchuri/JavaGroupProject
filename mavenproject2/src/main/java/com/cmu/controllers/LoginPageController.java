/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmu.controllers;

import com.cmu.models.User;
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
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
    @FXML
    TextField password1;
    @FXML
    TextField password2;
    @FXML
    ImageView incorrect;
    @FXML
    ImageView correct;
    @FXML
    Label errorId;

    private Login_Page_Database_Controller login_Page_Database_Controller;

    private MainApp application;
    
    public String userName;

    public void setApp(MainApp application) {
        this.application = application;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        login_Page_Database_Controller = new Login_Page_Database_Controller();
        correct.setVisible(false);
        incorrect.setVisible(false);
        errorId.setVisible(false);
       
    }

    @FXML
    public void processLogin(ActionEvent event) throws IOException, Throwable {
        if (validateField()) {
            errorId.setVisible(false);
            System.out.println(userId.getText());
            System.out.println(password.getText());
            User result = login_Page_Database_Controller.checkCredentials(userId.getText(), password.getText());
            String userType = result.getUserType();
//            userName = result.getFirstName() + " " + result.getLastName();
            InstructorScreen.setUser(result);
            if (userType.equals("student")) {
                showStudentScreen();

            } else if (userType.equals("instructor")) {
                Stage takeQuizStage;
                takeQuizStage = ((Stage) userId.getScene().getWindow());
                InstructorScreen.setStage(takeQuizStage);
                Parent root = FXMLLoader.load(getClass().getResource("/fxml/InstructorScreen.fxml"));

                Scene scene = new Scene(root);
                scene.getStylesheets().add("/styles/instructorcsvupload.css");

                takeQuizStage.setTitle("Instructor Screen");
                takeQuizStage.setScene(scene);
                takeQuizStage.show();

            } else {
                errorId.setText("Invalid Credentials");
                errorId.setVisible(true);
                // 0 is returned if the username is incorrect or the password does not match.
            }
        } else {
            errorId.setVisible(true);
        }

    }

    private void showStudentScreen() throws IOException {
        Stage takeQuizStage;
        takeQuizStage = ((Stage) userId.getScene().getWindow());
        StudentScreenController.setStage(takeQuizStage);

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/StudentScreen.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/instructorcsvupload.css");

        takeQuizStage.setTitle("Student Screen");
        takeQuizStage.setScene(scene);
        takeQuizStage.show();
    }

    private boolean validateField() {
        if (userId.getText().equals("")) {
            System.out.println("in");
            userId.setStyle("-fx-border-color:red;");
            return false;

        }
        if (password.getText().equals("")) {
            System.out.println("in");
            password.setStyle("-fx-border-color:red;");
            return false;
        }
        return true;
    }

    private boolean validateFieldsSignup() {
        if (fnameId.getText().equals("")) {

            fnameId.setStyle("-fx-border-color:red;");
            return false;

        }
        if (lnameId.getText().equals("")) {
            lnameId.setStyle("-fx-border-color:red;");
            return false;
        }
        if (emailId.getText().equals("")) {
            emailId.setStyle("-fx-border-color:red;");
            return false;
        }
        if (!emailId.getText().matches("\\S+@\\S+")) {
            System.out.println(emailId.getText().matches("\\S+@\\S+"));
            errorId.setText("Email is not valid.");
            errorId.setVisible(true);
            return false;
        }
        if (password1.getText().equals("")) {
            System.out.println("in");
            password1.setStyle("-fx-border-color:red;");
            return false;
        }
        if (password2.getText().equals("")) {
            System.out.println("in");
            password2.setStyle("-fx-border-color:red;");
            return false;
        }
        return true;
    }

    @FXML
    public void processSignup(ActionEvent event) throws Exception {

        errorId.setVisible(false);
        if (validateFieldsSignup() && password1.getText().equals(password2.getText())) {
            incorrect.setVisible(false);
            correct.setVisible(true);
            System.out.println("ready for signup");
            User student = new User(fnameId.getText(), lnameId.getText(), password1.getText(), emailId.getText(), "student");
            try {
                login_Page_Database_Controller.addANewUser(student);
                showStudentScreen();
            } catch (Exception e) {
                errorId.setText("There was an error processing your request. please try again later.");
                errorId.setVisible(true);
            }
        } else if (!password1.getText().equals(password2.getText())) {
            errorId.setVisible(false);
            incorrect.setVisible(true);
            correct.setVisible(false);
        } else {
            //errorId.setText("Please enter all the fields");
            errorId.setVisible(true);
        }
        //User student = new User();
    }

}
