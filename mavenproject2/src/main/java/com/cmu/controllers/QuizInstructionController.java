package com.cmu.controllers;

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
 * This is the controller for QuizInstruction FXML file.
 */
public class QuizInstructionController implements Initializable {

    /**
     * Initializes the controller class.
     * 
     */
    private static Stage stage;

    /**
     *
     * @param takeQuizStage
     * This method sets the stage in this controller
     */
    public static void setStage(Stage takeQuizStage) {
        QuizInstructionController.stage = takeQuizStage;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    /**
     *
     * @throws IOException
     * This method starts the quiz
     */
    @FXML
    public void startQuizButtonHandle() throws IOException {
        System.out.println("AddInst Button Called");
        QuizController.setStage(QuizInstructionController.stage);
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Quiz.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/quiz.css");
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        QuizInstructionController.stage.setTitle("JavaFX and Maven");
        QuizInstructionController.stage.setScene(scene);
        QuizInstructionController.stage.show();
    }


}
