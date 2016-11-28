/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmu.dao;

import com.cmu.Models.Course;

/**
 *
 * @author Ayushjain
 */
public interface CourseDAO {

    public void add(Course c) throws Exception;

    public void update(Course c) throws Exception;

}
