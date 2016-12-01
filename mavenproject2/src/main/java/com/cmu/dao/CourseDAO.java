/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmu.dao;

import com.cmu.models.Course;
import com.cmu.models.User;

/**
 *
 * @author Ayushjain
 */
public interface CourseDAO {

    /**
     * this method adds a course object to the database
     * 
     * @param c
     * @throws java.lang.Exception
     */
    public void add(Course c) throws Exception;

    /**
     * this method updates a course object to the database
     * 
     * @param c
     * @throws java.lang.Exception
     */
    public void update(Course c) throws Exception;
    
    /**
     * this method gets a course object by user id from the database
     * 
     * @param user
     * @return 
     * @throws java.lang.Exception
     */
    public Course getCourse(User user) throws Exception;
    
    /**
     * this method gets a course object by course id from the database
     * 
     * @param id
     * @return 
     * @throws java.lang.Exception
     */
    public Course getCourseById(int id) throws Exception;
    
    

}
