
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmu.models;

/**
 *
 * @author Ayushjain
 */
public class Questions {

    private int ques_id;

    private String ques_type;

    private String diff_lvl;

    private String ques_desc;

    private String option1;

    private String answer1;

    private String option2;

    private String answer2;

    private String option3;

    private String answer3;

    private String option4;

    private String answer4;

    private String answer;

    private int crs_id;

    private int time;

    private String type;

    /**
     *
     * this constructor creates an object of questions for MC and MA
     *
     * @param questionFormat
     * @param difficultyLevel
     * @param quesDesc
     * @param option1
     * @param answer1
     * @param option2
     * @param answer2
     * @param option3
     * @param answer3
     * @param option4
     * @param answer4
     * @param crs_id
     * @param time
     */
    public Questions(String questionFormat, String difficultyLevel,
            String quesDesc, String option1, String answer1,
            String option2, String answer2,
            String option3, String answer3,
            String option4, String answer4, int crs_id, Integer time) {
        this.ques_desc = quesDesc;
        this.option1 = option1;
        this.answer1 = answer1;
        this.option2 = option2;
        this.answer2 = answer2;
        this.option3 = option3;
        this.answer3 = answer3;
        this.option4 = option4;
        this.answer4 = answer4;
        this.crs_id = crs_id;
        this.time = time;
        this.ques_type = questionFormat;
    }

    /**
     *
     * this constructor creates an object of questions for TF and FIB
     *
     * @param questionFormat
     * @param difficultyLevel
     * @param quesDesc
     * @param answer
     * @param crs_id
     * @param time
     */
    public Questions(String questionFormat, String difficultyLevel,
            String quesDesc, String answer, int crs_id, Integer time) {
        this.ques_desc = quesDesc;
        this.ques_type = questionFormat;
        this.diff_lvl = difficultyLevel;
        this.ques_desc = quesDesc;
        this.crs_id = crs_id;
        this.answer = answer;
        this.time = time;
    }

    /**
     * this method gets the answer
     * 
     * @return 
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * this method sets the answer
     * 
     * @param answer
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * this method gets the option 1
     * 
     * @return 
     */
    public String getOption1() {
        return option1;
    }

    /**
     * this method sets the option 1
     * 
     * @param option1
     */
    public void setOption1(String option1) {
        this.option1 = option1;
    }

    /**
     * this method gets the answer 1
     * 
     * @return 
     */
    public String getAnswer1() {
        return answer1;
    }

    /**
     * this method sets the answer 1
     * 
     * @param answer1
     */
    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    /**
     * this method gets the option 2
     * 
     * @return 
     */
    public String getOption2() {
        return option2;
    }

    /**
     * this method sets the option 2
     * 
     * @param option2
     */
    public void setOption2(String option2) {
        this.option2 = option2;
    }

    /**
     * this method gets the answer 2
     * 
     * @return 
     */
    public String getAnswer2() {
        return answer2;
    }

    /**
     * this method sets the answer 2
     * 
     * @param answer2
     */
    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    /**
     * this method gets the option 3
     * 
     * @return 
     */
    public String getOption3() {
        return option3;
    }

    /**
     * this method sets the option 3
     * 
     * @param option3
     */
    public void setOption3(String option3) {
        this.option3 = option3;
    }

    /**
     * this method gets the answer 3
     * 
     * @return 
     */
    public String getAnswer3() {
        return answer3;
    }

    /**
     * this method sets the answer 3
     * 
     * @param answer3
     */
    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    /**
     * this method gets the option 4
     * 
     * @return 
     */
    public String getOption4() {
        return option4;
    }

    /**
     * this method sets the option 4
     * 
     * @param option4
     */
    public void setOption4(String option4) {
        this.option4 = option4;
    }

    /**
     * this method gets the answer 4
     * 
     * @return 
     */
    public String getAnswer4() {
        return answer4;
    }

    /**
     * this method sets the answer 4
     * 
     * @param answer4
     */
    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }

    /**
     * this method gets the time
     * 
     * @return 
     */
    public int getTime() {
        return time;
    }

    /**
     * this method sets the time
     * 
     * @param time
     */
    public void setTime(int time) {
        this.time = time;
    }

    /**
     * @return the ques_id
     */
    public int getQues_id() {
        return ques_id;
    }

    /**
     * @param ques_id the ques_id to set
     */
    public void setQues_id(int ques_id) {
        this.ques_id = ques_id;
    }

    /**
     * @return the ques_type
     */
    public String getQues_type() {
        return this.ques_type;
    }

    /**
     * @param ques_type the ques_type to set
     */
    public void setQues_type(String ques_type) {
        this.ques_type = ques_type;
    }

    /**
     * @return the diff_lvl
     */
    public String getDiff_lvl() {
        return diff_lvl;
    }

    /**
     * @param diff_lvl the diff_lvl to set
     */
    public void setDiff_lvl(String diff_lvl) {
        this.diff_lvl = diff_lvl;
    }

    /**
     * @return the ques_desc
     */
    public String getQues_desc() {
        return ques_desc;
    }

    /**
     * @param ques_desc the ques_desc to set
     */
    public void setQues_desc(String ques_desc) {
        this.ques_desc = ques_desc;
    }

    /**
     * @return the crs_id
     */
    public int getCrs_id() {
        return crs_id;
    }

    /**
     * this method sets the course id
     * 
     * @param crs_id
     */
    public void setCrs_id(int crs_id) {
        this.crs_id = crs_id;
    }

}
