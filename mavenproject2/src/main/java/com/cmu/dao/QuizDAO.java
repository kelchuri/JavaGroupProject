/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmu.dao;

import java.util.ArrayList;

/**
 *
 * @author Ayushjain
 */
public interface QuizDAO extends AutoCloseable{
    
    public ArrayList<Integer> noOfCrrctQuesAsPerDiffLvlInstructor(String ins_id) throws Exception;
    
}
