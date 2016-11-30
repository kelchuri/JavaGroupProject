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
    private ArrayList<Double> studentMarksList;

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

                Calendar lastMonth1 = Calendar.getInstance();
                lastMonth1.add(Calendar.MONTH, -1);
                Calendar lastQuarter1 = Calendar.getInstance();
                lastQuarter1.add(Calendar.MONTH, -3);
                Calendar lastYear1 = Calendar.getInstance();
                lastYear1.add(Calendar.YEAR, -1);

                if (cal.after(lastMonth1)) {
                    lastMonth = lastMonth + 1;
                    lastQuarter = lastQuarter + 1;
                    lastYear = lastYear + 1;
                } else if (cal.after(lastQuarter1)) {
                    lastQuarter = lastQuarter + 1;
                    lastYear = lastYear + 1;
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

    public ArrayList<Double> passFailStudent(int stu_id) throws Throwable {

        try (Statement stmt = connection.createStatement()) {
            studentMarksList = new ArrayList<>();
            Double pass = 0.00;
            Double fail = 0.00;
            Double marks = 0.00;
            Double totalMarks = 0.00;
            PreparedStatement stmt1 = null;
            String sql = null;

            sql = "select stu_id, studentquiz.quiz_id, marks, total_marks\n"
                    + "from quizmarks inner join studentquiz\n"
                    + "on quizmarks.QUIZ_ID=studentquiz.QUIZ_ID \n"
                    + "where stu_id=? \n"
                    + "group by stu_id, studentquiz.quiz_id, marks, total_marks";
            stmt1 = connection.prepareStatement(sql);
            stmt1.setInt(1, stu_id);
            ResultSet rs = stmt1.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();

            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    marks = (rs.getInt("marks")) + 0.0;
                    totalMarks = (rs.getInt("total_marks")) + 0.0;

                    if ((marks / totalMarks) >= 0.5) {
                        pass = pass + 1.0;
                    } else {
                        fail = fail + 1.0;
                    }

                }
            }
            studentMarksList.add(pass);
            studentMarksList.add(fail);
            return studentMarksList;
        } catch (SQLException se) {
            //se.printStackTrace();
            throw new Exception("Error reading the count of number of test taken in last year, quarter and year as per instructor ID.", se);
        }

    }

    @Override
    public ArrayList<Double> avgScoreForInstructor(String ins_id) throws Exception {
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
            stmt1.setString(1, ins_id);
            int quizMarks = 0;
            ResultSet rs = stmt1.executeQuery();

            while (rs.next()) {
                ins_id = rs.getString("ins_id");
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

    @Override
    public ArrayList<Integer> numberOfQuizTakenPerStudent(String stu_id) throws Exception {
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
            stmt1.setString(1, stu_id);
            ResultSet rs = stmt1.executeQuery();

            while (rs.next()) {
                stu_id = rs.getString("stu_id");

                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(rs.getDate("date").getTime());
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);

                Calendar lastMonth1 = Calendar.getInstance();
                lastMonth1.add(Calendar.MONTH, -1);
                Calendar lastQuarter1 = Calendar.getInstance();
                lastQuarter1.add(Calendar.MONTH, -3);
                Calendar lastYear1 = Calendar.getInstance();
                lastYear1.add(Calendar.YEAR, -1);

                if (cal.after(lastMonth1)) {
                    lastMonth = lastMonth + 1;
                    lastQuarter = lastQuarter + 1;
                    lastYear = lastYear + 1;
                } else if (cal.after(lastQuarter1)) {
                    lastQuarter = lastQuarter + 1;
                    lastYear = lastYear + 1;
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
    public Double overallAvgMarksStudent(int stu_id) throws Exception {

        try (Statement stmt = connection.createStatement()) {
            Double noOfQuizes = 0.00;
            Double marks = 0.00;
            Double totalMarks = 0.00;
            Double overallAvgMarks = 0.00;
            PreparedStatement stmt1 = null;
            String sql = null;

            sql = "select stu_id, studentquiz.quiz_id, marks, total_marks\n"
                    + "from quizmarks inner join studentquiz\n"
                    + "on quizmarks.QUIZ_ID=studentquiz.QUIZ_ID \n"
                    + "where stu_id=? \n"
                    + "group by stu_id, studentquiz.quiz_id, marks, total_marks";
            stmt1 = connection.prepareStatement(sql);
            stmt1.setInt(1, stu_id);
            ResultSet rs = stmt1.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();

            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    marks = (rs.getInt("marks")) + 0.0;
                    totalMarks = (rs.getInt("total_marks")) + 0.0;
                    overallAvgMarks = overallAvgMarks + (marks / totalMarks);
                    noOfQuizes = noOfQuizes + 1.0;
                }
            }
            overallAvgMarks = overallAvgMarks / noOfQuizes;
            return overallAvgMarks;
        } catch (SQLException se) {
            //se.printStackTrace();
            throw new Exception("Error reading the count of number of test taken in last year, quarter and year as per instructor ID.", se);
        }

    }

    @Override
    public ArrayList<Double> scoresByLODForStudent(String stu_id) throws Exception {
        try (Statement stmt = connection.createStatement()) {

            ArrayList<Double> scoresByLODForStudent = new ArrayList<>();
            PreparedStatement stmt1 = null;
            String sql = null;

            sql = "SELECT questions.diff_lvl, Avg(marks) AS AvgMarks\n"
                    + "FROM (Questions INNER JOIN Quiz ON Questions.ques_id = Quiz.ques_id) \n"
                    + "INNER JOIN StudentQuiz ON Quiz.quiz_id = StudentQuiz.quiz_id\n"
                    + " where stu_id=? GROUP BY questions.diff_lvl";
            stmt1 = connection.prepareStatement(sql);
            stmt1.setString(1, stu_id);
            ResultSet rs = stmt1.executeQuery();
            while (rs.next()) {
                String lod = rs.getString("diff_lvl");
                Double stu_avg_marks = rs.getInt("AvgMarks") + 0.0;
                scoresByLODForStudent.add(stu_avg_marks);
            }
            return scoresByLODForStudent;

        } catch (SQLException se) {

            //se.printStackTrace();
            throw new Exception("Error reading the count of number of test taken in last year, quarter and year as per instructor ID.", se);

        }

    }

    @Override
    public ArrayList<Double> averageScoreOfStudent(String stu_id) throws Exception {

        try (Statement stmt = connection.createStatement()) {
            ArrayList<Double> avgScoreOfStudent = new ArrayList<>();
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
            sql = "select stu_id, studentquiz.quiz_id, avg(marks) As AvgScore, total_marks, date\n"
                    + "from quizmarks inner join studentquiz\n"
                    + "on quizmarks.QUIZ_ID=studentquiz.QUIZ_ID \n"
                    + "where stu_id=? \n"
                    + "group by stu_id, studentquiz.quiz_id, total_marks, date";

            stmt1 = connection.prepareStatement(sql);
            stmt1.setString(1, stu_id);
            int avgScore = 0;
            ResultSet rs = stmt1.executeQuery();
            while (rs.next()) {
                stu_id = rs.getString("stu_id");
                avgScore = (rs.getInt("AvgScore"));
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(rs.getDate("date").getTime());
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy",Locale.ENGLISH);
                String text = "Instructor: " + stu_id + "| Average Score: "
                        + avgScore + "| Date: " + sdf.format(cal.getTime());

                System.out.println(text);
                Calendar lastMonth1 = Calendar.getInstance();
                lastMonth1.add(Calendar.MONTH, -1);
                Calendar lastQuarter1 = Calendar.getInstance();
                lastQuarter1.add(Calendar.MONTH, -3);
                Calendar lastYear1 = Calendar.getInstance();
                lastYear1.add(Calendar.YEAR, -1);
                if (cal.after(lastMonth1)) {
                    lastMonth = lastMonth + 1.00;
                    totalMarksMonth = totalMarksMonth + avgScore;
                } else if (cal.after(lastQuarter1)) {
                    lastQuarter = lastQuarter + 1.00;
                    totalMarksQuarter = totalMarksQuarter + avgScore;
                } else if (cal.after(lastYear1)) {
                    lastYear = lastYear + 1.00;
                    totalMarksYear = totalMarksYear + avgScore;
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
            avgScoreOfStudent.add(avgMarksMonth);
            avgScoreOfStudent.add(avgMarksQuarter);
            avgScoreOfStudent.add(avgMarksYear);
            return avgScoreOfStudent;

        } catch (SQLException se) {

            //se.printStackTrace();
            throw new Exception("Error reading the count of number of testtaken in last year, quarter and year as per instructor ID.", se);

        }

    }
}
