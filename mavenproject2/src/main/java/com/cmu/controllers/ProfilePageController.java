/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmu.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 *
 * @author User
 */
public class ProfilePageController implements Initializable{
    
    @FXML
    TextField lname;
    @FXML
    TextField fname;
    @FXML
    TextField totalquiz;
    @FXML
    TextField avggrade;
    
    private MainApp application;
    
    public void setApp(MainApp application){
        this.application = application;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       
    lname.setText("Shree");
    fname.setText("Nidhi");
    totalquiz.setText("10");
    avggrade.setText("A");
    
    }
    
    @FXML
    public void processLogin(ActionEvent event) {
        
             
    }
    
}
