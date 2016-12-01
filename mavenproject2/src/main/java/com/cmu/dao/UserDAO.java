/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmu.dao;

import com.cmu.models.User;
/**
 *
 * @author kavya
 */
public interface UserDAO extends AutoCloseable {
            
    /**
     * 
     * this method adds an User record using an INSERT SQL command
     * 
     * @param user
     * @return 
     * @throws java.lang.Exception
     */
    public User add(User user) throws Exception;
            
    /**
     * 
     * this method is adds the instructor to the database
     * 
     * @param user
     * @return 
     * @throws java.lang.Exception
     */
    public User addInstructor(User user) throws Exception;
           
    /**
     * 
     * this method Update an User record with a SQL UPDATE command
     * 
     * @param user
     * @throws java.lang.Exception
     */ 
    public void update(User user) throws Exception;
            
    /**
     * 
     * this method Find an user record using this ID
     * 
     * @param id
     * @return 
     * @throws java.lang.Exception
     */  
    public User findById(int id) throws Exception;
                
    /**
     * 
     * this method checks if the given user id exists and the password entered is correct
     * 
     * @param userId
     * @param password
     * @return 
     * @throws java.lang.Exception
     */  
    public User checkUserExists(String userId, String password) throws Exception;
                
    /**
     * 
     * this method checks the user type for the given user id
     * 
     * @param userId
     * @return 
     * @throws java.lang.Exception
     */ 
    public String checkUserType(String userId) throws Exception;
                
    /**
     * 
     * this method gets all the students from the user table
     * 
     * @return 
     * @throws java.lang.Exception
     */ 
    public User[] getAllStudents() throws Exception;
                 
    /**
     * 
     * this method s all the instructors from user table
     * 
     * @return 
     * @throws java.lang.Exception
     */  
    public User[] getAllInstructors() throws Exception;
}
