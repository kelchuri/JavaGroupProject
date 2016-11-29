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

    public void add(Course c) throws Exception;

    public void update(Course c) throws Exception;
    
    public Course getCourse(User user) throws Exception;
    
    public Course getCourseById(int id) throws Exception;
    
    

}
