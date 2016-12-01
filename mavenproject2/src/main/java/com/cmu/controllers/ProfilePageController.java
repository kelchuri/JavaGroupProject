/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmu.controllers;

import com.cmu.handlers.InstructorHandler;
import com.cmu.models.User;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author User
 */
public class ProfilePageController implements Initializable{
    
     private static User user;
    
    public static void setUser(User user){
        ProfilePageController.user = user;
    }
    
    @FXML
    private Label fname;
    
    @FXML
    private Label lname;
    
    @FXML
    private Label totalQuiz;
    
    @FXML
    private Label grade;
    
    private MainApp application;
    
    double studentDetailList;
    
    InstructorHandler instructorHandler = new InstructorHandler();
    
    public void setApp(MainApp application){
        this.application = application;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       
          fname.setText(ProfilePageController.user.getFirstName());
          lname.setText(ProfilePageController.user.getLastName());
         try {
             
             studentDetailList = instructorHandler.getStudentMarksDetail(ProfilePageController.user);
            if (!Double.isNaN(studentDetailList)){
               if(studentDetailList>=80){
                   grade.setText("A");
               }
               else if(studentDetailList < 80 && studentDetailList >= 60){
                   grade.setText("B");
               }
               else if(studentDetailList < 60 && studentDetailList >=40){
                   grade.setText("C");
               }
               else
                   grade.setText("F");
            }
                
            
             System.out.println("Stud Grade" + studentDetailList);
          //  studentDetailList.get(2)/studentDetailList(3)
            // grade.setText(Strin);
             totalQuiz.setText(String.valueOf(instructorHandler.getStudentQuizCount(ProfilePageController.user)));
         } catch (Throwable ex) {
             Logger.getLogger(ProfilePageController.class.getName()).log(Level.SEVERE, null, ex);
         }
       
    }
    
    @FXML
    public void processLogin(ActionEvent event) {
        
             
    }
    
}
