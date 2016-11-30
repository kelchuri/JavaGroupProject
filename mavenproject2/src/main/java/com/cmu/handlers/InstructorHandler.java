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
import com.cmu.dao.QuizDAOJDBCImpl;
import com.cmu.dao.StudentQuizDAO;
import com.cmu.dao.StudentQuizDAOFactory;
import com.cmu.dao.UserDAO;
import com.cmu.dao.UserDAOFactory;
import com.cmu.models.Course;
import com.cmu.models.User;
import java.util.ArrayList;

/**
 *
 * @author kavya
 */
public class InstructorHandler {

    private final CourseDAOFactory courseDAOFactory = new CourseDAOFactory();
    private final CourseDAO courseDAO = courseDAOFactory.createCourseDAO();

    private final QuizDAOFactory quizDAOFactory = new QuizDAOFactory();
    private final QuizDAO quizDAO = quizDAOFactory.createQuizDAO();

    private final StudentQuizDAOFactory studentQuizDAOFactory = new StudentQuizDAOFactory();
    private final StudentQuizDAO studentQuizDAO = studentQuizDAOFactory.createStudentQuizDAO();

    public Course getCourse(User user) throws Exception {
        return courseDAO.getCourse(user);
    }

    public int getStudentQuizCount(User user) throws Exception, Throwable {
        return quizDAO.studentQuiz(user.getUserId());
    }

    public Double getStudentMarksDetail(User user) throws Throwable {
        return studentQuizDAO.overallAvgMarksStudent(user.getUserId());
    }
}
