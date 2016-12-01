/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmu.dao;

import com.cmu.models.Course;
import com.cmu.models.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Ayushjain
 */
public class CourseDAOJDBCImpl implements CourseDAO {

    private Connection connection;

    /**
     * this constructor creates the connection to the database
     * 
     */
    CourseDAOJDBCImpl() {
        String url = "jdbc:derby:QCASDB;create=true";

        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException se) {
            System.out.println("Error obtaining connection with the database: " + se);
            System.exit(-1);
        }
    }

    /**
     * this method adds a course object to the database
     * 
     */
    @Override
    public void add(Course c) throws Exception {
        String addRowSql = "INSERT INTO APP.COURSE("
                + "crs_id,crs_name,ins_id)"
                + "VALUES(?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(addRowSql)) {

            stmt.setInt(1, c.getCrs_id());
            stmt.setString(2, c.getCrs_name());
            stmt.setInt(3, c.getIns_id());
            if (stmt.executeUpdate() != 1) {
                throw new Exception("Error adding Course");
            }
        } catch (SQLException se) {
            //se.printStackTrace();
            throw new Exception("Error adding Course entry in DAO", se);
        }
    }

    /**
     * this method updates a course object to the database
     * 
     */
    @Override
    public void update(Course c) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * this method gets a course object by user id from the database
     * 
     */
    @Override
    public Course getCourse(User user) throws Exception {
        try (Statement stmt = connection.createStatement()) {
            String query = "SELECT * FROM Course WHERE ins_id=" + user.getUserId();
            ResultSet rs = stmt.executeQuery(query);
            if (!rs.next()) {
                return null;
            }
            return (new Course(rs.getInt("crs_id"), rs.getString("crs_name"),
                    rs.getInt("ins_id")));
        } catch (SQLException se) {
            //se.printStackTrace();
            throw new Exception("Error finding User in DAO", se);
        }
    }

    /**
     * this method gets a course object by course id from the database
     * 
     */
    @Override
    public Course getCourseById(int id) throws Exception {
        try (Statement stmt = connection.createStatement()) {
            String query = "SELECT * FROM Course WHERE crs_id=" + id;
            ResultSet rs = stmt.executeQuery(query);
            if (!rs.next()) {
                return null;
            }
            return (new Course(rs.getInt("crs_id"), rs.getString("crs_name"),
                    rs.getInt("ins_id")));
        } catch (SQLException se) {
            //se.printStackTrace();
            throw new Exception("Error finding User in DAO", se);
        }
    }

}
