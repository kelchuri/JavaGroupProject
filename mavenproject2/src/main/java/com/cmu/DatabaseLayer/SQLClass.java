/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmu.DatabaseLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    public void Query1(String ins_id) throws Throwable {
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
    public void Query2(String ins_id) throws Throwable {
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

    public void Query3(String ins_id) throws Throwable {
        PreparedStatement stmt = null;
        Connection conn = null;
        String dbURL = "jdbc:derby://localhost:1527/QCASDB;create=true";
        conn = DriverManager.getConnection(dbURL);
        String sql = "Select stu_id, ques_id, diff_lvl, isCorrect \n"
                + "from Quiz inner join studentquiz \n"
                + "on quiz.quiz_id=studentquiz.QUIZ_ID\n"
                + "group by stu_id, ques_id, diff_lvl, isCorrect\n"
                + "having diff_lvl='M'";

        stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            int stu_id = rs.getInt("stu_id");
            int ques_id = rs.getInt("ques_id");
            String diff_lvl=rs.getString("diff_lvl");
            Boolean isCorrect = rs.getBoolean("isCorrect");
            String text = "Student Id: " + stu_id + "| Question Id: " + ques_id + " |Difficulty Level: "+ diff_lvl+ "| Result: " + isCorrect;

            System.out.println(text);
        }

    }
}
