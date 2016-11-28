/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmu.dao;

import com.cmu.Models.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author kavya
 */
public class UserDAOJDBCImpl implements UserDAO{
    private Connection connection;
    
    UserDAOJDBCImpl(){
        String url = "jdbc:derby://localhost:1527/QCASDB";

        try {
           connection = DriverManager.getConnection(url);
        } catch (SQLException se) {
            System.out.println("Error obtaining connection with the database: " + se);
            System.exit(-1);
        }
    }
    
    // Add an User record using an INSERT SQL command
    public void add(User user) throws Exception {
        try (Statement stmt = connection.createStatement()) {
            String query = "INSERT INTO USERTBL VALUES (" + user.getFirstName() + ","
                    + "'" + user.getLastName()+ "'," + "'" + user.getEmailId() + "',"
                    + "'" + user.getUserType() + "'," + "'" + user.getPassword()+ ")";
 
            if (stmt.executeUpdate(query) != 1) {
                throw new Exception("Error adding User");
            }
        } catch (SQLException se) {
            //se.printStackTrace();
            throw new Exception("Error adding User in DAO", se);
        }
    }
    
    // Update an User record with a SQL UPDATE command
    public void update(User user) throws Exception {
        try (Statement stmt = connection.createStatement()) {
            String query = "UPDATE USERTBL "
                    + "SET FIRST_NAME='" + user.getFirstName() + "',"
                    + "LAST_NAME='" + user.getLastName() + "',"
                    + "password='" + user.getPassword() + "',"
                    + "email=" + user.getEmailId()
                    + "WHERE ID=" + user.getUserId();
            if (stmt.executeUpdate(query) != 1) {
                throw new Exception("Error updating User");
            }
        } catch (SQLException se) {
            throw new Exception("Error updating User in DAO", se);
        }
    }
   
    // Find an user record using this ID
    public User findById(int id) throws Exception {
        try (Statement stmt = connection.createStatement()) {
            String query = "SELECT * FROM USERTBL WHERE email=" + id;
            ResultSet rs = stmt.executeQuery(query);
            if (!rs.next()) {
                return null;
            }
            return (new User(rs.getInt("userId"), rs.getString("FIRST_NAME"),
                    rs.getString("LAST_NAME"), rs.getString("password"),
                    rs.getString("email"), rs.getString("userType")));
        } catch (SQLException se) {
            //se.printStackTrace();
            throw new Exception("Error finding User in DAO", se);
        }
    }
    
    public User[] getAllStudents() throws Exception {
        try (Statement stmt = connection.createStatement()) {
            String query = "SELECT * FROM USERTBL where userType='student'";
            ResultSet rs = stmt.executeQuery(query);
            // Create an ArrayList to save resulting records
            ArrayList<User> students = new ArrayList<>();
            // Iterate through the results and create user objects
            while (rs.next()) {
                
                    students.add(new User(rs.getInt("userId"), rs.getString("FIRST_NAME"),
                    rs.getString("LAST_NAME"), rs.getString("password"),
                    rs.getString("email"), rs.getString("userType")));
                
                
            }
            return students.toArray(new User[0]);
        } catch (SQLException se) {
            //se.printStackTrace();
            throw new Exception("Error getting all Students in DAO: " + se.getMessage(), se);
        }
    }
    
    public User[] getAllInstructors() throws Exception {
        try (Statement stmt = connection.createStatement()) {
            String query = "SELECT * FROM USERTBL where userType='instructor'";
            ResultSet rs = stmt.executeQuery(query);
            // Create an ArrayList to save resulting records
            ArrayList<User> students = new ArrayList<>();
            // Iterate through the results and create user objects
            while (rs.next()) {
                
                    students.add(new User(rs.getInt("userId"), rs.getString("FIRST_NAME"),
                    rs.getString("LAST_NAME"), rs.getString("password"),
                    rs.getString("email"), rs.getString("userType")));
                
                
            }
            return students.toArray(new User[0]);
        } catch (SQLException se) {
            //se.printStackTrace();
            throw new Exception("Error getting all Instrutors in DAO: " + se.getMessage(), se);
        }
    }

    public void delete(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void close() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean checkUserExists(String userId, String password) throws Exception {
        try (Statement stmt = connection.createStatement()) {
            String query = "SELECT * FROM USERTBL WHERE EMAIL='" + userId +"'and password='"+ password + "'";
            ResultSet rs = stmt.executeQuery(query);
            if (!rs.next()) {
                return false;
            } else {
                return true;
            }
           
        } catch (SQLException se) {
            //se.printStackTrace();
            throw new Exception("Error finding User in DAO", se);
        }
    }

    @Override
    public String checkUserType(String userId) throws Exception {
        String type= "";
        try (Statement stmt = connection.createStatement()) {
            String query = "SELECT * FROM USERTBL WHERE EMAIL='" + userId + "'";
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                type = rs.getString("stuinsid");
            }
           return type;
        } catch (SQLException se) {
            //se.printStackTrace();
            throw new Exception("Error finding User in DAO", se);
        }
    }
}
