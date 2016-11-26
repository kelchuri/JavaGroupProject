/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmu.quiz;

import au.com.bytecode.opencsv.CSVReader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 *
 * @author b2bey
 */
public class DatabaseWork {

    static void createConnection() throws Throwable {
        PreparedStatement stmt = null;
        Connection conn = null;
        String dbURL = "jdbc:derby://localhost:1527/QCASDB;create=true";
        conn = DriverManager.getConnection(dbURL);
//        createStudentQuiz(conn, stmt);
//        createQuestions(conn, stmt);
//        createQuiz(conn, stmt);
//        createCourse(conn, stmt);
//        insertToStudentQuiz(conn, stmt);
//        insertToQuestions(conn, stmt);
//        insertToQuiz(conn, stmt);
//        insertToCourse(conn, stmt);
//        dropTables(conn, stmt);
updateQuesID(conn,stmt);
//        updateTime(conn, stmt);
//updateCrsId(conn,stmt);
    }

    private static void createStudentQuiz(Connection conn, PreparedStatement stmt) throws Throwable {
        String sql = "CREATE TABLE STUDENTQUIZ("
                + "stu_id VARCHAR(20),"
                + "quiz_id VARCHAR(20),"
                + "marks VARCHAR(20))";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();
    }

    private static void createQuestions(Connection conn, PreparedStatement stmt) throws Throwable {
        String sql = "CREATE TABLE QUESTIONS("
                + "ques_id VARCHAR(20),"
                + "ques_type VARCHAR(20),"
                + "diff_lvl VARCHAR(20),"
                + "ques_desc VARCHAR(300),"
                + "option1 VARCHAR(200),"
                + "answer1 VARCHAR(200),"
                + "option2 VARCHAR(200),"
                + "answer2 VARCHAR(200),"
                + "option3 VARCHAR(200),"
                + "answer3 VARCHAR(200),"
                + "option4 VARCHAR(200),"
                + "answer4 VARCHAR(200),"
                + "answer VARCHAR(200),"
                + "crs_id VARCHAR(20),"
                + "time DECIMAL)";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();
    }

    private static void createQuiz(Connection conn, PreparedStatement stmt) throws Throwable {
        String sql = "CREATE TABLE QUIZ("
                + "quiz_id VARCHAR(20),"
                + "ques_id VARCHAR(20),"
                + "diff_lvl VARCHAR(20),"
                + "isCorrect BOOLEAN)";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();
    }

    private static void createCourse(Connection conn, PreparedStatement stmt) throws Throwable {
        String sql = "CREATE TABLE COURSE("
                + "crs_id VARCHAR(20),"
                + "crs_name VARCHAR(20),"
                + "ins_id VARCHAR(20))";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();
    }

    private static void insertToQuestions(Connection conn, PreparedStatement stmt) throws Throwable {
        CSVReader csvr = new CSVReader(new FileReader("C:\\B2\\Java Questions.csv"), ',', '"', 0);
        String[] nextLine;
        while ((nextLine = csvr.readNext()) != null) {

//            String[] values = line.split(",");
            String ques_type = "";
            String diff_lvl = "";
            String ques_desc = "";
            String option1 = "";
            String answer1 = "";
            String option2 = "";
            String answer2 = "";
            String option3 = "";
            String answer3 = "";
            String option4 = "";
            String answer4 = "";
            String answer = "";
            String ques_id = "";
            String crs_id = "";
            int time = 0;
            ques_type = nextLine[0];
            switch (ques_type) {
                case "MC":
                    diff_lvl = nextLine[1];
                    ques_desc = nextLine[2];
                    option1 = nextLine[3];
                    answer1 = nextLine[4];
                    option2 = nextLine[5];
                    answer2 = nextLine[6];
                    option3 = nextLine[7];
                    answer3 = nextLine[8];
                    option4 = nextLine[9];
                    answer4 = nextLine[10];
                    break;
                case "MA":
                    diff_lvl = nextLine[1];
                    ques_desc = nextLine[2];
                    option1 = nextLine[3];
                    answer1 = nextLine[4];
                    option2 = nextLine[5];
                    answer2 = nextLine[6];
                    option3 = nextLine[7];
                    answer3 = nextLine[8];
                    option4 = nextLine[9];
                    answer4 = nextLine[10];
                    break;
                case "FIB":
                    diff_lvl = nextLine[1];
                    ques_desc = nextLine[2];
                    answer = nextLine[3];
                    break;
                case "TF":
                    diff_lvl = nextLine[1];
                    ques_desc = nextLine[2];
                    answer = nextLine[3];
                    break;
            }

            String addRowSql = "INSERT INTO APP.QUESTIONS("
                    + "ques_id, ques_type, diff_lvl, ques_desc, "
                    + "option1, answer1, option2, answer2, option3, "
                    + "answer3, option4, answer4, answer, crs_id, time)"
                    + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            stmt = conn.prepareStatement(addRowSql);
            stmt.setString(1, ques_id);
            stmt.setString(2, ques_type);
            stmt.setString(3, diff_lvl);
            stmt.setString(4, ques_desc);
            stmt.setString(5, option1);
            stmt.setString(6, answer1);
            stmt.setString(7, option2);
            stmt.setString(8, answer2);
            stmt.setString(9, option3);
            stmt.setString(10, answer3);
            stmt.setString(11, option4);
            stmt.setString(12, answer4);
            stmt.setString(13, answer);
            stmt.setString(14, crs_id);
            stmt.setInt(15, time);

            stmt.executeUpdate();
        }
    }

    private static void insertToQuiz(Connection conn, PreparedStatement stmt) throws Throwable {
        BufferedReader br = new BufferedReader(new FileReader("C:\\B2\\Quiz.txt"));
        String line = null;
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            String quiz_id = values[0];
            String ques_id = values[1];
            String diff_lvl = values[2];
            Boolean isCorrect = Boolean.parseBoolean(values[3]);
            String addRowSql = "INSERT INTO APP.QUIZ("
                    + "quiz_id,ques_id,diff_lvl,isCorrect)"
                    + "VALUES(?,?,?,?)";

            stmt = conn.prepareStatement(addRowSql);
            stmt.setString(1, quiz_id);
            stmt.setString(2, ques_id);
            stmt.setString(3, diff_lvl);
            stmt.setBoolean(4, isCorrect);
            stmt.executeUpdate();
        }
    }

    private static void insertToStudentQuiz(Connection conn, PreparedStatement stmt) throws Throwable {
        BufferedReader br = new BufferedReader(new FileReader("C:\\B2\\StudentQuiz.txt"));
        String line = null;
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            String stu_id = values[0];
            String quiz_id = values[1];
            String marks = values[2];
            String addRowSql = "INSERT INTO APP.STUDENTQUIZ("
                    + "stu_id,quiz_id,marks)"
                    + "VALUES(?,?,?)";

            stmt = conn.prepareStatement(addRowSql);
            stmt.setString(1, stu_id);
            stmt.setString(2, quiz_id);
            stmt.setString(3, marks);
            stmt.executeUpdate();
        }
    }

    private static void insertToCourse(Connection conn, PreparedStatement stmt) throws Throwable {
        BufferedReader br = new BufferedReader(new FileReader("C:\\B2\\Course.txt"));
        String line = null;
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            String crs_id = values[0];
            String crs_name = values[1];
            String ins_id = values[2];
            String addRowSql = "INSERT INTO APP.COURSE("
                    + "crs_id,crs_name,ins_id)"
                    + "VALUES(?,?,?)";

            stmt = conn.prepareStatement(addRowSql);
            stmt.setString(1, crs_id);
            stmt.setString(2, crs_name);
            stmt.setString(3, ins_id);
            stmt.executeUpdate();
        }
    }

    private static void dropTables(Connection conn, PreparedStatement stmt) throws Throwable {
        String sql = "DROP TABLE STUDENTQUIZ";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();
        sql = "DROP TABLE QUESTIONS";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();
        sql = "DROP TABLE COURSE";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();
        sql = "DROP TABLE QUIZ";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();
    }

    private static void updateQuesID(Connection conn, PreparedStatement stmt) throws Throwable {
        String sql = "update QUESTIONS outer set ques_id = (select rnum from (select ques_desc, row_number() from QUESTIONS) inner where inner.ques_desc = outer.ques_desc)";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();
    }

    private static void updateTime(Connection conn, PreparedStatement stmt) throws Throwable {
        String sql = "UPDATE QUESTIONS SET time = 60 where diff_lvl='E'";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();
        sql = "UPDATE QUESTIONS SET time = 90 where diff_lvl='M'";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();
        sql = "UPDATE QUESTIONS SET time = 60 where diff_lvl='H'";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();
    }

    private static void updateCrsId(Connection conn, PreparedStatement stmt) throws Throwable {
        String sql = "UPDATE QUESTIONS SET crs_id = 'Java'";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();
    }
}