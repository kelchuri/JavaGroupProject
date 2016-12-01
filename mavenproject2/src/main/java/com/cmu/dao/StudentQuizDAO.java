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
public interface StudentQuizDAO extends AutoCloseable {

    /**
     * this method adds the student quiz to the database
     * 
     * @return 
     * @throws java.lang.Exception 
     */
    public String addStudentQuiz() throws Exception;

    /**
     * this method updates the student quiz record in the database
     * 
     * @return 
     * @throws java.lang.Exception 
     */
    public String updateStudentQuiz() throws Exception;

    /**
     * this method deletes the student quiz records
     * 
     * @return 
     * @throws java.lang.Exception 
     */
    public String deleteStudentQuiz() throws Exception;

    /**
     * this method gets the number of quiz taken by the students in a particular
     * instructor in last month, quarter and year
     *
     * @param ins_id
     * @return
     * @throws java.lang.Exception
     */
    public ArrayList<Integer> numberOfQuizTakenPerInstructor(int ins_id) throws Exception;

    /**
     * this method gets the number of passed and failed test by the student
     *
     * @param stu_id
     * @return
     * @throws java.lang.Throwable
     */
    public ArrayList<Double> passFailStudent(int stu_id) throws Throwable;

    /**
     * this method gets the list of average scores of students under the
     * instructor for last month, quarter and year
     *
     * @param ins_id
     * @return
     * @throws java.lang.Exception
     */
    public ArrayList<Double> avgScoreForInstructor(int ins_id) throws Exception;

    /**
     * this method gets number of quiz taken by the student in last month, 
     * quarter and year
     *
     * @param stu_id
     * @return
     * @throws java.lang.Exception
     */
    public ArrayList<Integer> numberOfQuizTakenPerStudent(int stu_id) throws Exception;

    /**
     * this method gets the overall average score of a student in all the quiz taken
     *
     * @param stu_id
     * @return
     * @throws java.lang.Exception
     */
    public double overallAvgMarksStudent(int stu_id) throws Exception;

    /**
     * this method return the list of scores by the level of difficulty for the student
     *
     * @param stu_id
     * @return
     * @throws java.lang.Exception
     */
    public ArrayList<Double> scoresByLODForStudent(int stu_id) throws Exception;

    /**
     * this method gets the list of average score of student in last month, 
     * quarter and year
     *
     * @param stu_id
     * @return
     * @throws java.lang.Exception
     */
    public ArrayList<Double> averageScoreOfStudent(int stu_id) throws Exception;

    /**
     * this method return the list of pass and fail student in a particular course under an instructor
     *
     * @param ins_id
     * @return
     * @throws java.lang.Throwable
     */
    public ArrayList<Double> passFailInstructor(int ins_id) throws Throwable;
  
    /**
     * this method returns the number of student in a particular course
     *
     * @param crs_id
     * @return
     * @throws java.lang.Throwable
     */  
    public int studentCountByCourse(int crs_id) throws Throwable;

}
