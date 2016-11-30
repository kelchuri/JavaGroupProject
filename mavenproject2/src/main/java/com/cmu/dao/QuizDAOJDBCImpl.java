/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmu.dao;

import com.cmu.models.Questions;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

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
    public ArrayList<Double> noOfCrrctQuesAsPerDiffLvlInstructor(String ins_id) throws Exception {
        try (Statement stmt = connection.createStatement()) {
            TreeMap<Integer, Integer[]> s = new TreeMap<>();
            ArrayList<Double> a = new ArrayList<>();
            Double countE = 0.00;
            Double countM = 0.00;
            Double countH = 0.00;

            PreparedStatement stmt1 = null;
            String sql = null;
            sql = "select stu_id, ins_id, ques_id, diff_lvl, isCorrect\n"
                    + "from (studentquiz inner join quiz on studentquiz.quiz_id=quiz.quiz_id)\n"
                    + "inner join course on studentquiz.crs_id=course.CRS_ID\n"
                    + "group by stu_id, ques_id, diff_lvl, isCorrect, ins_id\n"
                    + "having(diff_lvl='E' AND ins_id=?)";
            stmt1 = connection.prepareStatement(sql);
            stmt1.setString(1, ins_id);
//            int quizCount = 0;
            ResultSet rs = stmt1.executeQuery();

            while (rs.next()) {
                int stu_id = rs.getInt("stu_id");
                int ques_id = rs.getInt("ques_id");
                String diff_lvl = rs.getString("diff_lvl");
                Boolean isCorrect = rs.getBoolean("isCorrect");
                String text = "Student Id: " + stu_id + "| Question Id: " + ques_id + " |Difficulty Level: " + diff_lvl + "| Result: " + isCorrect;

//                System.out.println(text);
                Integer[] count = new Integer[2];

                if (s.isEmpty() || !s.containsKey(stu_id)) {
                    if (isCorrect) {
                        count[0] = 1;
                        count[1] = 0;
                    } else {
                        count[0] = 0;
                        count[1] = 1;
                    }
                    s.put(stu_id, count);
                } else {
                    count = s.get(stu_id);
                    if (isCorrect) {
                        count[0] = count[0] + 1;
                    } else {
                        count[1] = count[1] + 1;
                    }
                    s.replace(stu_id, count);
                }
            }

            if (!s.isEmpty()) {
                for (Map.Entry<Integer, Integer[]> entry : s.entrySet()) {
                    Integer key = entry.getKey();
                    Integer[] value = entry.getValue();
                    if (((value[0] * 100.00) / (value[0] + value[1])) >= 50) {
                        countE = countE + 1.00;
                    }
                }
            }
            System.out.println(countE);

            sql = "select stu_id, ins_id, ques_id, diff_lvl, isCorrect\n"
                    + "from (studentquiz inner join quiz on studentquiz.quiz_id=quiz.quiz_id)\n"
                    + "inner join course on studentquiz.crs_id=course.CRS_ID\n"
                    + "group by stu_id, ques_id, diff_lvl, isCorrect, ins_id\n"
                    + "having(diff_lvl='M' AND ins_id=?)";
            stmt1 = connection.prepareStatement(sql);
            stmt1.setString(1, ins_id);
            rs = stmt1.executeQuery();

            while (rs.next()) {
                int stu_id = rs.getInt("stu_id");
                int ques_id = rs.getInt("ques_id");
                String diff_lvl = rs.getString("diff_lvl");
                Boolean isCorrect = rs.getBoolean("isCorrect");
                String text = "Student Id: " + stu_id + "| Question Id: " + ques_id + " |Difficulty Level: " + diff_lvl + "| Result: " + isCorrect;

//                System.out.println(text);
                Integer[] count = new Integer[2];

                if (s.isEmpty() || !s.containsKey(stu_id)) {
                    if (isCorrect) {
                        count[0] = 1;
                        count[1] = 0;
                    } else {
                        count[0] = 0;
                        count[1] = 1;
                    }
                    s.put(stu_id, count);
                } else {
                    count = s.get(stu_id);
                    if (isCorrect) {
                        count[0] = count[0] + 1;
                    } else {
                        count[1] = count[1] + 1;
                    }
                    s.replace(stu_id, count);
                }
            }

            if (!s.isEmpty()) {
                for (Map.Entry<Integer, Integer[]> entry : s.entrySet()) {
                    Integer key = entry.getKey();
                    Integer[] value = entry.getValue();
                    if (((value[0] * 100.00) / (value[0] + value[1])) >= 50) {
                        countM = countM + 1.00;
                    }
                }
            }
            System.out.println(countM);

            sql = "select stu_id, ins_id, ques_id, diff_lvl, isCorrect\n"
                    + "from (studentquiz inner join quiz on studentquiz.quiz_id=quiz.quiz_id)\n"
                    + "inner join course on studentquiz.crs_id=course.CRS_ID\n"
                    + "group by stu_id, ques_id, diff_lvl, isCorrect, ins_id\n"
                    + "having(diff_lvl='H' AND ins_id=?)";
            stmt1 = connection.prepareStatement(sql);
            stmt1.setString(1, ins_id);
            rs = stmt1.executeQuery();

            while (rs.next()) {
                int stu_id = rs.getInt("stu_id");
                int ques_id = rs.getInt("ques_id");
                String diff_lvl = rs.getString("diff_lvl");
                Boolean isCorrect = rs.getBoolean("isCorrect");
                String text = "Student Id: " + stu_id + "| Question Id: " + ques_id + " |Difficulty Level: " + diff_lvl + "| Result: " + isCorrect;

//                System.out.println(text);
                Integer[] count = new Integer[2];

                if (s.isEmpty() || !s.containsKey(stu_id)) {
                    if (isCorrect) {
                        count[0] = 1;
                        count[1] = 0;
                    } else {
                        count[0] = 0;
                        count[1] = 1;
                    }
                    s.put(stu_id, count);
                } else {
                    count = s.get(stu_id);
                    if (isCorrect) {
                        count[0] = count[0] + 1;
                    } else {
                        count[1] = count[1] + 1;
                    }
                    s.replace(stu_id, count);
                }
            }

            if (!s.isEmpty()) {
                for (Map.Entry<Integer, Integer[]> entry : s.entrySet()) {
                    Integer key = entry.getKey();
                    Integer[] value = entry.getValue();
                    if (((value[0] * 100.00) / (value[0] + value[1])) >= 50) {
                        countH = countH + 1.00;
                    }
                }
            }
            System.out.println(countH);

            a.add(countE);
            a.add(countM);
            a.add(countH);
            
            return a;
        } catch (SQLException se) {
            //se.printStackTrace();
            throw new Exception("Error reading the count of number of test taken in last year, quarter and year as per instructor ID.", se);
        }
    }

    @Override
    public ArrayList<Questions> getQuizQuestion(int NoQ, int crs_id, String diff_lvl) throws Exception {
        try (Statement stmt = connection.createStatement()) {
            ArrayList<Questions> questionList = new ArrayList<>();
            System.out.println(NoQ + " " + crs_id + " " +  diff_lvl);
            PreparedStatement stmt1 = null;
            String sql = null;
            sql = "Select ques_id, ques_type,diff_lvl,ques_desc,\n"
                    + "option1,answer1,option2,answer2,option3,answer3,\n"
                    + "option4,answer4,answer,crs_id,time\n"
                    + "from questions where\n"
                    + "crs_id=? and diff_lvl=?\n"
                    + "ORDER BY RANDOM() OFFSET 0 ROWS FETCH NEXT ? ROWS ONLY";
            stmt1 = connection.prepareStatement(sql);
            stmt1.setInt(1, crs_id);
            stmt1.setString(2, diff_lvl);
            stmt1.setInt(3, NoQ);
            ResultSet rs = stmt1.executeQuery();

            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (rs.next()) {
                System.out.println(rs.getInt(1));
                int ques_ID = (rs.getInt(1));
                
                String ques_type = rs.getString(2);
                String rtrn;
                if (ques_type.equalsIgnoreCase("MC") || ques_type.equalsIgnoreCase("MA")) {
                    String difficultyLevel = rs.getString(3);
                    String quesDesc = rs.getString(4);
                    String option1 = rs.getString(5);
                    String answer1 = rs.getString(6);
                    String option2 = rs.getString(7);
                    String answer2 = rs.getString(8);
                    String option3 = rs.getString(9);
                    String answer3 = rs.getString(10);
                    String option4 = rs.getString(11);
                    String answer4 = rs.getString(12);
                    int crs_id1 = rs.getInt(14);
                    Integer time = rs.getInt(15);

                    Questions ques = new Questions(ques_type,
                            difficultyLevel, quesDesc, option1, answer1,
                            option2, answer2, option3, answer3, option4, answer4, crs_id1, time);
                    questionList.add(ques);
                    
                } else if (ques_type.equalsIgnoreCase("TF") || ques_type.equalsIgnoreCase("FIB")) {
                    String difficultyLevel = rs.getString(3);
                    String quesDesc = rs.getString(4);
                    String answer = rs.getString(13);
                    int crs_id1 = rs.getInt(14);
                    Integer time = rs.getInt(15);
                    
                    Questions ques = new Questions(ques_type,
                            difficultyLevel, quesDesc, answer, crs_id1, time);
                    questionList.add(ques);
                    
                }
            }
            return questionList;

        } catch (SQLException se) {
            //se.printStackTrace();
            throw new Exception("Error reading the count of number of test taken in last year, quarter and year as per instructor ID.", se);
        }
    }

    @Override
    public void close() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int studentQuiz(int stu_id) throws Throwable {
        int quizCount = 0;
        try {

            PreparedStatement stmt = null;
            Connection conn = null;
            String dbURL = "jdbc:derby://localhost:1527/QCASDB;create=true";
            conn = DriverManager.getConnection(dbURL);

            String sql = "SELECT stu_id, Count(quiz_id) AS QuizCount\n"
                    + "FROM StudentQuiz\n"
                    + "where stu_id=? GROUP BY stu_id";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, stu_id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                quizCount = rs.getInt("QuizCount");
                String text = "Student Id: " + stu_id + "| Quiz Count: " + quizCount;
                System.out.println(text);
                
            }
            return quizCount;
        } catch (Exception ex) {

            throw new Exception("Total number of quizzes cannot be retrieved", ex);
        }
     
    }


}
