
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmu.models;

import java.util.Calendar;

/**
 *
 * @author Ayushjain
 */
public class Quiz {
    private Integer quizID;
    private Integer questionNo;
    private String diffLevel;
    private Boolean isCorrect;
    private Double time;

    /**
     * @return the quizID
     */
    
    public Quiz(int question_id, String difficulty_level, boolean iscorrect) {
        setQuestionNo(question_id);
        setDiffLevel(difficulty_level);
        setIsCorrect(iscorrect);
    }
    public Integer getQuizID() {
        return quizID;
    }

    /**
     * @param quizID the quizID to set
     */
    public void setQuizID(Integer quizID) {
        this.quizID = quizID;
    }

    /**
     * @return the questionNo
     */
    public Integer getQuestionNo() {
        return questionNo;
    }

    /**
     * @param questionNo the questionNo to set
     */
    public void setQuestionNo(Integer questionNo) {
        this.questionNo = questionNo;
    }

    /**
     * @return the diffLevel
     */
    public String getDiffLevel() {
        return diffLevel;
    }

    /**
     * @param diffLevel the diffLevel to set
     */
    public void setDiffLevel(String diffLevel) {
        this.diffLevel = diffLevel;
    }

    /**
     * @return the isCorrect
     */
    public Boolean getIsCorrect() {
        return isCorrect;
    }

    /**
     * @param isCorrect the isCorrect to set
     */
    public void setIsCorrect(Boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    /**
     * @return the time
     */
    public Double getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(Double time) {
        this.time = time;
    }
}
