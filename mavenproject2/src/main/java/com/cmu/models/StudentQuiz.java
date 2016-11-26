/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmu.Models;

/**
 *
 * @author Ayushjain
 */
public class StudentQuiz {
    private String stu_Id;
    private Integer quiz_id;
    private Integer marks;

    /**
     * 
     * this method gets the student id
     * 
     * @return 
     */
    public String getStu_Id() {
        return stu_Id;
    }

    /**
     * 
     * this method sets the student id
     * 
     * @param stu_Id
     */
    public void setStu_Id(String stu_Id) {
        this.stu_Id = stu_Id;
    }

    /**
     * 
     * this method gets the quiz id
     * 
     * @return 
     */
    public Integer getQuiz_id() {
        return quiz_id;
    }

    /**
     * 
     * this method sets the quiz id
     * 
     * @param quiz_id
     */
    public void setQuiz_id(Integer quiz_id) {
        this.quiz_id = quiz_id;
    }

    /**
     * 
     * this method gets the marks in the particular quiz
     * 
     * @return 
     */
    public Integer getMarks() {
        return marks;
    }

    /**
     * 
     * this method sets the marks in the particular quiz
     * 
     * @param marks
     */
    public void setMarks(Integer marks) {
        this.marks = marks;
    }
    
    
    
}
