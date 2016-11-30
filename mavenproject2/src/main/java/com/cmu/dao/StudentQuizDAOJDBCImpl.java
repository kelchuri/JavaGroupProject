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
    private ArrayList<String> studentMarksList;

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

    
    public ArrayList<Integer> numberOfQuizTakenPerInstructor(int ins_id) throws Exception {
        try (Statement stmt = connection.createStatement()) {
            ArrayList<Integer> numberOfTestTaken = new ArrayList<>();
            Integer lastMonth = 0;
            Integer lastQuarter = 0;
            Integer lastYear = 0;
            PreparedStatement stmt1 = null;
            String sql = null;
            sql = "SELECT ins_id, Count(quiz_id) AS QuizCount, date FROM Course INNER JOIN StudentQuiz ON Course.crs_id = StudentQuiz.crs_id WHERE ins_id=? GROUP BY ins_id, date";
            stmt1 = connection.prepareStatement(sql);
            stmt1.setInt(1, ins_id);
            int quizCount = 0;
            ResultSet rs = stmt1.executeQuery();

            while (rs.next()) {
                ins_id = rs.getInt("ins_id");
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
    
    public ArrayList<String> passFailStudent(int stu_id) throws Throwable {

        studentMarksList = new ArrayList<>();
        PreparedStatement stmt = null;
        Connection conn = null;
        String dbURL = "jdbc:derby://localhost:1527/QCASDB;create=true";
        conn = DriverManager.getConnection(dbURL);

        String sql = "select stu_id, studentquiz.quiz_id, marks, total_marks\n"
                + "from quizmarks inner join studentquiz\n"
                + "on quizmarks.QUIZ_ID=studentquiz.QUIZ_ID \n"
                + "where stu_id=? \n"
                + "group by stu_id, studentquiz.quiz_id, marks, total_marks";
        stmt = conn.prepareStatement(sql);
        stmt.setInt(1, stu_id);
        ResultSet rs = stmt.executeQuery();
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        while (rs.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) {
                    System.out.print(",  ");
}
                String columnValue = rs.getString(i);
                studentMarksList.add(rsmd.getColumnName(i));
                //System.out.print(columnValue + " " + rsmd.getColumnName(i));
            }

        }
        return studentMarksList;
    }

    @Override
    public ArrayList<Double> avgScoreForInstructor(int ins_id) throws Exception {
        try (Statement stmt = connection.createStatement()) {
            ArrayList<Double> avgScore = new ArrayList<>();
            Double lastMonth = 0.00;
            Double lastQuarter = 0.00;
            Double lastYear = 0.00;
            Integer totalMarksMonth = 0;
            Integer totalMarksQuarter = 0;
            Integer totalMarksYear = 0;
            Double avgMarksMonth = 0.00;
            Double avgMarksQuarter = 0.00;
            Double avgMarksYear = 0.00;
            PreparedStatement stmt1 = null;
            String sql = null;
            sql = "SELECT Course.ins_id, Course.crs_id, StudentQuiz.stu_id, StudentQuiz.marks, StudentQuiz.date\n"
                    + "FROM Course INNER JOIN StudentQuiz ON Course.crs_id = StudentQuiz.crs_id\n"
                    + " where ins_id=? GROUP BY Course.ins_id, Course.crs_id, StudentQuiz.stu_id, StudentQuiz.marks, StudentQuiz.date";
            stmt1 = connection.prepareStatement(sql);
            stmt1.setInt(1, ins_id);
            int quizMarks = 0;
            ResultSet rs = stmt1.executeQuery();

            while (rs.next()) {
                ins_id = rs.getInt("ins_id");
                quizMarks = (rs.getInt("marks"));

                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(rs.getDate("date").getTime());
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);

                String text = "Instructor: " + ins_id + "| Quiz Count: " + quizMarks + "| Date: " + sdf.format(cal.getTime());
                System.out.println(text);
                Calendar lastMonth1 = Calendar.getInstance();
                lastMonth1.add(Calendar.MONTH, -1);
                Calendar lastQuarter1 = Calendar.getInstance();
                lastQuarter1.add(Calendar.MONTH, -3);
                Calendar lastYear1 = Calendar.getInstance();
                lastYear1.add(Calendar.YEAR, -1);

                if (cal.after(lastMonth1)) {
                    lastMonth = lastMonth + 1.00;
                    totalMarksMonth = totalMarksMonth + quizMarks;
                } else if (cal.after(lastQuarter1)) {
                    lastQuarter = lastQuarter + 1.00;
                    totalMarksQuarter = totalMarksQuarter + quizMarks;
                } else if (cal.after(lastYear1)) {
                    lastYear = lastYear + 1.00;
                    totalMarksYear = totalMarksYear + quizMarks;
                }
            }
            if (lastMonth > 0) {
                avgMarksMonth = totalMarksMonth / lastMonth;
            }
            if (lastQuarter > 0) {
                avgMarksQuarter = totalMarksQuarter / lastQuarter;
            }
            if (lastYear > 0) {
                avgMarksYear = totalMarksYear / lastYear;
            }
            
            avgScore.add(avgMarksMonth);
            avgScore.add(avgMarksQuarter);
            avgScore.add(avgMarksYear);
            return avgScore;
        } catch (SQLException se) {
            //se.printStackTrace();
            throw new Exception("Error reading the count of number of test taken in last year, quarter and year as per instructor ID.", se);
        }
    }
    

    public ArrayList<Integer> numberOfQuizTakenPerStudent(int stu_id) throws Exception {
        try (Statement stmt = connection.createStatement()) {
            ArrayList<Integer> numberOfTestTaken = new ArrayList<>();
            Integer lastMonth = 0;
            Integer lastQuarter = 0;
            Integer lastYear = 0;
            PreparedStatement stmt1 = null;
            String sql = null;
            sql = "SELECT stu_id, quiz_id,date\n"
                + "FROM StudentQuiz\n"
                + "where stu_id=?";
            stmt1 = connection.prepareStatement(sql);
            stmt1.setInt(1, stu_id);
//            int quizCount = 0;
            ResultSet rs = stmt1.executeQuery();
 
            while (rs.next()) {
                stu_id = rs.getInt("stu_id");
//                quizCount = (rs.getInt("QuizCount"));
 
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(rs.getDate("date").getTime());
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
 
                String text = "Instructor: " + stu_id + "| Date: " + sdf.format(cal.getTime());
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


}
