/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmu.quiz;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author b2bey
 */
public class LPDB {

    static int checkCredentials(String username, String password) throws Throwable {
        PreparedStatement stmt = null;
        Connection conn = null;
        String dbURL = "jdbc:derby://localhost:1527/QCASDB;create=true";
        conn = DriverManager.getConnection(dbURL);
        createTable(conn, stmt);
        insertToDB(conn, stmt);
     int userVal= validateUserExists(conn,stmt,username);
     int check = 0;
     if(userVal==1){
        String sql = "SELECT password FROM APP.USERDATA WHERE userId=?";
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        String pass = rs.getString("PASSWORD");
        System.out.println(pass);
        
        if (!pass.equals(password)) {
            check = 0;
        } else {
            check = 1;
        }}else{
         check=0;
     }
     
        return check;

    }

    static void createTable(Connection conn, PreparedStatement stmt) {
        try {
            String sql = "CREATE TABLE USERDATA("
                    + "userid VARCHAR(20),"
                    + "password VARCHAR(20))";
            stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void insertToDB(Connection conn, PreparedStatement stmt) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("C:\\B2\\User.txt"));
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                LPDB_var.setUserid(values[1]);
                LPDB_var.setPassword(values[2]);

                String addRowSql = "INSERT INTO APP.USERDATA("
                        + "userid,password)"
                        + "VALUES(?,?)";

                stmt = conn.prepareStatement(addRowSql);
                stmt.setString(1, LPDB_var.getUserid());
                stmt.setString(2, LPDB_var.getPassword());

                stmt.executeUpdate();
            }
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }

    }

    private static int validateUserExists(Connection conn, PreparedStatement stmt, String user) throws Throwable {
        List<String> users = new ArrayList<>();
        Integer temp = 0;
        String sql = "SELECT userid FROM APP.USERDATA";
        stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {

            String u = rs.getString("USERID");
            users.add(u);
        }
        for (String no : users) {
            if (no.equals(user)) {
                temp = 1;
            }
        }
        return temp;
    }
}
