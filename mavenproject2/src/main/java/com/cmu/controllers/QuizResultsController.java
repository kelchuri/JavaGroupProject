package com.cmu.controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author kavya
 */
public class QuizResultsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    PieChart studentTestResultPie;

     private static Stage stage;
 
 public static void setStage(Stage takeQuizStage) {
        QuizResultsController.stage = takeQuizStage;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
          
        studentTestResultPie.setTitle("Your Quiz Result");
        createStudentResultPieChart();
    }    
    
    @FXML
    public void backToStudentScreen() throws IOException{
        System.out.println("Return Button Called");
        
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/StudentScreen.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/studentscreen.css");
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        QuizResultsController.stage.setTitle("JavaFX and Maven");
        QuizResultsController.stage.setScene(scene);
        QuizResultsController.stage.show();
    }
    
    public void createStudentResultPieChart() {
        ObservableList<PieChart.Data> pieChartData
                = FXCollections.observableArrayList(
                        new PieChart.Data("Correct", 60),
                        new PieChart.Data("Incorrect", 25));

        studentTestResultPie.setData(pieChartData);

}
}
