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

    public String addStudentQuiz() throws Exception;

    public String updateStudentQuiz() throws Exception;

    public String deleteStudentQuiz() throws Exception;

    public ArrayList<Integer> numberOfQuizTakenPerInstructor(int ins_id) throws Exception;

    public ArrayList<Double> passFailStudent(int stu_id) throws Throwable;

    public ArrayList<Double> avgScoreForInstructor(int ins_id) throws Exception;

    public ArrayList<Integer> numberOfQuizTakenPerStudent(int stu_id) throws Exception;

    public Double overallAvgMarksStudent(int stu_id) throws Exception;

    public ArrayList<Double> scoresByLODForStudent(String stu_id) throws Exception;

    public ArrayList<Double> averageScoreOfStudent(String stu_id) throws Exception;

    public ArrayList<Double> passFailInstructor(int ins_id) throws Throwable;

}
