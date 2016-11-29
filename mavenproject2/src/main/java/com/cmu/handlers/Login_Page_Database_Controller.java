/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmu.handlers;

import com.cmu.dao.CourseDAO;
import com.cmu.dao.CourseDAOFactory;
import com.cmu.models.User;
import com.cmu.dao.UserDAO;
import com.cmu.dao.UserDAOFactory;
import com.cmu.models.Course;

/**
 *
 * @author b2
 */
public class Login_Page_Database_Controller {

    private final UserDAOFactory userDAOFactory = new UserDAOFactory();
    private final UserDAO userDAO = userDAOFactory.createUserDAO();
    
    private final CourseDAOFactory courseDAOFactory = new CourseDAOFactory();
    private final CourseDAO courseDAO = courseDAOFactory.createCourseDAO();

    public User checkCredentials(String userid, String password) throws Throwable {
        int check = 0;
        String type = "";
        User userExists = userDAO.checkUserExists(userid, password);
        try{
           if (userExists.getUserId()!= 0) {
            type = userDAO.checkUserType(userid);

            System.out.println(check);

        }  
        } catch(Exception e) {
            return null;
        }
        
        return userExists;
    }
    
    public void addANewUser(User user) throws Exception{
        userDAO.add(user);
    }
    
    public void addNewInstructor(User user, int courseid) throws Exception{
        User instructor = userDAO.addInstructor(user);
        Course course = new Course(courseid,"", instructor.getUserId());
        courseDAO.add(course);
    }

}
