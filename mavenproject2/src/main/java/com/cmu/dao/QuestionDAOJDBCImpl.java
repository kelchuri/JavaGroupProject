/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmu.dao;

import com.cmu.models.Questions;
import java.util.ArrayList;

/**
 *
 * @author Ayushjain
 */
public class QuestionDAOJDBCImpl implements QuestionDAO{
   
    /**
     * this method adds a question object to the database
     * 
     * @param questions
     * @return 
     */
    @Override
    public String addQuestion(ArrayList<Questions> questions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
       
    /**
     * this method updates a question object to the database
     * 
     * @param questions
     * @return 
     */ 
    @Override
    public String updateQuestion(ArrayList<Questions> questions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
    /**
     * this method gets a question object to the database
     * 
     * @param quesDesc
     * @return 
     */
    @Override
    public Questions getQuestion(String quesDesc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
