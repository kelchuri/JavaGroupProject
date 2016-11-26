/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmu.quiz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author b2
 */
public class Login_Page_Database_Controller {

    static int checkCredentials(String userid, String password) throws Throwable {
        PreparedStatement stmt = null;
        Connection conn = null;
        String dbURL = "jdbc:derby://localhost:1527/QCASDB;create=true";
        conn = DriverManager.getConnection(dbURL);
        int userVal = validateUserExists(conn, stmt, userid);
        int stuInsId = checkStuIns(conn, stmt, userid);
        int check = 0;
        if (userVal == 1) {
            String sql = "SELECT password FROM USERTBL WHERE user_id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, userid);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            String pass = rs.getString("PASSWORD");
            if (!pass.equals(password)) {
                check = 0;
            } else {
                check = 1;
            }
            if (check == 1) {
                if (stuInsId == 1) {
                    check = 2;
                }
            }

            

        }return check;
}

    

    private static int validateUserExists(Connection conn, PreparedStatement stmt, String user) throws Throwable {
        List<String> users = new ArrayList<>();
        Integer temp = 0;
        String sql = "SELECT user_id FROM USERTBL";
        stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {

            String u = rs.getString("user_id");
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
        String sql = "SELECT stuinsid FROM USERTBL WHERE user_id=?";
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        String stuIns = rs.getString("STUINSID");
        int stuInsId = 0;
        if (stuIns.equals("instructor")) {
            stuInsId = 1;
        }
        return stuInsId;
    }


}


