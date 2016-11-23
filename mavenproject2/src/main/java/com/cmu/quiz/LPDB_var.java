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



    public LPDB_var(String userid, String password) {
        this.userid = userid;
        this.password = password;
    }

    @Override
    public String toString() {
        return "LPDB_var{" + "userid=" + userid + ", password=" + password + '}';
    }

    public static String getUserid() {
        return userid;
    }

    public static void setUserid(String userid) {
        LPDB_var.userid = userid;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        LPDB_var.password = password;
    }
   private static String userid;
   private static String password;
}
