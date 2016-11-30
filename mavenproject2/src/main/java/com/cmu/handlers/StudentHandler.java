/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmu.handlers;

import com.cmu.dao.QuizDAO;
import com.cmu.dao.QuizDAOFactory;
import com.cmu.models.Questions;
import com.cmu.models.Quiz;
import com.cmu.models.User;
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
    
    public void addIntoQuiz(List<Quiz> quiz, User user, int course_id) throws Throwable {
        quizDAO.addIntoDB(quiz, user, course_id );
    }
    
}
