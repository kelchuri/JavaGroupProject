package com.cmu.controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.cmu.models.Questions;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
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

    private static List<Questions> ques;

    final CheckBox[] cbs = new CheckBox[20];

    @FXML
    private TextArea question;

    @FXML
    private CheckBox option1;

    @FXML
    private CheckBox option2;

    @FXML
    private CheckBox option3;

    @FXML
    private CheckBox option4;

    @FXML
    private VBox multipleAnswers;
    
    @FXML
    private RadioButton option1m;

    @FXML
    private RadioButton option2m;

    @FXML
    private RadioButton option3m;

    @FXML
    private RadioButton option4m;

    @FXML
    private VBox multipleChoiceAnswers;
    
    @FXML
    private RadioButton true1;

    @FXML
    private RadioButton false1;

    @FXML
    private VBox trueFalse;
    
    @FXML
    private TextField fib;
    
    @FXML
    private Button next;
    
    private static int i=0;

    public static void setStage(Stage takeQuizStage) {
        QuizController.stage = takeQuizStage;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void setQuestions(List questions) {
        QuizController.ques = questions;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
            displayQuestion(i);
        
        // TODO
    }
    
    @FXML
    public void goToNext(){
        i = i+1;
        if(i< QuizController.ques.size()){
                   displayQuestion(i);
 
        } else {
            next.setDisable(true);
        }
    }

    public void displayQuestion(int i) {
        Questions questionStr = QuizController.ques.get(i);
        question.setText(questionStr.getQues_desc());
        System.out.println(questionStr.getQues_desc());
        if (questionStr.getQues_type().equals("MA")) {
            System.out.println("In MA");
            multipleAnswers.setVisible(true);
            multipleChoiceAnswers.setVisible(false);
            fib.setVisible(false);
            trueFalse.setVisible(false);
            
        } else if (questionStr.getQues_type().equals("MC")) {
            System.out.println("In MC");
            multipleAnswers.setVisible(false);
            multipleChoiceAnswers.setVisible(true);
            fib.setVisible(false);
            trueFalse.setVisible(false);
            option1m.setText(questionStr.getOption1());
            option2m.setText(questionStr.getOption2());
            option3m.setText(questionStr.getOption3());
            option4m.setText(questionStr.getOption4());
            
        } else if (questionStr.getQues_type().equals("FIB")) {
            multipleAnswers.setVisible(false);
            multipleChoiceAnswers.setVisible(false);
            fib.setVisible(true);
            trueFalse.setVisible(false);
            
            System.out.println("In FIB");
        } else if (questionStr.getQues_type().equals("TF")) {
            multipleAnswers.setVisible(false);
            multipleChoiceAnswers.setVisible(false);
            fib.setVisible(false);
            trueFalse.setVisible(true);
            
            
        }
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
