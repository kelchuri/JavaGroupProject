package com.cmu.quiz;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author kavya
 */
public class QuizController implements Initializable {

    /**
     * Initializes the controller class.
     */
       private static Stage stage;
 
 public static void setStage(Stage takeQuizStage) {
        QuizController.stage = takeQuizStage;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    @FXML
    public void submitAndViewResults() throws IOException {
        System.out.println("Submit Result Called");
        QuizResultsController.setStage(QuizController.stage);
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/QuizResults.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/quizresults.css");
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        QuizController.stage.setTitle("JavaFX and Maven");
        QuizController.stage.setScene(scene);
        QuizController.stage.show();
    }
    
    
    
}
