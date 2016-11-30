
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
public class User {

    private Integer userId;
    private String firstName;
    private String lastName;
    private String password;
    private String emailId;
    private String userType;

    /**
     * this a default constructor
     *
     */
    public User() {

    }

    /**
     * this constructor helps create the object of user with assigning all the
     * details including user id
     *
     * @param userId
     * @param firstName
     * @param lastName
     * @param password
     * @param emailId
     * @param userType
     */
    public User(Integer userId, String firstName, String lastName, String password, String emailId, String userType) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.emailId = emailId;
        this.userType = userType;
    }

    /**
     * this constructor helps create the object of user without assigning all
     * the details including user id
     *
     * @param firstName
     * @param lastName
     * @param userType
     * @param password
     * @param emailId
     */
    public User(String firstName, String lastName, String password, String emailId, String userType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.emailId = emailId;
        this.userType = userType;
    }

    /**
     * this method gets the user id
     *
     * @return 
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * this method sets the user id
     * 
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
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

    /**
     * this method gets the email id
     *
     * @return 
     */
    public String getEmailId() {
        return emailId;
    }

    /**
     * this method sets the email id
     * 
     * @param emailId
     */
    public void setEmailId(String emailId) {
        this.emailId = emailId;
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

}
