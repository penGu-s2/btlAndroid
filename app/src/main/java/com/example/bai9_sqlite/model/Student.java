package com.example.bai9_sqlite.model;

import java.io.Serializable;

public class Student implements Serializable {
    private int id;
    private String name;
    private  boolean gender;
    private double mark;

    public Student() {
    }

    public Student(int id, String name, boolean gender, double mark) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.mark = mark;
    }

    public Student(String name, boolean gender, double mark) {
        this.name = name;
        this.gender = gender;
        this.mark = mark;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isGender() {
        return gender;
    }

    public double getMark() {
        return mark;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }
}
