package com.cmu.controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.cmu.models.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author kavya
 * This is the controller for StudentScreen FXML file.
 */
public class StudentScreenController implements Initializable {

    @FXML
    private AnchorPane rightPaneStudent;

    @FXML
    private Label loggedInUser;

    private static Stage stage;

    private static User user;

    /**
     * Initializes the controller class.
     * @param user
     * This method sets the user in this controller
     */
    public static void setUser(User user) {
        StudentScreenController.user = user;
    }

    /**
     *
     * @param takeQuizStage
     * This method sets the stage in this controller
     */
    public static void setStage(Stage takeQuizStage) {
        StudentScreenController.stage = takeQuizStage;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/DashboardStudent.fxml"));
        try {
            Pane cmdPane = (Pane) fxmlLoader.load();
            loggedInUser.setText(user.getFirstName());
            rightPaneStudent.getChildren().clear();
            rightPaneStudent.getChildren().add(cmdPane);
        } catch (Exception e) {

        }
    }

    @FXML
    private void handleProfileStudentButton() throws IOException {
        System.out.println("Profile Button Called");
        ProfilePageController.setUser(user);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Profile.fxml"));
        Pane cmdPane = (Pane) fxmlLoader.load();
        try {

            rightPaneStudent.getChildren().clear();
            rightPaneStudent.getChildren().add(cmdPane);
        } catch (Exception e) {
        }
    }

    @FXML
    private void handleTakeQuizButton() throws IOException {
        System.out.println("AddInst Button Called");
        TakeQuizController.setStage(StudentScreenController.stage);
        TakeQuizController.setUser(StudentScreenController.user);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/TakeQuiz.fxml"));
        Pane cmdPane = (Pane) fxmlLoader.load();
        try {

            rightPaneStudent.getChildren().clear();
            rightPaneStudent.getChildren().add(cmdPane);
        } catch (Exception e) {
        }
    }

    @FXML
    private void viewDashboardButtonStudent() throws IOException {
        System.out.println("AddInst Button Called");

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/DashboardStudent.fxml"));
        Pane cmdPane = (Pane) fxmlLoader.load();
        try {

            rightPaneStudent.getChildren().clear();
            rightPaneStudent.getChildren().add(cmdPane);
        } catch (Exception e) {
        }
    }

    @FXML
    private void logOutStudentButton() throws IOException {
        System.out.println("AddInst Button Called");

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/LoginPage.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/loginpage.css");
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        StudentScreenController.stage.setTitle("JavaFX and Maven");
        StudentScreenController.stage.setScene(scene);
        StudentScreenController.stage.show();
    }

}
