package com.cmu.controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.cmu.dao.QuizDAO;
import com.cmu.dao.QuizDAOFactory;
import com.cmu.dao.StudentQuizDAO;
import com.cmu.dao.StudentQuizDAOFactory;
import com.cmu.models.User;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author kavya
 */
public class DashboardInstructorController implements Initializable {

    @FXML
    BarChart chart1;

    @FXML
    BarChart chart2;

    @FXML
    PieChart pie1;

    @FXML
    PieChart pie2;

    @FXML
    Button chart1btn;
    @FXML
    Button chart2btn;
    @FXML
    Button pie1btn;
    @FXML
    Button pie2btn;

    static Stage stage;

    private static User user;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        chart1.setTitle("Number of Tests Taken by Students");
        StudentQuizDAOFactory sqdaof = new StudentQuizDAOFactory();
        StudentQuizDAO sqdao = sqdaof.createStudentQuizDAO();
        ArrayList<Integer> noOfQuiz = new ArrayList<>();
        ArrayList<Double> avgScore = new ArrayList<>();
        ArrayList<Double> noByDifficultyLevel = new ArrayList<>();

        int userId = user.getUserId();

        try {
            noOfQuiz = sqdao.numberOfQuizTakenPerInstructor(userId);
        } catch (Exception ex) {
            Logger.getLogger(DashboardInstructorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        chart1.getData().add(createChart(noOfQuiz));

        try {
            avgScore = sqdao.avgScoreForInstructor(userId);
        } catch (Exception ex) {
            Logger.getLogger(DashboardInstructorController.class.getName()).log(Level.SEVERE, null, ex);
        }

        chart2.setTitle("Average Scores of Students");
        chart2.getData().add(createChart(avgScore));

        QuizDAOFactory qdaof = new QuizDAOFactory();
        QuizDAO aO = qdaof.createQuizDAO();
        try {
            noByDifficultyLevel = aO.noOfCrrctQuesAsPerDiffLvlInstructor(userId);
        } catch (Exception ex) {
            Logger.getLogger(DashboardInstructorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        pie1.setTitle("Scores by level of Difficulty");
        createPieChart1(noByDifficultyLevel);

        pie2.setTitle("Students Passed and Failed");
        createPieChart2();

        // TODO
    }

    public void createPieChart1(ArrayList x) {
        ObservableList<PieChart.Data> pieChartData
                = FXCollections.observableArrayList(
                        new PieChart.Data("Easy", (double) (Number) x.get(0)),
                        new PieChart.Data("Medium", (double) (Number) x.get(1)),
                        new PieChart.Data("Hard", (double) (Number) x.get(2)));

        pie1.setData(pieChartData);

    }

    public void createPieChart2() {
        ObservableList<PieChart.Data> pieChartData
                = FXCollections.observableArrayList(
                        new PieChart.Data("Pass", 60),
                        new PieChart.Data("Fail", 15));

        pie2.setData(pieChartData);

    }

    public XYChart.Series<String, Number> createChart(ArrayList x) {
        final String[] years = {"Last month", "Last quarter", "Last year"};
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        yAxis.setTickLabelFormatter(new NumberAxis.DefaultFormatter(yAxis, "", null));
        final BarChart<String, Number> bc = new BarChart<String, Number>(xAxis, yAxis);

        //bc.setTitle("Number of Tests Taken by Students");
        xAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList(years)));

        XYChart.Series<String, Number> series1 = new XYChart.Series<String, Number>();

        series1.getData().add(new XYChart.Data<String, Number>(years[0], (Number) x.get(0)));
        series1.getData().add(new XYChart.Data<String, Number>(years[1], (Number) x.get(1)));
        series1.getData().add(new XYChart.Data<String, Number>(years[2], (Number) x.get(2)));

        bc.getData().add(series1);

        return series1;
    }

    public static void setStage(Stage takeQuizStage) {
        DashboardInstructorController.stage = takeQuizStage;

    }

    @FXML
    public void print1(ActionEvent event) {

        FileChooser chooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF file(*.pdf)", "   *.pdf ");
        chooser.getExtensionFilters().add(extFilter);
        File file = chooser.showSaveDialog(stage);
        try {

            WritableImage img = chart1.snapshot(null, null);

            ImageData imgData = ImageDataFactory.create(SwingFXUtils.fromFXImage(img, null), null);

            com.itextpdf.layout.element.Image pdfImg = new com.itextpdf.layout.element.Image(imgData);

            System.out.println("Entered 3 ");
            PdfWriter writer = new PdfWriter(new FileOutputStream(file));
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document doc = new Document(pdfDoc);
            doc.add(new Paragraph("Hello world, " + "this is a test pdf file."));
            doc.add(pdfImg);
            doc.close();
        } catch (Exception exc) {
            exc.printStackTrace();
        }

    }

    @FXML
    public void print2(ActionEvent event) {

        FileChooser chooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF file(*.pdf)", "   *.pdf ");
        chooser.getExtensionFilters().add(extFilter);
        File file = chooser.showSaveDialog(stage);
        try {

            WritableImage img = chart2.snapshot(null, null);

            ImageData imgData = ImageDataFactory.create(SwingFXUtils.fromFXImage(img, null), null);

            com.itextpdf.layout.element.Image pdfImg = new com.itextpdf.layout.element.Image(imgData);

            System.out.println("Entered 3 ");
            PdfWriter writer = new PdfWriter(new FileOutputStream(file));
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document doc = new Document(pdfDoc);
            doc.add(new Paragraph("Hello world, " + "this is a test pdf file."));
            doc.add(pdfImg);
            doc.close();
        } catch (Exception exc) {
            exc.printStackTrace();
        }

    }

    @FXML
    public void print3(ActionEvent event) {

        FileChooser chooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF file(*.pdf)", "   *.pdf ");
        chooser.getExtensionFilters().add(extFilter);
        File file = chooser.showSaveDialog(stage);
        try {

            WritableImage img = pie1.snapshot(null, null);

            ImageData imgData = ImageDataFactory.create(SwingFXUtils.fromFXImage(img, null), null);

            com.itextpdf.layout.element.Image pdfImg = new com.itextpdf.layout.element.Image(imgData);

            System.out.println("Entered 3 ");
            PdfWriter writer = new PdfWriter(new FileOutputStream(file));
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document doc = new Document(pdfDoc);
            doc.add(new Paragraph("Hello world, " + "this is a test pdf file."));
            doc.add(pdfImg);
            doc.close();
        } catch (Exception exc) {
            exc.printStackTrace();
        }

    }

    @FXML
    public void print4(ActionEvent event) {

        FileChooser chooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF file(*.pdf)", "   *.pdf ");
        chooser.getExtensionFilters().add(extFilter);
        File file = chooser.showSaveDialog(stage);
        try {

            WritableImage img = pie2.snapshot(null, null);

            ImageData imgData = ImageDataFactory.create(SwingFXUtils.fromFXImage(img, null), null);

            com.itextpdf.layout.element.Image pdfImg = new com.itextpdf.layout.element.Image(imgData);

            System.out.println("Entered 3 ");
            PdfWriter writer = new PdfWriter(new FileOutputStream(file));
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document doc = new Document(pdfDoc);
            doc.add(new Paragraph("Hello world, " + "this is a test pdf file."));
            doc.add(pdfImg);
            doc.close();
        } catch (Exception exc) {
            exc.printStackTrace();
        }

    }

    public static void setUser(User user) {
        DashboardInstructorController.user = user;

    }
}
