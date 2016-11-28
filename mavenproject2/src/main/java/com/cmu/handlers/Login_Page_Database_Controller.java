/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmu.handlers;

import com.cmu.models.User;
import com.cmu.dao.UserDAO;
import com.cmu.dao.UserDAOFactory;

/**
 *
 * @author b2
 */
public class Login_Page_Database_Controller {

    private final UserDAOFactory userDAOFactory = new UserDAOFactory();
    private final UserDAO userDAO = userDAOFactory.createUserDAO();

    public String checkCredentials(String userid, String password) throws Throwable {
        int check = 0;
        String type = "";
        boolean userExists = userDAO.checkUserExists(userid, password);
        if (userExists) {
            type = userDAO.checkUserType(userid);

            System.out.println(check);

        }
        return type;
    }
    
    public void addANewUser(User user) throws Exception{
        userDAO.add(user);
    }

}
