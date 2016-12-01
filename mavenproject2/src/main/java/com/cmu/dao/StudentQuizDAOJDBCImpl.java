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

    /**
     * this constructor creates the connection to the database
     *
     */
    StudentQuizDAOJDBCImpl() {
        String url = "jdbc:derby:QCASDB;create=true";

        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException se) {
            System.out.println("Error obtaining connection with the database: " + se);
            System.exit(-1);
        }
    }

    /**
     * this method adds the student quiz to the database
     *
     * @return
     */
    @Override
    public String addStudentQuiz() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * this method updates the student quiz record in the database
     *
     * @return
     */
    @Override
    public String updateStudentQuiz() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * this method deletes the student quiz records
     *
     * @return
     */
    @Override
    public String deleteStudentQuiz() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * this method gets the number of quiz taken by the students in a particular
     * instructor in last month, quarter and year
     *
     * @param ins_id
     * @return
     * @throws java.lang.Exception
     */
    @Override
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

                Calendar lastMonth1 = Calendar.getInstance();
                lastMonth1.add(Calendar.MONTH, -1);
                Calendar lastQuarter1 = Calendar.getInstance();
                lastQuarter1.add(Calendar.MONTH, -3);
                Calendar lastYear1 = Calendar.getInstance();
                lastYear1.add(Calendar.YEAR, -1);

                if (cal.after(lastMonth1)) {
                    lastMonth = lastMonth + quizCount;
                    lastQuarter = lastQuarter + quizCount;
                    lastYear = lastYear + quizCount;
                } else if (cal.after(lastQuarter1)) {
                    lastQuarter = lastQuarter + quizCount;
                    lastYear = lastYear + quizCount;
                } else if (cal.after(lastYear1)) {
                    lastYear = lastYear + quizCount;
                }
            }

            numberOfTestTaken.add(lastMonth);
            numberOfTestTaken.add(lastQuarter);
            numberOfTestTaken.add(lastYear);
            return numberOfTestTaken;
        } catch (SQLException se) {
            //se.printStackTrace();
            throw new Exception("Error reading the number of quiz taken by the "
                    + "students in a particular instructor in last month, quarter"
                    + " and year.", se);
        }
    }

    /**
     * this method close all the active connections to the database
     *
     */
    @Override
    public void close() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * this method gets the number of passed and failed test by the student
     *
     * @param stu_id
     * @return
     * @throws java.lang.Throwable
     */
    @Override
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
            throw new Exception("Error reading the the number of passed and "
                    + "failed test by the student.", se);
        }

    }

    /**
     * this method gets the list of average scores of students under the
     * instructor for last month, quarter and year
     *
     * @param ins_id
     * @return
     * @throws java.lang.Exception
     */
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
                    lastQuarter = lastQuarter + 1.00;
                    totalMarksQuarter = totalMarksQuarter + quizMarks;
                    lastYear = lastYear + 1.00;
                    totalMarksYear = totalMarksYear + quizMarks;
                } else if (cal.after(lastQuarter1)) {
                    lastQuarter = lastQuarter + 1.00;
                    totalMarksQuarter = totalMarksQuarter + quizMarks;
                    lastYear = lastYear + 1.00;
                    totalMarksYear = totalMarksYear + quizMarks;
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
            throw new Exception("Error reading the list of average scores of "
                    + "students under the instructor for last month, quarter and "
                    + "year.", se);
        }
    }

    /**
     * this method gets number of quiz taken by the student in last month, 
     * quarter and year
     *
     * @param stu_id
     * @return
     * @throws java.lang.Exception
     */
    @Override
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
            ResultSet rs = stmt1.executeQuery();

            while (rs.next()) {
                stu_id = rs.getInt("stu_id");

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
            throw new Exception("Error reading the number of quiz taken by the"
                    + " student in last month, quarter and year.", se);
        }
    }

    /**
     * this method returns the number of student in a particular course
     *
     * @param crs_id
     * @return
     * @throws java.lang.Throwable
     */
    @Override
    public int studentCountByCourse(int crs_id) throws Throwable {

        try (Statement stmt = connection.createStatement()) {
            int columnValue = 0;
            String sql = null;

            PreparedStatement stmt1 = null;
            sql = "select Count(stu_id) AS StudentCount"
                    + " from studentquiz"
                    + " where crs_id=?"
                    + " group by crs_id";
            stmt1 = connection.prepareStatement(sql);
            stmt1.setInt(1, crs_id);
            ResultSet rs = stmt1.executeQuery();

            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) {
                        System.out.print(",  ");
                    }
                    columnValue = rs.getInt(i);

                    // System.out.print(columnValue + " " + rsmd.getColumnName(i));
                }

                System.out.println("COLU PRINT:" + columnValue);
            }
            return columnValue;

        } catch (SQLException se) {
            //se.printStackTrace();
            throw new Exception("Error reading the number of student in a particular course.", se);
        }
    }

    /**
     * this method gets the overall average score of a student in all the quiz taken
     *
     * @param stu_id
     * @return
     * @throws java.lang.Exception
     */
    @Override
    public double overallAvgMarksStudent(int stu_id) throws Exception {

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
            System.out.println("cols :" + columnsNumber);
            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    marks = (rs.getInt("marks")) + 0.0;
                    System.out.println("Marks are" + marks);
                    totalMarks = (rs.getInt("total_marks")) + 0.0;
                    System.out.println("Total marks" + totalMarks);
                    overallAvgMarks = overallAvgMarks + (marks / totalMarks);
                    System.out.println("overall marks" + overallAvgMarks);
                    noOfQuizes = noOfQuizes + 1.0;
                    System.out.println("Total quiz" + noOfQuizes);
                }
            }
            overallAvgMarks = overallAvgMarks / noOfQuizes;
            System.out.println("Inside query" + overallAvgMarks);
            return overallAvgMarks;
        } catch (SQLException se) {
            //se.printStackTrace();
            throw new Exception("Error reading the overall average score of a"
                    + " student in all the quiz taken.", se);
        }

    }

    /**
     * this method return the list of scores by the level of difficulty for the student
     *
     * @param stu_id
     * @return
     * @throws java.lang.Exception
     */
    @Override
    public ArrayList<Double> scoresByLODForStudent(int stu_id) throws Exception {
        try (Statement stmt = connection.createStatement()) {

            ArrayList<Double> scoresByLODForStudent = new ArrayList<>();
            PreparedStatement stmt1 = null;
            String sql = null;
            Double stu_avg_marks = 0.0;
            sql = "SELECT questions.diff_lvl, Avg(marks) AS AvgMarks\n"
                    + "FROM (Questions INNER JOIN Quiz ON Questions.ques_id = Quiz.ques_id) \n"
                    + "INNER JOIN StudentQuiz ON Quiz.quiz_id = StudentQuiz.quiz_id\n"
                    + " where stu_id=? GROUP BY questions.diff_lvl";
            stmt1 = connection.prepareStatement(sql);
            stmt1.setInt(1, stu_id);
            ResultSet rs = stmt1.executeQuery();
            while (rs.next()) {
                String lod = rs.getString("diff_lvl");
                stu_avg_marks = rs.getInt("AvgMarks") + 0.0;
                scoresByLODForStudent.add(stu_avg_marks);
            }
            if (scoresByLODForStudent.isEmpty()) {
                scoresByLODForStudent.add(stu_avg_marks);
                scoresByLODForStudent.add(stu_avg_marks);
                scoresByLODForStudent.add(stu_avg_marks);
            }
            return scoresByLODForStudent;

        } catch (SQLException se) {

            //se.printStackTrace();
            throw new Exception("Error reading the list of scores by the level "
                    + "of difficulty for the student.", se);

        }

    }

    /**
     * this method gets the list of average score of student in last month, 
     * quarter and year
     *
     * @param stu_id
     * @return
     * @throws java.lang.Exception
     */
    @Override
    public ArrayList<Double> averageScoreOfStudent(int stu_id) throws Exception {

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
            stmt1.setInt(1, stu_id);
            int avgScore = 0;
            ResultSet rs = stmt1.executeQuery();
            while (rs.next()) {
                stu_id = rs.getInt("stu_id");
                avgScore = (rs.getInt("AvgScore"));
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(rs.getDate("date").getTime());
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
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
                    lastQuarter = lastQuarter + 1.00;
                    lastYear = lastYear + 1.00;
                    totalMarksYear = totalMarksYear + avgScore;
                    totalMarksQuarter = totalMarksQuarter + avgScore;
                } else if (cal.after(lastQuarter1)) {
                    lastQuarter = lastQuarter + 1.00;
                    totalMarksQuarter = totalMarksQuarter + avgScore;
                    lastYear = lastYear + 1.00;
                    totalMarksYear = totalMarksYear + avgScore;
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
            throw new Exception("Error reading the list of average score of "
                    + "student in last month, quarter and year.", se);

        }

    }

    /**
     * this method return the list of pass and fail student in a particular course under an instructor
     *
     * @param ins_id
     * @return
     * @throws java.lang.Throwable
     */
    @Override
    public ArrayList<Double> passFailInstructor(int ins_id) throws Throwable {

        try (Statement stmt = connection.createStatement()) {
            studentMarksList = new ArrayList<>();
            Double pass = 0.00;
            Double fail = 0.00;
            Double marks = 0.00;
            Double totalMarks = 0.00;
            PreparedStatement stmt1 = null;
            String sql = null;

            sql = "SELECT ins_id, StudentQuiz.stu_id, StudentQuiz.quiz_id,\n"
                    + "StudentQuiz.marks, Total_Marks\n"
                    + "FROM (quizmarks INNER JOIN StudentQuiz \n"
                    + "ON quizmarks.quiz_id = StudentQuiz.quiz_id) \n"
                    + "INNER JOIN Course \n"
                    + "ON StudentQuiz.crs_id = Course.crs_id\n"
                    + "where ins_id=? GROUP BY stu_id, StudentQuiz.quiz_id, \n"
                    + "marks, Total_Marks, ins_id";
            stmt1 = connection.prepareStatement(sql);
            stmt1.setInt(1, ins_id);
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
            throw new Exception("Error reading the list of pass and fail student"
                    + " in a particular course under an instructor.", se);
        }

    }
}
