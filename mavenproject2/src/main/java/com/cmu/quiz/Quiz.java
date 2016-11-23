/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmu.quiz;

import java.util.Calendar;

/**
 *
 * @author Ayushjain
 */
public class Quiz {
    private Integer ID;
    private String numberOfMC;
    private String numberOfMA;
    private String numberOfTF;
    private String numberOfFIB;
    private Calendar dateQuizTaken;
    private Integer totalMCCorrect;
    private Integer totalMACorrect;
    private Integer totalTFCorrect;
    private Integer totalFIBCorrect;
    private Double time;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getNumberOfMC() {
        return numberOfMC;
    }

    public void setNumberOfMC(String numberOfMC) {
        this.numberOfMC = numberOfMC;
    }

    public String getNumberOfMA() {
        return numberOfMA;
    }

    public void setNumberOfMA(String numberOfMA) {
        this.numberOfMA = numberOfMA;
    }

    public String getNumberOfTF() {
        return numberOfTF;
    }

    public void setNumberOfTF(String numberOfTF) {
        this.numberOfTF = numberOfTF;
    }

    public String getNumberOfFIB() {
        return numberOfFIB;
    }

    public void setNumberOfFIB(String numberOfFIB) {
        this.numberOfFIB = numberOfFIB;
    }

    public Calendar getDateQuizTaken() {
        return dateQuizTaken;
    }

    public void setDateQuizTaken(Calendar dateQuizTaken) {
        this.dateQuizTaken = dateQuizTaken;
    }

    public Integer getTotalMCCorrect() {
        return totalMCCorrect;
    }

    public void setTotalMCCorrect(Integer totalMCCorrect) {
        this.totalMCCorrect = totalMCCorrect;
    }

    public Integer getTotalMACorrect() {
        return totalMACorrect;
    }

    public void setTotalMACorrect(Integer totalMACorrect) {
        this.totalMACorrect = totalMACorrect;
    }

    public Integer getTotalTFCorrect() {
        return totalTFCorrect;
    }

    public void setTotalTFCorrect(Integer totalTFCorrect) {
        this.totalTFCorrect = totalTFCorrect;
    }

    public Integer getTotalFIBCorrect() {
        return totalFIBCorrect;
    }

    public void setTotalFIBCorrect(Integer totalFIBCorrect) {
        this.totalFIBCorrect = totalFIBCorrect;
    }

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }
    
    
}
