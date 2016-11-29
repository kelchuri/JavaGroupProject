/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmu.dao;

/**
 *
 * @author Ayushjain
 */
public class QuizDAOFactory {

    public QuizDAO createQuizDAO() {
        return new QuizDAOJDBCImpl();
    }
}
