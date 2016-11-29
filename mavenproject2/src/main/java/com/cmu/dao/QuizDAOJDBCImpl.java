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
    public TreeMap<Integer, Integer[]> noOfCrrctQuesAsPerDiffLvlInstructor(String ins_id) throws Exception {
        try (Statement stmt = connection.createStatement()) {
            TreeMap<Integer, Integer[]> s = new TreeMap<>();
            Integer countE = 0;
            Integer countM = 0;
            Integer countH = 0;

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
                        countE = countE + 1;
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
                        countM = countM + 1;
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
                        countH = countH + 1;
                    }
                }
            }
            System.out.println(countH);

            return s;
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
