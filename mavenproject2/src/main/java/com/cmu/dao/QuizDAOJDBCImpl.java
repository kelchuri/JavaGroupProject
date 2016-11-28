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
public class QuizDAOJDBCImpl implements QuizDAO {

    private Connection connection;

    QuizDAOJDBCImpl() {
        String url = "jdbc:derby://localhost:1527/QCASDB";

        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException se) {
            System.out.println("Error obtaining connection with the database: " + se);
            System.exit(-1);
        }
    }

    @Override
    public ArrayList<Integer> noOfCrrctQuesAsPerDiffLvlInstructor(String ins_id) throws Exception {
        try (Statement stmt = connection.createStatement()) {
            ArrayList<Integer> numberOfTestTaken = new ArrayList<>();
            Integer lastMonth = 0;
            Integer lastQuarter = 0;
            Integer lastYear = 0;
            PreparedStatement stmt1 = null;
            String sql = null;
            sql = "Select stu_id, ques_id, diff_lvl, isCorrect \n"
                    + "from Quiz inner join studentquiz \n"
                    + "on quiz.quiz_id=studentquiz.QUIZ_ID\n"
                    + "group by stu_id, ques_id, diff_lvl, isCorrect\n"
                    + "having diff_lvl='E'";
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

}
