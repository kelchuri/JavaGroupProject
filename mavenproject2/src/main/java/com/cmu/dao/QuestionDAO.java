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
    public String addQuestion(ArrayList<Questions> questions);
    
    public String updateQuestion(ArrayList<Questions> questions);
    
    public Questions getQuestion(String quesDesc);
    
}
