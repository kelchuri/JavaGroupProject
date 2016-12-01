/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmu.controllers;

/**
 * All the imports used in the code below are defined here
 */
import au.com.bytecode.opencsv.CSVReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * @author Saurabh Tripathi
 */
public class DatabaseWork {

    /**
     * This function initializes the PreparedStatement and the Connection. The
     * connection to the database is defined and established. Functions for
     * creating the tables are called.
     */
    public void createConnection() {
        try {
            PreparedStatement stmt = null;
            Connection conn = null;
            String dbURL = "jdbc:derby://localhost:1527/QCASDB;create=true";
            conn = DriverManager.getConnection(dbURL);

// ---------------- Calling table creation functions-----------------------------
            UserCreation(conn, stmt);
            StudentQuizCreation(conn, stmt);
            QuestionCreation(conn, stmt);
            QuizCreation(conn, stmt);
            CourseCreation(conn, stmt);
            QuizMarksCreation(conn, stmt);
            updateTime(conn, stmt);
            updateCrsId(conn, stmt);
//-----------------Code to be used for dropping all tables-----------------------            
//            dropTables(conn, stmt);

        } catch (Throwable ex) {
        }

    }

    /**
     * This function creates the table USERTBL and inserts data in it using the
     * text file. The try catch block ensures that the code does not redundantly
     * run multiple times.
     */
    private void UserCreation(Connection conn, PreparedStatement stmt) {
        try {
            String sql = "CREATE TABLE USERTBL("
                    + "user_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                    + "first_name VARCHAR(50),"
                    + "last_name VARCHAR(50),"
                    + "email VARCHAR(50),"
                    + "password VARCHAR(50),"
                    + "stuInsId VARCHAR(50))";
            stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
            BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/files/User.csv")));
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String first_name = values[0];
                String last_name = values[1];
                String email = values[2];
                String password = values[3];
                String stuInsId = values[4];
                String addRowSql = "INSERT INTO USERTBL("
                        + "first_name,last_name,email,password,stuInsId)"
                        + "VALUES(?,?,?,?,?)";

                stmt = conn.prepareStatement(addRowSql);
                stmt.setString(1, first_name);
                stmt.setString(2, last_name);
                stmt.setString(3, email);
                stmt.setString(4, password);
                stmt.setString(5, stuInsId);
                stmt.executeUpdate();
            }
        } catch (SQLException | IOException ex) {
        }
    }

    /**
     * This function creates the table STUDENTQUIZ and inserts data in it using
     * the text file. The try catch block ensures that the code does not
     * redundantly run multiple times.
     */
    private void StudentQuizCreation(Connection conn, PreparedStatement stmt) {
        try {
            String sql = "CREATE TABLE STUDENTQUIZ("
                    + "stu_id int,"
                    + "quiz_id int,"
                    + "marks DECIMAL,"
                    + "date DATE,"
                    + "crs_id int)";
            stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
            BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/files/StudentQuiz.csv")));
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                int stu_id = Integer.parseInt(values[0]);
                int quiz_id = Integer.parseInt(values[1]);
                int marks = Integer.parseInt(values[2]);
                Calendar cal = Calendar.getInstance();
                int crs_id = Integer.parseInt(values[4]);
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
                cal.setTime(sdf.parse(values[3]));
                java.sql.Date date = new java.sql.Date(cal.getTime().getTime());
                Calendar cal1 = new GregorianCalendar();
                cal.setTimeInMillis(date.getTime());

                String addRowSql = "INSERT INTO APP.STUDENTQUIZ("
                        + "stu_id,quiz_id,marks, date, crs_id)"
                        + "VALUES(?,?,?,?,?)";

                stmt = conn.prepareStatement(addRowSql);
                stmt.setInt(1, stu_id);
                stmt.setInt(2, quiz_id);
                stmt.setInt(3, marks);
                stmt.setDate(4, date);
                stmt.setInt(5, crs_id);
                stmt.executeUpdate();
            }
        } catch (SQLException | IOException | ParseException ex) {
        }
    }

    /**
     * This function creates the table QUESTIONS and inserts data in it using
     * the text file. The try catch block ensures that the code does not
     * redundantly run multiple times.
     */
    private void QuestionCreation(Connection conn, PreparedStatement stmt) {
        try {
            String sql = "CREATE TABLE QUESTIONS("
                    + "ques_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
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
                    + "crs_id int,"
                    + "time DECIMAL)";
            stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
            CSVReader csvr = new CSVReader(new InputStreamReader(getClass().getResourceAsStream("/files/Java Questions.csv")), ',', '"', 0);
            String[] nextLine;
            while ((nextLine = csvr.readNext()) != null) {
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
                int crs_id = 0;
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
                        + "ques_type, diff_lvl, ques_desc, "
                        + "option1, answer1, option2, answer2, option3, "
                        + "answer3, option4, answer4, answer, crs_id, time)"
                        + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

                stmt = conn.prepareStatement(addRowSql);

                stmt.setString(1, ques_type);
                stmt.setString(2, diff_lvl);
                stmt.setString(3, ques_desc);
                stmt.setString(4, option1);
                stmt.setString(5, answer1);
                stmt.setString(6, option2);
                stmt.setString(7, answer2);
                stmt.setString(8, option3);
                stmt.setString(9, answer3);
                stmt.setString(10, option4);
                stmt.setString(11, answer4);
                stmt.setString(12, answer);
                stmt.setInt(13, crs_id);
                stmt.setInt(14, time);

                stmt.executeUpdate();
            }
        } catch (SQLException | IOException ex) {
        }
    }

    /**
     * This function creates the table QUIZ and inserts data in it using the
     * text file. The try catch block ensures that the code does not redundantly
     * run multiple times.
     */
    private void QuizCreation(Connection conn, PreparedStatement stmt) {
        try {
            String sql = "CREATE TABLE QUIZ("
                    + "quiz_id DECIMAL,"
                    + "ques_id DECIMAL,"
                    + "diff_lvl VARCHAR(20),"
                    + "isCorrect BOOLEAN)";
            stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
            BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/files/Quiz.csv")));

            String line = null;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                int quiz_id = Integer.parseInt(values[0]);
                int ques_id = Integer.parseInt(values[1]);
                String diff_lvl = values[2];
                Boolean isCorrect = Boolean.parseBoolean(values[3]);
                String addRowSql = "INSERT INTO APP.QUIZ("
                        + "quiz_id,ques_id,diff_lvl,isCorrect)"
                        + "VALUES(?,?,?,?)";

                stmt = conn.prepareStatement(addRowSql);
                stmt.setInt(1, quiz_id);
                stmt.setInt(2, ques_id);
                stmt.setString(3, diff_lvl);
                stmt.setBoolean(4, isCorrect);
                stmt.executeUpdate();
            }
        } catch (SQLException | IOException ex) {
        }
    }

    /**
     * This function creates the table COURSE and inserts data in it using the
     * text file. The try catch block ensures that the code does not redundantly
     * run multiple times.
     */
    private void CourseCreation(Connection conn, PreparedStatement stmt) {
        try {
            String sql = "CREATE TABLE COURSE("
                    + "crs_id INTEGER,"
                    + "crs_name VARCHAR(20),"
                    + "ins_id INTEGER)";
            stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
            BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/files/Courses.csv")));
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                int crs_id = Integer.parseInt(values[0]);
                String crs_name = values[1];
                int ins_id = Integer.parseInt(values[2]);
                String addRowSql = "INSERT INTO APP.COURSE("
                        + "crs_id,crs_name,ins_id)"
                        + "VALUES(?,?,?)";

                stmt = conn.prepareStatement(addRowSql);
                stmt.setInt(1, crs_id);
                stmt.setString(2, crs_name);
                stmt.setInt(3, ins_id);
                stmt.executeUpdate();
            }
        } catch (SQLException | IOException ex) {
        }
    }

    /**
     * This function creates the table QUIZMARKS and inserts data in it. This
     * table does not take data from any specific text file. The try catch block
     * ensures that the code does not redundantly run multiple times.
     */
    private void QuizMarksCreation(Connection conn, PreparedStatement stmt) {
        try {
            String sql = "CREATE TABLE quizMarks("
                    + "quiz_id DECIMAL,"
                    + "total_marks DECIMAL)";
            stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
            String sql1 = "SELECT quiz_id, Count(ques_id) AS Ques_Count\n"
                    + "FROM Quiz GROUP BY quiz_id";
            String sql2 = "INSERT INTO quizMarks(" + "quiz_id," + "total_marks)" + "VALUES(?,?)";

            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            stmt = conn.prepareStatement(sql2);
            ResultSet rs = statement.executeQuery(sql1);
            while (rs.next()) {
                int quiz_id = rs.getInt("quiz_id");
                int ques_count = rs.getInt("ques_count");
                int total_marks = ques_count * 3;
                stmt.setInt(1, quiz_id);
                stmt.setInt(2, total_marks);
                stmt.executeUpdate();
            }
        } catch (SQLException ex) {
        }
    }

    /**
     * This function updates the time allocated for the question based on the
     * difficulty level.
     */
    private static void updateTime(Connection conn, PreparedStatement stmt) throws Throwable {
        String sql = "UPDATE QUESTIONS SET time = 60 where diff_lvl='E'";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();
        sql = "UPDATE QUESTIONS SET time = 90 where diff_lvl='M'";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();
        sql = "UPDATE QUESTIONS SET time = 120 where diff_lvl='H'";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();
    }

    /**
     * This function can be used to update the crs_id in the COURSE table.
     */
    private static void updateCrsId(Connection conn, PreparedStatement stmt) {
        try {
            String sql = "UPDATE QUESTIONS SET crs_id = 1";
            stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
        } catch (SQLException ex) {
        }
    }

    /**
     * This function can be used to drop all the 6 tables created above.
     */
    private static void dropTables(Connection conn, PreparedStatement stmt) {
        try {
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
            sql = "drop table QuizMarks";
            stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
        } catch (SQLException ex) {
        }
    }
}
