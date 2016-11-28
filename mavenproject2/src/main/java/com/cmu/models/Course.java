/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmu.models;

/**
 *
 * @author b2bey
 */
public class Course {

    Integer crs_id;
    String crs_name;
    String ins_id;

    public Course(Integer crs_id, String crs_name, String ins_id) {
        this.crs_id = crs_id;
        this.crs_name = crs_name;
        this.ins_id = ins_id;
    }

    @Override
    public String toString() {
        return "Course{" + "crs_id=" + crs_id + ", crs_name=" + crs_name + ", ins_id=" + ins_id + '}';
    }

    public Integer getCrs_id() {
        return crs_id;
    }

    public void setCrs_id(Integer crs_id) {
        this.crs_id = crs_id;
    }

    public String getCrs_name() {
        return crs_name;
    }

    public void setCrs_name(String crs_name) {
        this.crs_name = crs_name;
    }

    public String getIns_id() {
        return ins_id;
    }

    public void setIns_id(String ins_id) {
        this.ins_id = ins_id;
    }

    public Course() {
    }

}
