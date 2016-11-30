/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmu.models;

/**
 *
 * @author b2bey
 */
public class Course {

    Integer crs_id;
    String crs_name;
    Integer ins_id;

    /**
     * this a default constructor
     * 
     */
    public Course() {
    }
    
    /**
     * this constructor creates an object of course
     * 
     * @param crs_id
     * @param crs_name
     * @param ins_id
     */
    public Course(Integer crs_id, String crs_name, int ins_id) {
        this.crs_id = crs_id;
        this.crs_name = crs_name;
        this.ins_id = ins_id;
    }

    /**
     * this method is an overriding toString method
     * 
     * @return 
     */
    @Override
    public String toString() {
        return "Course{" + "crs_id=" + crs_id + ", crs_name=" + crs_name + ", ins_id=" + ins_id + '}';
    }

    /**
     * this method gets the course id
     * 
     * @return 
     */
    public Integer getCrs_id() {
        return crs_id;
    }

    /**
     * this method sets the course id
     * 
     * @param crs_id
     */
    public void setCrs_id(Integer crs_id) {
        this.crs_id = crs_id;
    }

    /**
     * this method gets the course name
     * 
     * @return 
     */
    public String getCrs_name() {
        return crs_name;
    }

    /**
     * this method sets the course name
     * 
     * @param crs_name
     */
    public void setCrs_name(String crs_name) {
        this.crs_name = crs_name;
    }

    /**
     * this method gets the instructor id
     * 
     * @return 
     */
    public int getIns_id() {
        return ins_id;
    }

    /**
     * this method sets the instructor id
     * 
     * @param ins_id
     */
    public void setIns_id(int ins_id) {
        this.ins_id = ins_id;
    }

}
