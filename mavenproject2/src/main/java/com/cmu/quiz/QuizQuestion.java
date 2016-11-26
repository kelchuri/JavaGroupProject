
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmu.quiz;

/**
 *
 * @author Ayushjain
 */
public class QuizQuestion {

    private String questionFormat;
    private String difficultyLevel;
    private String questionDescription;
    private String option1;
    private String answer1;
    private String option2;
    private String answer2;
    private String option3;
    private String answer3;
    private String option4;
    private String answer4;
    private String answer;
    private String fillInTheBlank;
    private Integer time;

    public QuizQuestion(String questionFormat, String difficultyLevel,
            String quesDesc, String option1, String answer1,
            String option2, String answer2,
            String option3, String answer3,
            String option4, String answer4, Integer time) {
        this.questionFormat = questionFormat;
        this.difficultyLevel = difficultyLevel;
        this.questionDescription = quesDesc;
        this.option1 = option1;
        this.answer1 = answer1;
        this.option2 = option2;
        this.answer2 = answer2;
        this.option3 = option3;
        this.answer3 = answer3;
        this.option4 = option4;
        this.answer4 = answer4;
        this.time = time;
    }

    public QuizQuestion(String questionFormat, String difficultyLevel,
            String quesDesc, String answer, Integer time) {
        this.questionFormat = questionFormat;
        this.difficultyLevel = difficultyLevel;
        this.questionDescription = quesDesc;
        this.answer = answer;
        this.time = time;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestionFormat() {
        return questionFormat;
    }

    public void setQuestionFormat(String questionFormat) {
        this.questionFormat = questionFormat;
    }

    public String getQuestionDescription() {
        return questionDescription;
    }

    public void setQuestionDescription(String questionDescription) {
        this.questionDescription = questionDescription;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public String getAnswer4() {
        return answer4;
    }

    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }

    public String getFillInTheBlank() {
        return fillInTheBlank;
    }

    public void setFillInTheBlank(String fillInTheBlank) {
        this.fillInTheBlank = fillInTheBlank;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

}
