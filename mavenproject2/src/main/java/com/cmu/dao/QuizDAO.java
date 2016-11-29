/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmu.dao;

import com.cmu.models.Questions;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 *
 * @author Ayushjain
 */
public interface QuizDAO extends AutoCloseable{
    
    public TreeMap<Integer, Integer[]> noOfCrrctQuesAsPerDiffLvlInstructor(String ins_id) throws Exception;

    public ArrayList<Questions> getQuizQuestion(int NoQ, int crs_id, String diff_lvl) throws Exception;
    
}
