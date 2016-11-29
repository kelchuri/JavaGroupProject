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
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 *
 * @author Ayushjain
 */
public class StudentQuizDAOJDBCImpl implements StudentQuizDAO {

    private Connection connection;

    StudentQuizDAOJDBCImpl() {
        String url = "jdbc:derby://localhost:1527/QCASDB";

        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException se) {
            System.out.println("Error obtaining connection with the database: " + se);
            System.exit(-1);
        }
    }

    @Override
    public String addStudentQuiz() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String updateStudentQuiz() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String deleteStudentQuiz() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Integer> numberOfQuizTakenPerInstructor(String ins_id) throws Exception {
        try (Statement stmt = connection.createStatement()) {
            ArrayList<Integer> numberOfTestTaken = new ArrayList<>();
            Integer lastMonth = 0;
            Integer lastQuarter = 0;
            Integer lastYear = 0;
            PreparedStatement stmt1 = null;
            String sql = null;
            sql = "SELECT ins_id, Count(quiz_id) AS QuizCount, date FROM Course INNER JOIN StudentQuiz ON Course.crs_id = StudentQuiz.crs_id WHERE ins_id=? GROUP BY ins_id, date";
            stmt1 = connection.prepareStatement(sql);
            stmt1.setString(1, ins_id);
            int quizCount = 0;
            ResultSet rs = stmt1.executeQuery();

            while (rs.next()) {
                ins_id = rs.getString("ins_id");
                quizCount = (rs.getInt("QuizCount"));

                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(rs.getDate("date").getTime());
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);

                String text = "Instructor: " + ins_id + "| Quiz Count: " + quizCount + "| Date: " + sdf.format(cal.getTime());
                System.out.println(text);
                Calendar lastMonth1 = Calendar.getInstance();
                lastMonth1.add(Calendar.MONTH, -1);
                Calendar lastQuarter1 = Calendar.getInstance();
                lastQuarter1.add(Calendar.MONTH, -3);
                Calendar lastYear1 = Calendar.getInstance();
                lastYear1.add(Calendar.YEAR, -1);

                if (cal.after(lastMonth1)) {
                    lastMonth = lastMonth + 1;
                } else if (cal.after(lastQuarter1)) {
                    lastQuarter = lastQuarter + 1;
                } else if (cal.after(lastYear1)) {
                    lastYear = lastYear + 1;
                }
            }

            numberOfTestTaken.add(lastMonth);
            numberOfTestTaken.add(lastQuarter);
            numberOfTestTaken.add(lastYear);
            return numberOfTestTaken;
        } catch (SQLException se) {
            //se.printStackTrace();
            throw new Exception("Error reading the count of number of test taken in last year, quarter and year as per instructor ID.", se);
        }
    }

    @Override
    public void close() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Double> avgScoreForInstructor(String ins_id) throws Exception {
        try (Statement stmt = connection.createStatement()) {
            ArrayList<Double> avgScore = new ArrayList<>();
            Integer lastMonth = 0;
            Integer lastQuarter = 0;
            Integer lastYear = 0;
            PreparedStatement stmt1 = null;
            String sql = null;
        sql = "SELECT Course.ins_id, Course.crs_id, StudentQuiz.stu_id, StudentQuiz.marks, StudentQuiz.date\n"
                + "FROM Course INNER JOIN StudentQuiz ON Course.crs_id = StudentQuiz.crs_id\n"
                + " where ins_id=? GROUP BY Course.ins_id, Course.crs_id, StudentQuiz.stu_id, StudentQuiz.marks, StudentQuiz.date";            stmt1 = connection.prepareStatement(sql);
            stmt1.setString(1, ins_id);
            int quizCount = 0;
            ResultSet rs = stmt1.executeQuery();

            while (rs.next()) {
                ins_id = rs.getString("ins_id");
                quizCount = (rs.getInt("QuizCount"));

                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(rs.getDate("date").getTime());
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);

                String text = "Instructor: " + ins_id + "| Quiz Count: " + quizCount + "| Date: " + sdf.format(cal.getTime());
                System.out.println(text);
                Calendar lastMonth1 = Calendar.getInstance();
                lastMonth1.add(Calendar.MONTH, -1);
                Calendar lastQuarter1 = Calendar.getInstance();
                lastQuarter1.add(Calendar.MONTH, -3);
                Calendar lastYear1 = Calendar.getInstance();
                lastYear1.add(Calendar.YEAR, -1);

                if (cal.after(lastMonth1)) {
                    lastMonth = lastMonth + 1;
                } else if (cal.after(lastQuarter1)) {
                    lastQuarter = lastQuarter + 1;
                } else if (cal.after(lastYear1)) {
                    lastYear = lastYear + 1;
                }
            }

            numberOfTestTaken.add(lastMonth);
            numberOfTestTaken.add(lastQuarter);
            numberOfTestTaken.add(lastYear);
            return numberOfTestTaken;
        } catch (SQLException se) {
            //se.printStackTrace();
            throw new Exception("Error reading the count of number of test taken in last year, quarter and year as per instructor ID.", se);
        }    }

}
