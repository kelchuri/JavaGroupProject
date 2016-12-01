/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmu.dao;

/**
 *
 * @author Ayushjain
 */
public class CourseDAOFactory {
    
    /**
     * this methods returns the object of the CourseDAOJDBCImpl class
     * 
     */
        public CourseDAO createCourseDAO(){
        return new CourseDAOJDBCImpl();
    } 
}
