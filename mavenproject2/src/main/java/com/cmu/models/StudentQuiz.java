/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmu.models;

import java.util.Calendar;

/**
 *
 * @author Ayushjain
 */
public class StudentQuiz {

    private Integer stu_Id;
    private Integer quiz_id;
    private Integer marks;
    private Calendar date;
    private String crs_Id;

    /**
     *
     * this method gets the course id
     *
     * @return
     */
    public String getCrs_Id() {
        return crs_Id;
    }

    /**
     *
     * this method sets the course id
     *
     * @param crs_Id
     */
    public void setCrs_Id(String crs_Id) {
        this.crs_Id = crs_Id;
    }

    /**
     *
     * this method gets the date
     *
     * @return
     */
    public Calendar getDate() {
        return date;
    }

    /**
     *
     * this method sets the date
     *
     * @param date
     */
    public void setDate(Calendar date) {
        this.date = date;
    }

    /**
     *
     * this method gets the student id
     *
     * @return
     */
    public Integer getStu_Id() {
        return stu_Id;
    }

    /**
     *
     * this method sets the student id
     *
     * @param stu_Id
     */
    public void setStu_Id(Integer stu_Id) {
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
