package com.cmu.controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import static com.cmu.controllers.DashboardInstructorController.stage;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.application.Application;
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
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author kavya
 */
public class DashboardStudentController implements Initializable {

    @FXML
    BarChart chart3;

    @FXML
    BarChart chart4;

    @FXML
    PieChart pie3;

    @FXML
    PieChart pie4;
    
     @FXML
    Button chart3btn;
    @FXML
    Button chart4btn;
    @FXML
    Button pie3btn;
    @FXML
    Button pie4btn;

    static Stage stage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        chart3.setTitle("Number of Tests Taken");
        chart3.getData().add(createChart());

        chart4.setTitle("Average Scores of Students");
        chart4.getData().add(createChart());

        pie3.setTitle("Scores by level of Difficulty");
        createPieChart1();

        pie4.setTitle("Number of Tests Passed and Failed");
        createPieChart2();
        // TODO
    }

    public void createPieChart1() {
        ObservableList<PieChart.Data> pieChartData
                = FXCollections.observableArrayList(
                        new PieChart.Data("Easy", 60),
                        new PieChart.Data("Medium", 25),
                        new PieChart.Data("Hard", 15));

        pie3.setData(pieChartData);

    }

    public void createPieChart2() {
        ObservableList<PieChart.Data> pieChartData
                = FXCollections.observableArrayList(
                        new PieChart.Data("Pass", 60),                   
                        new PieChart.Data("Fail", 15));
        pie4.setData(pieChartData);

    }

    public XYChart.Series<String, Number> createChart() {
        final String[] years = {"Last month", "Last quarter", "Last year"};
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        yAxis.setTickLabelFormatter(new NumberAxis.DefaultFormatter(yAxis, "", null));
        final BarChart<String, Number> bc = new BarChart<String, Number>(xAxis, yAxis);

        bc.setTitle("Number of Tests Taken by Students");

        xAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList(years)));

        XYChart.Series<String, Number> series1 = new XYChart.Series<String, Number>();

        series1.getData().add(new XYChart.Data<String, Number>(years[0], 100));
        series1.getData().add(new XYChart.Data<String, Number>(years[1], 300));
        series1.getData().add(new XYChart.Data<String, Number>(years[2], 1000));

        bc.getData().add(series1);

        return series1;
    }

    public static void setStage(Stage takeQuizStage) {
        DashboardInstructorController.stage = takeQuizStage;

    }

    @FXML
    public void print1(ActionEvent event) {

        FileChooser chooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF file(*.pdf)","   *.pdf ");
        chooser.getExtensionFilters().add(extFilter);
        File file = chooser.showSaveDialog(stage);
        try {

            WritableImage img = chart3.snapshot(null, null);

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

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF file(*.pdf)","   *.pdf ");
        chooser.getExtensionFilters().add(extFilter);
        File file = chooser.showSaveDialog(stage);
        try {

            WritableImage img = chart4.snapshot(null, null);

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

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF file(*.pdf)","   *.pdf ");
        chooser.getExtensionFilters().add(extFilter);
        File file = chooser.showSaveDialog(stage);
        try {

            WritableImage img = pie3.snapshot(null, null);

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

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF file(*.pdf)","   *.pdf ");
        chooser.getExtensionFilters().add(extFilter);
        File file = chooser.showSaveDialog(stage);
        try {

            WritableImage img = pie4.snapshot(null, null);

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
}
