/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmu.dao;

import com.cmu.models.User;
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
    
    /**
     * 
     * this constructor creates the connection with the database
     * 
     */
    UserDAOJDBCImpl(){
        String url = "jdbc:derby:QCASDB;create=true";

        try {
           connection = DriverManager.getConnection(url);
        } catch (SQLException se) {
            System.out.println("Error obtaining connection with the database: " + se);
            System.exit(-1);
        }
    }
        
    /**
     * 
     * this method is adds the instructor to the database
     * 
     * @param user
     * @return 
     * @throws java.lang.Exception
     */
    @Override
    public User addInstructor(User user) throws Exception{
        add(user);
        return checkUserExists(user.getEmailId(), user.getPassword());
    }
        
    /**
     * 
     * this method adds an User record using an INSERT SQL command
     * 
     * @param user
     * @throws java.lang.Exception
     */ 
    @Override
    public User add(User user) throws Exception {
        try (Statement stmt = connection.createStatement()) {

            System.out.println(user.getFirstName());
            String query = "INSERT INTO USERTBL ("
                    + "first_name,last_name,email,password,stuInsId) VALUES ('" 
                    + user.getFirstName() + "',"
                    + "'" + user.getLastName()+ "'," + "'" + user.getEmailId() + "',"
                    + "'" + user.getPassword()+ "'," + "'" + user.getUserType()+ "')";

            if (stmt.executeUpdate(query) != 1) {
                throw new Exception("Error adding User");
            }
            return checkUserExists(user.getEmailId(), user.getPassword());
        } catch (SQLException se) {
            //se.printStackTrace();
            throw new Exception("Error adding User in DAO", se);
        }
    }
           
    /**
     * 
     * this method Update an User record with a SQL UPDATE command
     * 
     * @param user
     * @throws java.lang.Exception
     */ 
    @Override
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
             
    /**
     * 
     * this method Find an user record using this ID
     * 
     * @param id
     * @throws java.lang.Exception
     */  
    @Override
    public User findById(int id) throws Exception {
        try (Statement stmt = connection.createStatement()) {
            String query = "SELECT * FROM USERTBL WHERE userId=" + id;
            ResultSet rs = stmt.executeQuery(query);
            if (!rs.next()) {
                return null;
            }
            return (new User(rs.getInt("user_id"), rs.getString("FIRST_NAME"),
                    rs.getString("LAST_NAME"), rs.getString("password"),
                    rs.getString("email"), rs.getString("stuinsid")));
        } catch (SQLException se) {
            //se.printStackTrace();
            throw new Exception("Error finding User in DAO", se);
        }
    }
                
    /**
     * 
     * this method gets all the students from the user table
     * 
     * @return 
     * @throws java.lang.Exception
     */  
    @Override
    public User[] getAllStudents() throws Exception {
        try (Statement stmt = connection.createStatement()) {
            String query = "SELECT * FROM USERTBL where userType='student'";
            ResultSet rs = stmt.executeQuery(query);
            // Create an ArrayList to save resulting records
            ArrayList<User> students = new ArrayList<>();
            // Iterate through the results and create user objects
            while (rs.next()) {
                
                    students.add(new User(rs.getInt("user_id"), rs.getString("FIRST_NAME"),
                    rs.getString("LAST_NAME"), rs.getString("password"),
                    rs.getString("email"), rs.getString("stuinsid")));
                
                
            }
            return students.toArray(new User[0]);
        } catch (SQLException se) {
            //se.printStackTrace();
            throw new Exception("Error getting all Students in DAO: " + se.getMessage(), se);
        }
    }
              
    /**
     * 
     * this method s all the instructors from user table
     * 
     * @return 
     * @throws java.lang.Exception
     */    
    @Override
    public User[] getAllInstructors() throws Exception {
        try (Statement stmt = connection.createStatement()) {
            String query = "SELECT * FROM USERTBL where userType='instructor'";
            ResultSet rs = stmt.executeQuery(query);
            // Create an ArrayList to save resulting records
            ArrayList<User> students = new ArrayList<>();
            // Iterate through the results and create user objects
            while (rs.next()) {
                
                    students.add(new User(rs.getInt("user_id"), rs.getString("FIRST_NAME"),
                    rs.getString("LAST_NAME"), rs.getString("password"),
                    rs.getString("email"), rs.getString("stuinsid")));
                
                
            }
            return students.toArray(new User[0]);
        } catch (SQLException se) {
            //se.printStackTrace();
            throw new Exception("Error getting all Instrutors in DAO: " + se.getMessage(), se);
        }
    }
            
    /**
     * 
     * this method deletes the user record for given user id
     * 
     * @param id
     * @throws java.lang.Exception
     */  
    public void delete(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
            
    /**
     * 
     * this method closes all the open connections to the database
     * 
     * @throws java.lang.Exception
     */  
    @Override
    public void close() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
            
    /**
     * 
     * this method checks if the given user id exists and the password entered is correct
     * 
     * @param userId
     * @param password
     * @return 
     * @throws java.lang.Exception
     */  
    @Override
    public User checkUserExists(String userId, String password) throws Exception {
        try (Statement stmt = connection.createStatement()) {
            String query = "SELECT * FROM USERTBL WHERE email='" + userId +"' and password='"+ password + "'";
            ResultSet rs = stmt.executeQuery(query);
            if (!rs.next()) {
                return new User();
            } else {
                return new User(rs.getInt("user_id"), rs.getString("FIRST_NAME"),
                    rs.getString("LAST_NAME"), rs.getString("password"),
                    rs.getString("email"), rs.getString("stuinsid"));
            }
            
           
        } catch (SQLException se) {
            //se.printStackTrace();
            throw new Exception("Error finding User in DAO", se);
        }
    }
            
    /**
     * 
     * this method checks the user type for the given user id
     * 
     * @param userId
     * @return 
     * @throws java.lang.Exception
     */  
    @Override
    public String checkUserType(String userId) throws Exception {
        String type= "";
        try (Statement stmt = connection.createStatement()) {
            String query = "SELECT * FROM USERTBL WHERE email='" + userId + "'";
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
