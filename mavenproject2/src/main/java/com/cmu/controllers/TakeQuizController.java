/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmu.controllers;

import com.cmu.handlers.StudentHandler;
import com.cmu.models.Questions;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Urvashi
 */
public class TakeQuizController implements Initializable {

    @FXML
    private ComboBox quiz_level;
    @FXML
    private ComboBox noOfQue;
    @FXML
    private ComboBox quizTimer;
    @FXML
    private ComboBox course;

    private static Stage stage;

    private StudentHandler studentHandler = new StudentHandler();

    public static void setStage(Stage takeQuizStage) {
        TakeQuizController.stage = takeQuizStage;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        quiz_level.getItems().addAll(
                "Easy",
                "Medium",
                "Hard"
        );

        quiz_level.setCellFactory(
                new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> param) {
                final ListCell<String> cell = new ListCell<String>() {
                    {
                        super.setPrefWidth(100);
                    }

                    @Override
                    public void updateItem(String item,
                            boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item);
                        } else {
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        });

        noOfQue.getItems().addAll(
                "10",
                "20",
                "30"
        );

        noOfQue.setCellFactory(
                new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> param) {
                final ListCell<String> cell = new ListCell<String>() {
                    {
                        super.setPrefWidth(100);
                    }

                    @Override
                    public void updateItem(String item,
                            boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item);
                        } else {
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        });

        quizTimer.getItems().addAll(
                "On",
                "Off"
        );

        quizTimer.setCellFactory(
                new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> param) {
                final ListCell<String> cell = new ListCell<String>() {
                    {
                        super.setPrefWidth(100);
                    }

                    @Override
                    public void updateItem(String item,
                            boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item);
                        } else {
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        });

        course.getItems().addAll(
                "Java",
                "History",
                "General Knowledge"
        );

        course.setCellFactory(
                new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> param) {
                final ListCell<String> cell = new ListCell<String>() {
                    {
                        super.setPrefWidth(100);
                    }

                    @Override
                    public void updateItem(String item,
                            boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item);
                        } else {
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        });

    }

    @FXML
    private void TakeQuizButton() throws IOException, Exception {
        String quiz = getDifficultyLevel(quiz_level.getSelectionModel().getSelectedItem().toString());
        int no = Integer.parseInt(this.noOfQue.getSelectionModel().getSelectedItem().toString());
        String quizTime = this.quizTimer.getSelectionModel().getSelectedItem().toString();
        int course = getCourseId(this.course.getSelectionModel().getSelectedItem().toString());

        List<Questions> questions = studentHandler.getQuestions(quiz, no, course);
        printQuestions(questions);
        QuizController.setQuestions(questions);
        QuizInstructionController.setStage(TakeQuizController.stage);
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/QuizInstruction.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/quizinstruction.css");
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        TakeQuizController.stage.setTitle("JavaFX and Maven");
        TakeQuizController.stage.setScene(scene);
        TakeQuizController.stage.show();
    }

    private int getCourseId(String name) {
        int courseID = 0;
        if (name.equalsIgnoreCase("history")) {
            courseID = 3;
        } else if (name.equalsIgnoreCase("General Knowledge")) {
            System.out.println("In the course comparision");
            courseID = 2;
        } else if (name.equalsIgnoreCase("Java")) {
            System.out.println("In the course comparision");
            courseID = 1;
        }
        return courseID;
    }
    
    private String getDifficultyLevel(String name) {
        String courseID = "";
        if (name.equalsIgnoreCase("easy")) {
            courseID = "E";
        } else if (name.equalsIgnoreCase("medium")) {
            System.out.println("In the course comparision");
            courseID = "M";
        } else if (name.equalsIgnoreCase("Hard")) {
            System.out.println("In the course comparision");
            courseID = "H";
        }
        return courseID;
    }

    private void printQuestions(List questions) {
        for (Object question : questions) {
            System.out.println(((Questions) question).getQues_type());
        }
        System.out.println(questions.size());
    }

}
