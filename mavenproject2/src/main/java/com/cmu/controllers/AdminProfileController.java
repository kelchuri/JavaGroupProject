package com.cmu.controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.cmu.handlers.InstructorHandler;
import com.cmu.models.Course;
import com.cmu.models.User;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author kavya
 */
public class AdminProfileController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private static User user;

    private Course course;

    public static void setUser(User user) {
        AdminProfileController.user = user;
    }

    @FXML
    private Label fname;

    @FXML
    private Label lname;

    @FXML
    private Label totalStudents;

    @FXML
    private Label courseName;

    private InstructorHandler instructorHandler = new InstructorHandler();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            fname.setText(AdminProfileController.user.getFirstName());
            lname.setText(AdminProfileController.user.getLastName());

            course = instructorHandler.getCourse(AdminProfileController.user);

//            System.out.println(instructorHandler.studentCountByCourse(AdminProfileController.course).getCrs_id()));
            totalStudents.setText(String.valueOf(instructorHandler.getStudentCountByCourse(course)));

            courseName.setText(course.getCrs_name());
        } catch (Exception ex) {
            Logger.getLogger(AdminProfileController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Throwable ex) {
            Logger.getLogger(AdminProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
