package com.cmu.quiz;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;

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

    @FXML
    private Button importCSV;

    public static void setStage(Stage takeQuizStage) {
        InstructorScreen.stage = takeQuizStage;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
    @FXML
    private void handleImportCSVButton() {
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
        } catch (Exception e) {
        }
    }
    
    @FXML
    private void handleProfileButton() throws IOException {
        System.out.println("Profile Button Called");

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/AdminProfile.fxml"));
        Pane cmdPane = (Pane) fxmlLoader.load();
        try {
            
            rightPane.getChildren().clear();
            rightPane.getChildren().add(cmdPane);
        } catch (Exception e) {
        }
    }
    
    @FXML
    private void addInstructorButton() throws IOException{
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
    private void viewDashboardButton() throws IOException{
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
    private void logOutButton() throws IOException{
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
    


    private void openFile(File file) {
        try {
           
            String line;
            Scanner input = new Scanner(file);
            while (input.hasNextLine()) {
                line = input.nextLine();
                System.out.println(line);
            }
        } catch (IOException ex) {
            Logger.getLogger(InstructorScreen.class.getName()).log(
                            Level.SEVERE, null, ex
                    );
        }
    }
}
