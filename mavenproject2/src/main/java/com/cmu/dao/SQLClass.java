/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 *
 * @author b2bey
 */
public class SQLClass {

    //This query takes in input of instructor id and 
    //returns the quiz count under that instructor under all dates.
    public void returnQuizCount(String ins_id) throws Throwable {
        PreparedStatement stmt = null;
        Connection conn = null;
        String dbURL = "jdbc:derby://localhost:1527/QCASDB;create=true";
        conn = DriverManager.getConnection(dbURL);
        String sql = null;
        sql = "SELECT ins_id, Count(quiz_id) AS QuizCount, date FROM Course INNER JOIN StudentQuiz ON Course.crs_id = StudentQuiz.crs_id WHERE ins_id=? GROUP BY ins_id, date";
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, ins_id);
        int quizCount = 0;
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            ins_id = rs.getString("ins_id");
            quizCount = (rs.getInt("QuizCount"));
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(rs.getDate("date").getTime());
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
            String text = "Instructor: " + ins_id + "| Quiz Count: " + quizCount + "| Date: " + sdf.format(cal.getTime());

            System.out.println(text);
        }
    }

    //This query takes in the input of instructor id
    //and  gives quiz_id, crs_name, diff_lvl, isCorrect(T/F) 
    //and count of isCorrect.
    public void returnQuizDetails(String ins_id) throws Throwable {
        PreparedStatement stmt = null;
        Connection conn = null;
        String dbURL = "jdbc:derby://localhost:1527/QCASDB;create=true";
        conn = DriverManager.getConnection(dbURL);

        String sql = "SELECT Quiz.quiz_id, Course.crs_name, Questions.diff_lvl, Quiz.isCorrect, Count(Quiz.isCorrect) AS CountResult\n"
                + "FROM (Course INNER JOIN Questions ON Course.crs_id = Questions.crs_id) INNER JOIN Quiz ON Questions.ques_id = Quiz.ques_id\n"
                + "WHERE ins_id=? GROUP BY Quiz.quiz_id, Course.crs_name, Questions.diff_lvl, Quiz.isCorrect";
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, ins_id);
        int quizCount = 0;
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            int quiz_id = rs.getInt("quiz_id");
            String diff_lvl = rs.getString("diff_lvl");
            Boolean isCorrect = rs.getBoolean("isCorrect");
            int CountResult = rs.getInt("CountResult");
            String text = "Quiz: " + quiz_id + "| Diff Level: " + diff_lvl + "| Result: " + isCorrect + "| Count of Result: " + CountResult;

            System.out.println(text);
        }

    }

    //This function returns the student id ques id diff lvl and isCorrect
    //for easy difficulty.
    public void returnTestResultEasy(int ins_id) throws Throwable {
        PreparedStatement stmt = null;
        Connection conn = null;
        String dbURL = "jdbc:derby://localhost:1527/QCASDB;create=true";
        conn = DriverManager.getConnection(dbURL);
        String sql = "select stu_id, ins_id, ques_id, diff_lvl, isCorrect\n"
                + "from (studentquiz inner join quiz on studentquiz.quiz_id=quiz.quiz_id)\n"
                + "inner join course on studentquiz.crs_id=course.CRS_ID\n"
                + "group by stu_id, ques_id, diff_lvl, isCorrect, ins_id\n"
                + "having(diff_lvl='E' AND ins_id=?)";

        stmt = conn.prepareStatement(sql);
        stmt.setInt(1, ins_id);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            int stu_id = rs.getInt("stu_id");
            int ques_id = rs.getInt("ques_id");
            String diff_lvl = rs.getString("diff_lvl");
            Boolean isCorrect = rs.getBoolean("isCorrect");
            String text = "Student Id: " + stu_id + "| Question Id: " + ques_id + " |Difficulty Level: " + diff_lvl + "| Result: " + isCorrect;

            System.out.println(text);
        }

    }
    //This function returns the student id ques id diff lvl and isCorrect
    //for medium difficulty.

    public void returnTestResultMedium(int ins_id) throws Throwable {
        PreparedStatement stmt = null;
        Connection conn = null;
        String dbURL = "jdbc:derby://localhost:1527/QCASDB;create=true";
        conn = DriverManager.getConnection(dbURL);
        String sql = "select stu_id, ins_id, ques_id, diff_lvl, isCorrect\n"
                + "from (studentquiz inner join quiz on studentquiz.quiz_id=quiz.quiz_id)\n"
                + "inner join course on studentquiz.crs_id=course.CRS_ID\n"
                + "group by stu_id, ques_id, diff_lvl, isCorrect, ins_id\n"
                + "having(diff_lvl='M' AND ins_id=?)";

        stmt = conn.prepareStatement(sql);
        stmt.setInt(1, ins_id);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            int stu_id = rs.getInt("stu_id");
            int ques_id = rs.getInt("ques_id");
            String diff_lvl = rs.getString("diff_lvl");
            Boolean isCorrect = rs.getBoolean("isCorrect");
            String text = "Student Id: " + stu_id + "| Question Id: " + ques_id + " |Difficulty Level: " + diff_lvl + "| Result: " + isCorrect;
            System.out.println(text);
        }

    }
    //This function returns the student id ques id diff lvl and isCorrect
    //for high difficulty.

    public void returnTestResultHard(int ins_id) throws Throwable {
        PreparedStatement stmt = null;
        Connection conn = null;
        String dbURL = "jdbc:derby://localhost:1527/QCASDB;create=true";
        conn = DriverManager.getConnection(dbURL);
        String sql = "select stu_id, ins_id, ques_id, diff_lvl, isCorrect\n"
                + "from (studentquiz inner join quiz on studentquiz.quiz_id=quiz.quiz_id)\n"
                + "inner join course on studentquiz.crs_id=course.CRS_ID\n"
                + "group by stu_id, ques_id, diff_lvl, isCorrect, ins_id\n"
                + "having(diff_lvl='H' AND ins_id=?)";

        stmt = conn.prepareStatement(sql);
        stmt.setInt(1, ins_id);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            int stu_id = rs.getInt("stu_id");
            int ques_id = rs.getInt("ques_id");
            String diff_lvl = rs.getString("diff_lvl");
            Boolean isCorrect = rs.getBoolean("isCorrect");
            String text = "Student Id: " + stu_id + "| Question Id: " + ques_id + " |Difficulty Level: " + diff_lvl + "| Result: " + isCorrect;
            System.out.println(text);
        }

    }

    //Returns all data from the course table
    public void returnCourseDetails() throws Throwable {
        PreparedStatement stmt = null;
        Connection conn = null;
        String dbURL = "jdbc:derby://localhost:1527/QCASDB;create=true";
        conn = DriverManager.getConnection(dbURL);
        String sql = "select crs_id, crs_name, ins_id from course";
        stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            int crs_id = rs.getInt("crs_id");
            int crs_name = rs.getInt("crs_name");
            int ins_id = rs.getInt("ins_id");
            String text = "Course Id: " + crs_id + "| Course Name: " + crs_name + " |Instructor Id: " + ins_id;
            System.out.println(text);
        }
    }

    //This function takes in input of number of questions, subject and difficulty level
    //and generates a random questionaire as per the requirement.
    void QuizFunc(int NoQ, String crs_id, String diff_lvl) throws Throwable {
        PreparedStatement stmt = null;
        Connection conn = null;
        String dbURL = "jdbc:derby://localhost:1527/QCASDB;create=true";
        conn = DriverManager.getConnection(dbURL);
        String sql = null;
        sql = "Select ques_id, ques_type,diff_lvl,ques_desc,\n"
                + "option1,answer1,option2,answer2,option3,answer3,\n"
                + "option4,answer4,answer,crs_id,time\n"
                + "from questions where\n"
                + "crs_id=? and diff_lvl=?\n"
                + "ORDER BY RANDOM() OFFSET 0 ROWS FETCH NEXT ? ROWS ONLY";
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, crs_id);
        stmt.setString(2, diff_lvl);
        stmt.setInt(3, NoQ);
        ResultSet rs = stmt.executeQuery();

        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        while (rs.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) {
                    System.out.print(",  ");
                }
                String columnValue = rs.getString(i);
                System.out.print(columnValue + " " + rsmd.getColumnName(i));
            }
            System.out.println("");
        }
    }

    void QuizFuncwithoutDifficulty(int NoQ, String crs_id) throws Throwable {
        PreparedStatement stmt = null;
        Connection conn = null;
        String dbURL = "jdbc:derby://localhost:1527/QCASDB;create=true";
        conn = DriverManager.getConnection(dbURL);
        String sql = null;
        sql = "Select ques_id, ques_type,diff_lvl,ques_desc,\n"
                + "option1,answer1,option2,answer2,option3,answer3,\n"
                + "option4,answer4,answer,crs_id,time\n"
                + "from questions where\n"
                + "crs_id=?\n"
                + "ORDER BY RANDOM() OFFSET 0 ROWS FETCH NEXT ? ROWS ONLY";
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, crs_id);

        stmt.setInt(2, NoQ);
        ResultSet rs = stmt.executeQuery();

        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        while (rs.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) {
                    System.out.print(",  ");
                }
                String columnValue = rs.getString(i);
                System.out.print(columnValue + " " + rsmd.getColumnName(i));
            }
            System.out.println("");
        }
    }     void studentCountByCourse(String crs_id) throws Throwable {
        PreparedStatement stmt = null;
        Connection conn = null;
        String dbURL = "jdbc:derby://localhost:1527/QCASDB;create=true";
        conn = DriverManager.getConnection(dbURL);
        String sql = null;
        sql = "select crs_id, Count(stu_id) AS StudentCount from studentquiz where crs_id=? group by crs_id";;
stmt = conn.prepareStatement(sql);
stmt.setString(1, crs_id);
ResultSet rs = stmt.executeQuery();

   ResultSetMetaData rsmd = rs.getMetaData();
   int columnsNumber = rsmd.getColumnCount();
   while (rs.next()) {
       for (int i = 1; i <= columnsNumber; i++) {
           if (i > 1) System.out.print(",  ");
           String columnValue = rs.getString(i);
           System.out.print(columnValue + " " + rsmd.getColumnName(i));
       }
       System.out.println("");
   }
 } 
}
