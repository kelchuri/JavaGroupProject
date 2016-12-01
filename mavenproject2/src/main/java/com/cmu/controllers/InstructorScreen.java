package com.cmu.controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.cmu.handlers.TextToDatabaseHandler;
import com.cmu.models.User;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author kavya
 */
public class InstructorScreen implements Initializable {

    private final Desktop desktop = Desktop.getDesktop();
    /**
     * Initializes the controller class.
     */

    @FXML
    private AnchorPane pane;

    @FXML
    private AnchorPane rightPane;

    @FXML
    private Button importButton;

    @FXML
    private SplitPane inputPane;

    private static Stage stage;

    private static User user;

    @FXML
    private Label loggedInUser;

    @FXML
    private Label nameId;

    @FXML
    private Button importCSV;
    @FXML
    private TextArea csvtext;

    @FXML
    private ComboBox courseId;

    private boolean first = true;

    private String course;
    
    @FXML
    private Label message;

    private TextToDatabaseHandler textToDatabaseHandler = new TextToDatabaseHandler();

    public static void setStage(Stage takeQuizStage) {
        InstructorScreen.stage = takeQuizStage;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void setUser(User user) {
        InstructorScreen.user = user;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DashboardInstructorController.setUser(InstructorScreen.user);
//        loggedInUser.setText(InstructorScreen.user.getFirstName());

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/DashboardInstructor.fxml"));
        try {
            Pane cmdPane = (Pane) fxmlLoader.load();
            rightPane.getChildren().clear();
            rightPane.getChildren().add(cmdPane);
        } catch (Exception e) {
        }
    }

    @FXML
    private void handleImportCSVButton() throws IOException, Throwable {
        final FileChooser fileChooser = new FileChooser();

        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            openFile(file);
        }
    }

    @FXML
    private void handleImportButton() throws IOException {
        System.out.println("Handle Button Called");

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/importCSVOnly.fxml"));
        Pane cmdPane = (Pane) fxmlLoader.load();
        try {
            
            rightPane.getChildren().clear();
            rightPane.getChildren().add(cmdPane);
            courseId.getSelectionModel().select("Java");

        } catch (Exception e) {
        }
    }

    @FXML
    private void handleProfileButton() throws IOException {
        System.out.println("Profile Button Called");
        AdminProfileController.setUser(InstructorScreen.user);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/AdminProfile.fxml"));
        Pane cmdPane = (Pane) fxmlLoader.load();
        try {

            rightPane.getChildren().clear();
            rightPane.getChildren().add(cmdPane);
        } catch (Exception e) {
        }
    }

    @FXML
    private void addInstructorButton() throws IOException {
        System.out.println("AddInst Button Called");

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/AddInstructor.fxml"));
        Pane cmdPane = (Pane) fxmlLoader.load();
        try {

            rightPane.getChildren().clear();
            rightPane.getChildren().add(cmdPane);
        } catch (Exception e) {
        }
    }

    @FXML
    private void viewDashboardButton() throws IOException {
        System.out.println("AddInst Button Called");

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/DashboardInstructor.fxml"));
        Pane cmdPane = (Pane) fxmlLoader.load();
        try {

            rightPane.getChildren().clear();
            rightPane.getChildren().add(cmdPane);
        } catch (Exception e) {
        }
    }

    @FXML
    private void logOutButton() throws IOException {
        System.out.println("AddInst Button Called");

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/LoginPage.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/loginpage.css");
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        InstructorScreen.stage.setTitle("JavaFX and Maven");
        InstructorScreen.stage.setScene(scene);
        InstructorScreen.stage.show();
    }

    private void openFile(File file) throws IOException, Throwable {
        int courseID = getCourseId();
        System.out.println(courseID);
        message.setVisible(true);
        if(textToDatabaseHandler.addData(file.getAbsolutePath(), courseID)) {
            message.setText("File imported Successfully");
            
        } else {
            message.setText("File vould not get imported");
        }

    }

    private int getCourseId() {
        int courseID = 0;
        course = courseId.getSelectionModel().getSelectedItem().toString();
        if (course.equalsIgnoreCase("history")) {
            courseID = 3;
        } else if (course.equalsIgnoreCase("General Knowledge")) {
            System.out.println("In the course comparision");
            courseID = 2;
        } else if (course.equalsIgnoreCase("Java")) {
            System.out.println("In the course comparision");
            courseID = 1;
        }
        return courseID;
    }
}
