
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
    private Path linePath = null;
    private File lineFile = null;

    public void readTextFile() throws IOException {
        linePath = Paths.get("tests-sample-csv.csv");
        lineFile = linePath.toFile();

        lineList = new ArrayList<>();

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
                    System.out.println(csvRecord.get(j));
                }
            }
        }
    }
}

