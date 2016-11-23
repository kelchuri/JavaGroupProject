/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmu.quiz;

/**
 *
 * @author b2bey
 */
public class LPDB_var {

    public static String getUserid() {
        return userid;
    }

    public static void setUserid(String userid) {
        LPDB_var.userid = userid;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        LPDB_var.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        LPDB_var.password = password;
    }

    public static String getStuIns() {
        return stuIns;
    }

    public static void setStuIns(String stuIns) {
        LPDB_var.stuIns = stuIns;
    }

    public LPDB_var() {
    }


   private static String userid;
   private static String username;
   private static String password;
   private static String stuIns;
}
