/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmu.quiz;

import java.awt.Color;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Urvashi
 */
public class TakeQuizController implements Initializable {

    @FXML
    ComboBox quiz_level;
    @FXML
    ComboBox noOfQue;
    @FXML
    ComboBox quizTimer;
    @FXML
    ComboBox course;

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
//                                    if (item.contains("High")) {
//                                        setTextFill();
//                                    }
//                                    else if (item.contains("Low")){
//                                        setTextFill(Color.GREEN);                                    }
//                                    else {
//                                        setTextFill(Color.BLACK);
//                                    }
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
//                                    if (item.contains("High")) {
//                                        setTextFill();
//                                    }
//                                    else if (item.contains("Low")){
//                                        setTextFill(Color.GREEN);                                    }
//                                    else {
//                                        setTextFill(Color.BLACK);
//                                    }
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
//                                    if (item.contains("High")) {
//                                        setTextFill();
//                                    }
//                                    else if (item.contains("Low")){
//                                        setTextFill(Color.GREEN);                                    }
//                                    else {
//                                        setTextFill(Color.BLACK);
//                                    }
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
                "Telecom"
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
//                                    if (item.contains("High")) {
//                                        setTextFill();
//                                    }
//                                    else if (item.contains("Low")){
//                                        setTextFill(Color.GREEN);                                    }
//                                    else {
//                                        setTextFill(Color.BLACK);
//                                    }
                                } else {
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                });
        
        

    }

}
