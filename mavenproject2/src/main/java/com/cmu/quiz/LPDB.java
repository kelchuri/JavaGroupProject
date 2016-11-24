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
 * @author b2
 */
public class LPDB {

    static int checkCredentials(String username, String password) throws Throwable {
        PreparedStatement stmt = null;
        Connection conn = null;
        String dbURL = "jdbc:derby://localhost:1527/QCASDB;create=true";
        conn = DriverManager.getConnection(dbURL);
        createTable(conn, stmt);
        insertToDB(conn, stmt);
        int userVal = validateUserExists(conn, stmt, username);
        int stuInsId = checkStuIns(conn, stmt, username);
//        System.out.println("StuInsId is "+stuInsId);
        int check = 0;
        if (userVal == 1) {
            String sql = "SELECT password FROM APP.USERDATA WHERE userid=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            String pass = rs.getString("PASSWORD");
//            System.out.println("Correct Password is "+ pass);

            if (!pass.equals(password)) {
                check = 0;
            } else {
                check = 1;
            }
        } else {
            check = 0;
        }
        if (check == 1) {
            if (stuInsId == 1) {
                check = 2;
            }
        }
        dropTable(conn, stmt);
        return check;

    }

    static void createTable(Connection conn, PreparedStatement stmt) {
        try {
            String sql = "CREATE TABLE USERDATA("
                    + "userid VARCHAR(20),"
                    + "username VARCHAR(20),"
                    + "password VARCHAR(20),"
                    + "stuIns VARCHAR(20))";
            stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     *
     * @param conn
     * @param stmt
     */
    public static void insertToDB(Connection conn, PreparedStatement stmt) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("User.txt"));
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                LPDB_var.setUserid(values[0]);
                LPDB_var.setUsername(values[1]);
                LPDB_var.setPassword(values[2]);
                LPDB_var.setStuIns(values[3]);

                String addRowSql = "INSERT INTO APP.USERDATA("
                        + "userid,username,password,stuins)"
                        + "VALUES(?,?,?,?)";

                stmt = conn.prepareStatement(addRowSql);
                stmt.setString(1, LPDB_var.getUserid());
                stmt.setString(2, LPDB_var.getUsername());
                stmt.setString(3, LPDB_var.getPassword());
                stmt.setString(4, LPDB_var.getStuIns());

                stmt.executeUpdate();
            }
        } catch (SQLException | IOException ex) {
            
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

    private static int checkStuIns(Connection conn, PreparedStatement stmt, String username) throws Throwable {
        String sql = "SELECT stuins FROM APP.USERDATA WHERE userid=?";
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        String stuIns = rs.getString("STUINS");
        int stuInsId = 0;
//        System.out.println("StuIns is "+stuIns);
        if (stuIns.equals("instructor")) {
            stuInsId = 1;
        }
        return stuInsId;
    }

    private static void dropTable(Connection conn, PreparedStatement stmt) throws SQLException {
        String sql = "DROP TABLE USERDATA";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();
    }

}
