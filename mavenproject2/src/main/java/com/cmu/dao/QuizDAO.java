/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmu.dao;

import com.cmu.models.Questions;
import com.cmu.models.Quiz;
import com.cmu.models.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ayushjain
 */
public interface QuizDAO extends AutoCloseable{
    
    public ArrayList<Double> noOfCrrctQuesAsPerDiffLvlInstructor(int ins_id) throws Exception;

    public ArrayList<Questions> getQuizQuestion(int NoQ, int crs_id, String diff_lvl) throws Exception;
 
    public int studentQuiz(int stu_id) throws Throwable;
    
    public void addIntoDB(List<Quiz> quiz, User student, int course_id) throws Throwable;
}
