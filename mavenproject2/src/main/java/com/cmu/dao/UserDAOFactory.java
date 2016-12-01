/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmu.dao;

/**
 *
 * @author kavya
 */
public class UserDAOFactory {
    
    /**
     * this method returns the object of UserDAOJDBCImpl class
     * 
     * @return 
     */
    public UserDAO createUserDAO(){
        return new UserDAOJDBCImpl();
    } 
}
