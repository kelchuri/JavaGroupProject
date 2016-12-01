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
public interface QuizDAO extends AutoCloseable {

    /**
     * this method gets array list of number of correct question grouped by
     * difficulty level for the instructor dashboard
     *
     * @param ins_id
     * @return
     * @throws java.lang.Exception
     */
    public ArrayList<Double> noOfCrrctQuesAsPerDiffLvlInstructor(int ins_id) throws Exception;

    /**
     * this method gets array of question objects to the database
     *
     * @param NoQ
     * @param crs_id
     * @param diff_lvl
     * @return
     * @throws java.lang.Exception
     */
    public ArrayList<Questions> getQuizQuestion(int NoQ, int crs_id, String diff_lvl) throws Exception;
       
    /**
     * this method returns the number of quiz taken by the student
     * 
     * @param stu_id
     * @return 
     * @throws java.lang.Exception 
     */
    public int studentQuiz(int stu_id) throws Throwable;
       
    /**
     * this method adds all the details of the quiz taken by the student
     * 
     * @param quiz
     * @param student
     * @param course_id
     * @throws java.lang.Exception 
     */
    public void addIntoDB(List<Quiz> quiz, User student, int course_id) throws Throwable;
}
