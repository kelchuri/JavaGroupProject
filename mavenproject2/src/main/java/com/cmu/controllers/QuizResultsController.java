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
import javafx.animation.TranslateTransitionBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

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

    @FXML
    Label noOfCorrectAnsLabel;

    @FXML
    Label noOfQueLabel;

    @FXML
    Label percentCorrectLabel;

    @FXML
    Label totalMarksLabel;

    @FXML
    Label interactivePie;

    boolean flag;
    double start;

    static final Duration ANIMATION_DURATION = new Duration(500);
    static final double ANIMATION_DISTANCE = 0.15;
    private double cos;
    private double sin;

    private static Stage stage;

    int incorrect;
    int correct;
    int totalQue;
    double percentCorrect;

    QuizController quizController = new QuizController();

    public static void setStage(Stage takeQuizStage) {
        QuizResultsController.stage = takeQuizStage;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        incorrect = quizController.getIncorrect();
        System.out.println("incorrect:" + incorrect);

        correct = QuizController.correct;
        System.out.println("correct:" + correct);

        totalQue = correct + incorrect;

        noOfCorrectAnsLabel.setText(Integer.toString(correct));
        noOfQueLabel.setText(Integer.toString(totalQue));
        percentCorrect = ((float) correct / totalQue) * 100;
        percentCorrect = Double.valueOf(String.format("%.2f", percentCorrect));
        percentCorrectLabel.setText(Double.toString(percentCorrect));
        totalMarksLabel.setText(Integer.toString(correct * 3));
        studentTestResultPie.setTitle("Your Quiz Result");
        createStudentResultPieChart(correct, incorrect);

    }

    @FXML
    public void backToStudentScreen() throws IOException {
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

    public void createStudentResultPieChart(int correct, int incorrect) {
        ObservableList<PieChart.Data> pieChartData
                = FXCollections.observableArrayList(
                        new PieChart.Data("Correct", correct),
                        new PieChart.Data("Incorrect", incorrect));

        studentTestResultPie.setData(pieChartData);
        interactivePie();

    }

    public void interactivePie() {

        interactivePie.setTextFill(Color.DARKORANGE);
        interactivePie.setStyle("-fx-font: 24 arial;");

        studentTestResultPie.getData().stream().forEach((data) -> {
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent e) -> {
                flag = !flag;
                double val = ((double) data.getPieValue() / totalQue) * 100;
                val = Double.valueOf(String.format("%.2f", val));
                studentTestResultPie.setClockwise(flag);
                interactivePie.setTranslateX(e.getSceneX());
                interactivePie.setTranslateY(e.getSceneY());
                interactivePie.setText(val + "%");
            });
        });
    }
}
