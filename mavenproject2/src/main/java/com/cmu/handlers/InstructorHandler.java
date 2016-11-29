/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmu.handlers;

import com.cmu.dao.CourseDAO;
import com.cmu.dao.CourseDAOFactory;
import com.cmu.dao.UserDAO;
import com.cmu.dao.UserDAOFactory;
import com.cmu.models.Course;
import com.cmu.models.User;

/**
 *
 * @author kavya
 */
public class InstructorHandler {
    private final CourseDAOFactory courseDAOFactory = new CourseDAOFactory();
    private final CourseDAO courseDAO = courseDAOFactory.createCourseDAO();
    
    public Course getCourse(User user) throws Exception{
        return courseDAO.getCourse(user);
    }
}
