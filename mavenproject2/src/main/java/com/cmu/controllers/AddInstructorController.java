package com.cmu.controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.cmu.handlers.Login_Page_Database_Controller;
import com.cmu.models.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author kavya
 */
public class AddInstructorController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private ComboBox courseIdIns;
    
    @FXML
    private TextField newuseremail;
    
    @FXML
    private PasswordField newuserpassword;
    
    
    private Login_Page_Database_Controller login_Page_Database_Controller = new Login_Page_Database_Controller();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void addInstructor(ActionEvent event) throws Exception{
        int courseid = getCourseId();
        User instructor = new User("", "", newuserpassword.getText(), newuseremail.getText(), "instructor");
        login_Page_Database_Controller.addNewInstructor(instructor, courseid);
        
    }
    
    private int getCourseId() {
        int courseID = 0;
        String course = courseIdIns.getSelectionModel().getSelectedItem().toString();
        if(course.equalsIgnoreCase("history")){
            courseID = 3;
        } else if(course.equalsIgnoreCase("General Knowledge")) {
            System.out.println("In the course comparision");
            courseID = 2;
        }
        else if(course.equalsIgnoreCase("Java")) {
            System.out.println("In the course comparision");
            courseID = 1;
        }
        return courseID;
    }
}
