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
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author kavya
 */
public class InstructorCSVUploadController implements Initializable {

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
        InstructorCSVUploadController.stage = takeQuizStage;
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

    private void openFile(File file) {
        try {
           
            String line;
            Scanner input = new Scanner(file);
            while (input.hasNextLine()) {
                line = input.nextLine();
                System.out.println(line);
            }
        } catch (IOException ex) {
            Logger.getLogger(
                    InstructorCSVUploadController.class.getName()).log(
                            Level.SEVERE, null, ex
                    );
        }
    }
}
