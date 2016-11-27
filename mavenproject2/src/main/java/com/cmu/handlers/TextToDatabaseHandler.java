
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmu.handlers;

import com.cmu.models.Questions;
import au.com.bytecode.opencsv.CSVReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;

/**
 *
 * @author Ayushjain
 */
public class TextToDatabaseHandler {

    private ArrayList<String> lineList = null;
    private ArrayList<Questions> questionList = null;
    private Path linePath = null;
    private File lineFile = null;
    private final String FIELD_SEP = ",";

    public String readTextFile(String path, String courseID) throws IOException {
        String rtrn = null;
        linePath = Paths.get(path);
        lineFile = linePath.toFile();
        lineList = new ArrayList<>();
        questionList = new ArrayList<>();

        if (Files.exists(linePath)) // prevent the FileNotFoundException
        {
            CSVReader csvr = new CSVReader(new FileReader("/Users/Ayushjain/Desktop/CMU/MISM Global Sem 1 Fall 2016/OOP in JAVA by Murli/JAVAGroupProjectGithub/1/JavaGroupProject/mavenproject2/target/classes/files/Java Questions.csv"), ',', '"', 0);
            String[] columns;
            while ((columns = csvr.readNext()) != null) {
                String questionFormat = columns[0];

                if (questionFormat.equalsIgnoreCase("MC") || questionFormat.equalsIgnoreCase("MA")) {
                    String difficultyLevel = columns[1];
                    Integer time = 60;
                    if (difficultyLevel.equalsIgnoreCase("E")) {
                        time = 60;
                    } else if (difficultyLevel.equalsIgnoreCase("M")) {
                        time = 90;
                    } else if (difficultyLevel.equalsIgnoreCase("H")) {
                        time = 120;
                    } else {
                        rtrn = "Invalid text file format.";
                        return rtrn;
                    }
                    String quesDesc = columns[2];
                    String option1 = columns[3];
                    String answer1 = columns[4];
                    String option2 = columns[5];
                    String answer2 = columns[6];
                    String option3 = columns[7];
                    String answer3 = columns[8];
                    String option4 = columns[9];
                    String answer4 = columns[10];
                    String crs_id = courseID;
                    Questions ques = new Questions(questionFormat,
                            difficultyLevel, quesDesc, option1, answer1,
                            option2, answer2, option3, answer3, option4, answer4, crs_id, time);
                    questionList.add(ques);
                } else if (questionFormat.equalsIgnoreCase("TF") || questionFormat.equalsIgnoreCase("FIB")) {
                    String difficultyLevel = columns[1];
                    Integer time = 60;
                    if (difficultyLevel.equalsIgnoreCase("E")) {
                        time = 60;
                    } else if (difficultyLevel.equalsIgnoreCase("M")) {
                        time = 90;
                    } else if (difficultyLevel.equalsIgnoreCase("H")) {
                        time = 120;
                    } else {
                        rtrn = "Invalid text file format.";
                        return rtrn;
                    }
                    String quesDesc = columns[2];
                    String answer = columns[3];
                    String crs_id = courseID;
                    Questions ques = new Questions(questionFormat,
                            difficultyLevel, quesDesc, answer, crs_id, time);
                    questionList.add(ques);
                } else {
                    rtrn = "Invalid text file format.";
                    return rtrn;
                }
            }
        } else {
            rtrn = "text file does not exist";
            return rtrn;
        }

//        for (int i = 0; i < lineList.size(); i++) {
//            CSVParser parser = CSVParser.parse(lineList.get(i), CSVFormat.RFC4180);
//            for (CSVRecord csvRecord : parser) {
//                for (int j = 0; j < csvRecord.size(); j++) {
//                    while (csvRecord.get(j) != null) {
//                        String[] columns = csvRecord.get(j).split(FIELD_SEP);
//                        String questionFormat = columns[0];
//
//                        if (questionFormat.equalsIgnoreCase("MC")) {
//                            String difficultyLevel = columns[1];
//                            Integer time = 60;
//                            if (difficultyLevel.equalsIgnoreCase("E")) {
//                                time = 60;
//                            } else if (difficultyLevel.equalsIgnoreCase("M")) {
//                                time = 90;
//                            } else if (difficultyLevel.equalsIgnoreCase("H")) {
//                                time = 120;
//                            } else {
//
//                            }
//                            String quesDesc = columns[2];
//                            String option1 = columns[3];
//                            String answer1 = columns[4];
//                            String option2 = columns[5];
//                            String answer2 = columns[6];
//                            String option3 = columns[7];
//                            String answer3 = columns[8];
//                            String option4 = columns[9];
//                            String answer4 = columns[10];
//                            Questions ques = new Questions(questionFormat,
//                                    difficultyLevel, quesDesc, option1, answer1,
//                                    option2, answer2, option3, answer3, option4, answer4, time);
//                            questionList.add(ques);
//                        } else if (questionFormat.equalsIgnoreCase("MA")) {
//                            String difficultyLevel = columns[1];
//                            Integer time = 60;
//                            if (difficultyLevel.equalsIgnoreCase("E")) {
//                                time = 60;
//                            } else if (difficultyLevel.equalsIgnoreCase("M")) {
//                                time = 90;
//                            } else if (difficultyLevel.equalsIgnoreCase("H")) {
//                                time = 120;
//                            } else {
//
//                            }
//                            String quesDesc = columns[2];
//                            String option1 = columns[3];
//                            String answer1 = columns[4];
//                            String option2 = columns[5];
//                            String answer2 = columns[6];
//                            String option3 = columns[7];
//                            String answer3 = columns[8];
//                            String option4 = columns[9];
//                            String answer4 = columns[10];
//                            Questions ques = new Questions(questionFormat,
//                                    difficultyLevel, quesDesc, option1, answer1,
//                                    option2, answer2, option3, answer3, option4, answer4, time);
//                            questionList.add(ques);
//                        } else if (questionFormat.equalsIgnoreCase("TF")) {
//                            String difficultyLevel = columns[1];
//                            Integer time = 60;
//                            if (difficultyLevel.equalsIgnoreCase("E")) {
//                                time = 60;
//                            } else if (difficultyLevel.equalsIgnoreCase("M")) {
//                                time = 90;
//                            } else if (difficultyLevel.equalsIgnoreCase("H")) {
//                                time = 120;
//                            } else {
//
//                            }
//                            String quesDesc = columns[2];
//                            String answer = columns[3];
//                            Questions ques = new Questions(questionFormat,
//                                    difficultyLevel, quesDesc, answer, time);
//                            questionList.add(ques);
//                        } else if (questionFormat.equalsIgnoreCase("FIB")) {
//                            String difficultyLevel = columns[1];
//                            Integer time = 60;
//                            if (difficultyLevel.equalsIgnoreCase("E")) {
//                                time = 60;
//                            } else if (difficultyLevel.equalsIgnoreCase("M")) {
//                                time = 90;
//                            } else if (difficultyLevel.equalsIgnoreCase("H")) {
//                                time = 120;
//                            } else {
//
//                            }
//                            String quesDesc = columns[2];
//                            String answer = columns[3];
//                            Questions ques = new Questions(questionFormat,
//                                    difficultyLevel, quesDesc, answer, time);
//                            questionList.add(ques);
//                        } else {
//
//                        }
//
//                    }
//                }
//            }
//        }
        return rtrn;

    }

    private static void addQuesToDB(ArrayList questinoList) throws Throwable {
        PreparedStatement stmt = null;
        Connection conn = null;
        String dbURL = "jdbc:derby://localhost:1527/QCASDB;create=true";
        conn = DriverManager.getConnection(dbURL);
        for (Object object : questinoList) {
            String ques_type = "";
            String diff_lvl = "";
            String ques_desc = "";
            String option1 = "";
            String answer1 = "";
            String option2 = "";
            String answer2 = "";
            String option3 = "";
            String answer3 = "";
            String option4 = "";
            String answer4 = "";
            String answer = "";
            String ques_id = "";
            String crs_id = "";
            Questions q = (Questions) object;
            ques_type = q.getQues_type();
            int time = (int) q.getTime();

            switch (ques_type) {
                case "MC":
                    diff_lvl = q.getDiff_lvl();
                    ques_desc = q.getQues_desc();
                    option1 = q.getOption1();
                    answer1 = q.getAnswer1();
                    option2 = q.getOption2();
                    answer2 = q.getAnswer2();
                    option3 = q.getOption3();
                    answer3 = q.getAnswer3();
                    option4 = q.getOption4();
                    answer4 = q.getAnswer4();
                    crs_id = q.getCrs_id();
                    break;
                case "MA":
                    diff_lvl = q.getDiff_lvl();
                    ques_desc = q.getQues_desc();
                    option1 = q.getOption1();
                    answer1 = q.getAnswer1();
                    option2 = q.getOption2();
                    answer2 = q.getAnswer2();
                    option3 = q.getOption3();
                    answer3 = q.getAnswer3();
                    option4 = q.getOption4();
                    answer4 = q.getAnswer4();
                    break;
                case "FIB":
                    diff_lvl = q.getDiff_lvl();
                    ques_desc = q.getQues_desc();
                    answer = q.getAnswer();
                    break;
                case "TF":
                    diff_lvl = q.getDiff_lvl();
                    ques_desc = q.getQues_desc();
                    answer = q.getAnswer();
                    break;
            }

            String addRowSql = "INSERT INTO APP.QUESTIONS("
                    + "ques_id, ques_type, diff_lvl, ques_desc, "
                    + "option1, answer1, option2, answer2, option3, "
                    + "answer3, option4, answer4, answer, crs_id, time)"
                    + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            stmt = conn.prepareStatement(addRowSql);
            stmt.setString(1, ques_id);
            stmt.setString(2, ques_type);
            stmt.setString(3, diff_lvl);
            stmt.setString(4, ques_desc);
            stmt.setString(5, option1);
            stmt.setString(6, answer1);
            stmt.setString(7, option2);
            stmt.setString(8, answer2);
            stmt.setString(9, option3);
            stmt.setString(10, answer3);
            stmt.setString(11, option4);
            stmt.setString(12, answer4);
            stmt.setString(13, answer);
            stmt.setString(14, crs_id);
            stmt.setInt(15, time);

            stmt.executeUpdate();
        }
    }
}
