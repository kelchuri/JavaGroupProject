
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

    public User(Integer userId, String firstName, String lastName, String password, String emailId, String userType) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.emailId = emailId;
        this.userType = userType;
    }
    
    public User(String firstName, String lastName, String password, String emailId, String userType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.emailId = emailId;
        this.userType = userType;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

}
