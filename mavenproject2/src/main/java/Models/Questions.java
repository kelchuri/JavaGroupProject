
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

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
    
    private double time;

    public Questions(String questionFormat, String difficultyLevel,
            String quesDesc, String option1, String answer1,
            String option2, String answer2,
            String option3, String answer3,
            String option4, String answer4, Integer time) {
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

    public Questions(String questionFormat, String difficultyLevel,
            String quesDesc, String answer, Integer time) {
        this.answer = answer;
        this.time = time;
    }

    
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
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

    
    public double getTime() {
        return time;
    }

    public void setTime(double time) {
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
        return ques_type;
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

}
