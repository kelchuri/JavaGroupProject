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
public interface QuestionDAO {
    
    /**
     * this method adds a question object to the database
     * 
     * @param questions
     * @return 
     */
    public String addQuestion(ArrayList<Questions> questions);
       
    /**
     * this method updates a question object to the database
     * 
     * @param questions
     * @return 
     */ 
    public String updateQuestion(ArrayList<Questions> questions);
        
    /**
     * this method gets a question object to the database
     * 
     * @param quesDesc
     * @return 
     */
    public Questions getQuestion(String quesDesc);
    
}
