/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmu.dao;

import com.cmu.Models.User;
/**
 *
 * @author kavya
 */
public interface UserDAO extends AutoCloseable {
    public void add(User emp) throws Exception;

    public void update(User emp) throws Exception;

    public User findById(int id) throws Exception;
    
    public boolean checkUserExists(String userId, String password) throws Exception;
    
    public String checkUserType(String userId) throws Exception;

    public User[] getAllStudents() throws Exception;
    
    public User[] getAllInstructors() throws Exception;
}
