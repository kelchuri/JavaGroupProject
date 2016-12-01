//
///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.cmu.controllers;
//
//import com.itextpdf.io.image.ImageData;
//import com.itextpdf.io.image.ImageDataFactory;
//import com.itextpdf.kernel.pdf.PdfDocument;
//import com.itextpdf.kernel.pdf.PdfWriter;
//import com.itextpdf.layout.Document;
//import com.itextpdf.layout.element.Paragraph;
//import java.io.File;
//import java.io.FileOutputStream;
//import javafx.embed.swing.SwingFXUtils;
//import javafx.scene.chart.BarChart;
//import javafx.scene.chart.CategoryAxis;
//import javafx.scene.chart.NumberAxis;
//import javafx.scene.image.WritableImage;
//import javafx.stage.FileChooser;
//import javafx.stage.Stage;
//
///**
// *
// * @author Nidhi
// */
//public class ReportToPDF {
//    
//    public void convert(){
//    
//     FileChooser chooser = new FileChooser();
//        Stage primaryStage = new Stage();
//        File file = chooser.showSaveDialog(primaryStage);
//        try {
//            CategoryAxis xAxis = new CategoryAxis();
//            NumberAxis yAxis = new NumberAxis();
//            BarChart<String, Number> chart = new BarChart<>(xAxis, yAxis);
//            WritableImage img = chart.snapshot(null, null);
//            ImageData imgData = ImageDataFactory.create(SwingFXUtils.fromFXImage(img, null), null);
//            com.itextpdf.layout.element.Image pdfImg = new com.itextpdf.layout.element.Image(imgData);
//
//            PdfWriter writer = new PdfWriter(new FileOutputStream(file));
//            PdfDocument pdfDoc = new PdfDocument(writer);
//            Document doc = new Document(pdfDoc);
//            doc.add(new Paragraph("Hello world, " + "this is a test pdf file."));
//            doc.add(pdfImg);
//            doc.close();
//        } catch (Exception exc) {
//            exc.printStackTrace();
//        }
//    }
//}
