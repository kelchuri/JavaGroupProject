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
    public void add(User user) throws Exception;
    
    public User addInstructor(User user) throws Exception;

    public void update(User user) throws Exception;

    public User findById(int id) throws Exception;
    
    public User checkUserExists(String userId, String password) throws Exception;
    
    public String checkUserType(String userId) throws Exception;

    public User[] getAllStudents() throws Exception;
    
    public User[] getAllInstructors() throws Exception;
}
