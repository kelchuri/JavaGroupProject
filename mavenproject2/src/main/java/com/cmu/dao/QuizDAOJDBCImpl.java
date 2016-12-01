/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmu.dao;

import com.cmu.models.Questions;
import com.cmu.models.Quiz;
import com.cmu.models.User;
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
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Ayushjain
 */
public class QuizDAOJDBCImpl implements QuizDAO {

    private Connection connection;

    QuizDAOJDBCImpl() {
        String url = "jdbc:derby:QCASDB;create=true";

        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException se) {
            System.out.println("Error obtaining connection with the database: " + se);
            System.exit(-1);
        }
    }

    @Override
    public ArrayList<Double> noOfCrrctQuesAsPerDiffLvlInstructor(int ins_id) throws Exception {
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
            stmt1.setInt(1, ins_id);
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
            stmt1.setInt(1, ins_id);
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
            stmt1.setInt(1, ins_id);
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
    public ArrayList<Questions> getQuizQuestion(int NoQ, int crs_id,
            String diff_lvl) throws Exception {
        try (Statement stmt = connection.createStatement()) {
            ArrayList<Questions> questionList = new ArrayList<>();
            System.out.println(NoQ + " " + crs_id + " " + diff_lvl);
            PreparedStatement stmt1 = null;
            String sql = null;
            ResultSet rs = null;
            ResultSetMetaData rsmd = null;
            Integer rndm1 = 0;
            Integer rndm2 = 0;
            Integer rndm3 = 0;
            Integer rndm4 = 0;
            if (diff_lvl.equalsIgnoreCase("E")
                    || diff_lvl.equalsIgnoreCase("M")
                    || diff_lvl.equalsIgnoreCase("H")) {
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
                rs = stmt1.executeQuery();
                rsmd = rs.getMetaData();
            } else {
                sql = "Select ques_id, ques_type,diff_lvl,ques_desc,\n"
                        + "option1,answer1,option2,answer2,option3,answer3,\n"
                        + "option4,answer4,answer,crs_id,time\n"
                        + "from questions where\n"
                        + "crs_id=?\n"
                        + "ORDER BY RANDOM() OFFSET 0 ROWS FETCH NEXT ? ROWS ONLY";
                stmt1 = connection.prepareStatement(sql);
                stmt1.setInt(1, crs_id);
                stmt1.setInt(2, NoQ);
                rs = stmt1.executeQuery();

                rsmd = rs.getMetaData();
            }
            int columnsNumber = rsmd.getColumnCount();
            while (rs.next()) {
                int ques_ID = (rs.getInt(1));
                String ques_type = rs.getString(2);
                String rtrn;
                if (ques_type.equalsIgnoreCase("MC")
                        || ques_type.equalsIgnoreCase("MA")) {
                    String difficultyLevel = rs.getString(3);
                    String quesDesc = rs.getString(4);

                    String option1 = "";
                    String answer1 = "";
                    String option2 = "";
                    String answer2 = "";
                    String option3 = "";
                    String answer3 = "";
                    String option4 = "";
                    String answer4 = "";

                    Boolean flag = true;
                    while (flag) {
                        rndm1 = (5 + (int) (Math.random() * 8));
                        if (rndm1 != 6 && rndm1 != 8 && rndm1 != 10
                                && rndm1 != 12) {
                            flag = false;
                        }
                    }

                    flag = true;
                    while (flag) {
                        rndm2 = (5 + (int) (Math.random() * 8));
                        if (rndm1 != rndm2 && rndm2 != 6 && rndm2 != 8
                                && rndm2 != 10 && rndm2 != 12) {
                            flag = false;
                        }
                    }

                    flag = true;
                    while (flag) {
                        rndm3 = (5 + (int) (Math.random() * 8));
                        if (rndm1 != rndm3 && rndm2 != rndm3 && rndm3 != 6
                                && rndm3 != 8 && rndm3 != 10 && rndm3 != 12) {
                            flag = false;
                        }
                    }

                    flag = true;
                    while (flag) {
                        rndm4 = (5 + (int) (Math.random() * 8));
                        if (rndm1 != rndm4 && rndm2 != rndm4 && rndm3
                                != rndm4 && rndm4 != 6 && rndm4 != 8 && rndm4 != 10
                                && rndm4 != 12) {
                            flag = false;
                        }
                    }

                    option1 = rs.getString(rndm1);
                    answer1 = rs.getString(rndm1 + 1);
                    option2 = rs.getString(rndm2);
                    answer2 = rs.getString(rndm2 + 1);
                    option3 = rs.getString(rndm3);
                    answer3 = rs.getString(rndm3 + 1);
                    option4 = rs.getString(rndm4);
                    answer4 = rs.getString(rndm4 + 1);

                    int crs_id1 = rs.getInt(14);
                    Integer time = rs.getInt(15);

                    Questions ques = new Questions(ques_type,
                            difficultyLevel, quesDesc, option1, answer1,
                            option2, answer2, option3, answer3, option4,
                            answer4, crs_id1, time);
                    questionList.add(ques);

                } else if (ques_type.equalsIgnoreCase("TF")
                        || ques_type.equalsIgnoreCase("FIB")) {
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
            throw new Exception("Error reading the count of number of ten in last year, quarter and year as per instructor ID.", se);
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
            String sql = "SELECT stu_id, Count(quiz_id) AS QuizCount\n"
                    + "FROM StudentQuiz\n"
                    + "where stu_id=? GROUP BY stu_id";
            stmt = connection.prepareStatement(sql);
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

    private int getQuizId() throws Exception {
        int quizCount = 0;
        try (Statement stmt = connection.createStatement()) {
            String query = "select count(distinct(quiz_id)) as count from quiz";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                quizCount = rs.getInt("Count");
            }
            System.out.println(quizCount);
            return quizCount + 1;
        } catch (SQLException se) {
            //se.printStackTrace();
            throw new Exception("Error finding Count in DAO", se);
        }
    }

    public void addIntoStudentQuiz(int quiz_id, User student, int marks, int course_id) throws Exception {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
        java.sql.Date date = new java.sql.Date(cal.getTime().getTime());
        Calendar cal1 = new GregorianCalendar();
        cal.setTimeInMillis(date.getTime());

        String addRowSql = "INSERT INTO APP.STUDENTQUIZ("
                + "stu_id,quiz_id,marks, date, crs_id)"
                + "VALUES(?,?,?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(addRowSql)) {
            stmt.setInt(1, student.getUserId());
            stmt.setInt(2, quiz_id);
            stmt.setInt(3, marks);
            stmt.setDate(4, date);
            stmt.setInt(5, course_id);
            stmt.executeUpdate();
        } catch (SQLException se) {
            //se.printStackTrace();
            throw new Exception("Error finding Count in DAO", se);
        }

    }

    @Override
    public void addIntoDB(List<Quiz> quiz, User student, int course_id) throws Throwable {
        int quiz_id = getQuizId();
        int correct = 0;
        String addRowSql = "INSERT INTO APP.QUIZ("
                + "quiz_id,ques_id,diff_lvl,isCorrect)"
                + "VALUES(?,?,?,?)";
        PreparedStatement statement = null;
        for (Quiz question : quiz) {
            question.setQuizID(quiz_id);

            statement = connection.prepareStatement(addRowSql);
            statement.setInt(1, quiz_id);
            statement.setInt(2, question.getQuestionNo());
            statement.setString(3, question.getDiffLevel());
            statement.setBoolean(4, question.getIsCorrect());
            statement.executeUpdate();
            if (question.getIsCorrect()) {
                correct++;
            }
        }
        addIntoStudentQuiz(quiz_id, student, correct * 3, course_id);
        //dropTblQuizMarks();
        //createQuizMarks();
        insertToQuizMarks();
    }

    private void createQuizMarks() throws Throwable {
        try {
            String sql = "CREATE TABLE quizMarks("
                    + "quiz_id DECIMAL,"
                    + "total_marks DECIMAL)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.executeUpdate();
        } catch (Exception e) {

        }
    }

    private void insertToQuizMarks() throws Throwable {
        String sql1 = "SELECT quiz_id, Count(ques_id) AS Ques_Count\n"
                + "FROM Quiz GROUP BY quiz_id";
        String sql2 = "INSERT INTO quizMarks(" + "quiz_id," + "total_marks)" + "VALUES(?,?)";

        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        PreparedStatement stmt = connection.prepareStatement(sql2);
        ResultSet rs = statement.executeQuery(sql1);
        while (rs.next()) {
            int quiz_id = rs.getInt("quiz_id");
            int ques_count = rs.getInt("ques_count");
            int total_marks = ques_count * 3;
            stmt.setInt(1, quiz_id);
            stmt.setInt(2, total_marks);
            stmt.executeUpdate();
        }

    }

    private void dropTblQuizMarks() throws Throwable {
        try {
            String sql = "drop table quizmarks";

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.executeUpdate();
        } catch (Exception e) {

        }
    }

}
