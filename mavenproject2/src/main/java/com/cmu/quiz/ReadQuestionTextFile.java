
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmu.quiz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author Ayushjain
 */
public class ReadQuestionTextFile {

    private ArrayList<String> lineList = null;
    private ArrayList<Questions> questionList = null;
    private Path linePath = null;
    private File lineFile = null;
    private final String FIELD_SEP = ",";

    public void readTextFile() throws IOException {
        linePath = Paths.get("tests-sample-csv.csv");
        lineFile = linePath.toFile();
        lineList = new ArrayList<>();
        questionList = new ArrayList<>();

        if (Files.exists(linePath)) // prevent the FileNotFoundException
        {
            try (BufferedReader in
                    = new BufferedReader(
                            new FileReader(lineFile))) {

                // read all quiz questions stored in the file into the array list
                String line = in.readLine();

                while (line != null) {
                    lineList.add(line);
                    line = in.readLine();
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        } else {
            System.out.println("text file does not exist");
        }

        for (int i = 0; i < lineList.size(); i++) {
            CSVParser parser = CSVParser.parse(lineList.get(i), CSVFormat.RFC4180);
            for (CSVRecord csvRecord : parser) {
                for (int j = 0; j < csvRecord.size(); j++) {
                    while (csvRecord.get(j) != null) {
                        String[] columns = csvRecord.get(j).split(FIELD_SEP);
                        String questionFormat = columns[0];
                        
                        if (questionFormat.equalsIgnoreCase("MC")) {
                            String difficultyLevel = columns[1];
                            Integer time = 60;
                            if (difficultyLevel.equalsIgnoreCase("E")) {
                                time = 60;
                            } else if (difficultyLevel.equalsIgnoreCase("M")) {
                                time = 90;
                            } else if (difficultyLevel.equalsIgnoreCase("H")) {
                                time = 120;
                            } else {

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
                            Questions ques = new Questions(questionFormat,
                                    difficultyLevel, quesDesc, option1, answer1,
                                    option2, answer2, option3, answer3, option4, answer4, time);
                            questionList.add(ques);
                        } 
                        
                        else if (questionFormat.equalsIgnoreCase("MA")) 
                            
                        {
                            String difficultyLevel = columns[1];
                            Integer time = 60;
                            if (difficultyLevel.equalsIgnoreCase("E")) {
                                time = 60;
                            } else if (difficultyLevel.equalsIgnoreCase("M")) {
                                time = 90;
                            } else if (difficultyLevel.equalsIgnoreCase("H")) {
                                time = 120;
                            } else {

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
                            Questions ques = new Questions(questionFormat,
                                    difficultyLevel, quesDesc, option1, answer1,
                                    option2, answer2, option3, answer3, option4, answer4, time);
                            questionList.add(ques);
                        } 
                        
                        else if (questionFormat.equalsIgnoreCase("TF")) 
                        
                        {
                            String difficultyLevel = columns[1];
                            Integer time = 60;
                            if (difficultyLevel.equalsIgnoreCase("E")) {
                                time = 60;
                            } else if (difficultyLevel.equalsIgnoreCase("M")) {
                                time = 90;
                            } else if (difficultyLevel.equalsIgnoreCase("H")) {
                                time = 120;
                            } else {

                            }
                            String quesDesc = columns[2];
                            String answer = columns[3];
                            Questions ques = new Questions(questionFormat,
                                    difficultyLevel, quesDesc, answer, time);
                            questionList.add(ques);
                        }
                        
                        else if (questionFormat.equalsIgnoreCase("FIB")) 
                        
                        {
                            String difficultyLevel = columns[1];
                            Integer time = 60;
                            if (difficultyLevel.equalsIgnoreCase("E")) {
                                time = 60;
                            } else if (difficultyLevel.equalsIgnoreCase("M")) {
                                time = 90;
                            } else if (difficultyLevel.equalsIgnoreCase("H")) {
                                time = 120;
                            } else {

                            }
                            String quesDesc = columns[2];
                            String answer = columns[3];
                            Questions ques = new Questions(questionFormat,
                                    difficultyLevel, quesDesc, answer, time);
                            questionList.add(ques);
                        } 
                        
                        else 
                        
                        {

                        }

                    }
                }
            }
        }

    }
}
