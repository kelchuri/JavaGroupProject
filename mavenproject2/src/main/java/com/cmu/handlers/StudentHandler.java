/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmu.handlers;

import com.cmu.dao.CourseDAO;
import com.cmu.dao.CourseDAOFactory;
import com.cmu.dao.QuizDAO;
import com.cmu.dao.QuizDAOFactory;
import com.cmu.models.Questions;
import java.util.List;

/**
 *
 * @author kavya
 */
public class StudentHandler {
    private final QuizDAOFactory quizDAOFactory = new QuizDAOFactory();
    private final QuizDAO quizDAO = quizDAOFactory.createQuizDAO();
    
    public List<Questions> getQuestions(String quiz, int no, int course) throws Exception{
        return quizDAO.getQuizQuestion(no, course, quiz);
    }
    
}
