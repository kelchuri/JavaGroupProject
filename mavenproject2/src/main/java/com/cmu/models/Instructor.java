
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmu.models;

/**
 *
 * @author Ayushjain
 */
public class Instructor {

    private Integer ID;
    private String username;
    private String firstName;
    private String lastName;
    private String userType;
    private String password;
    
    /**
     * this method gets the ID
     * 
     * @return 
     */
    public Integer getID() {
        return ID;
    }

    /**
     * this method sets the ID
     * 
     * @param ID
     */
    public void setID(Integer ID) {
        this.ID = ID;
    }
 
    /**
     * this method gets the user name
     * 
     * @return 
     */
    public String getUsername() {
        return username;
    }

    /**
     * this method sets the user name
     * 
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * this method gets the first name
     * 
     * @return 
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * this method sets the first name
     * 
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * this method gets the last name
     * 
     * @return 
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * this method sets the last name
     * 
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * this method gets the user type
     * 
     * @return 
     */
    public String getUserType() {
        return userType;
    }

    /**
     * this method sets the user type
     * 
     * @param userType
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     * this method gets the password
     * 
     * @return 
     */
    public String getPassword() {
        return password;
    }

    /**
     * this method sets the password
     * 
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

}