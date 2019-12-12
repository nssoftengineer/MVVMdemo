package com.example.apimodule.testmodel;

public class Student {

    private String name;
    private int rollNo;
    private float percentage;
    private String grade;

    public static final String FAIL = "FAIL";
    public static final String PASS = "PASS";
    public static final String SECOND_CLASS_PASS = "SECOND CLASS PASS";
    public static final String FIRST_CLASS_PASS = "FIRST CLASS PASS";
    public static final String DISTINCTION = "DISTINCTION";

    public Student(String name, int rollNo, float percentage) {
        this.name = name;
        this.rollNo = rollNo;
        this.percentage = percentage;
    }

    public String getGrade() {
        if (percentage < 35)
            grade = FAIL;
        else if (percentage < 50)
            grade = PASS;
        else if (percentage < 60)
            grade = SECOND_CLASS_PASS;
        else if (percentage <= 70)
            grade = FIRST_CLASS_PASS;
        else
            grade = DISTINCTION;

        return grade;
    }

    public void display() {
        System.out.println("Role no " + rollNo);
        System.out.println("Name " + name);
        System.out.println("Percentage " + percentage);
        System.out.println("Grade " + grade);
    }
}
