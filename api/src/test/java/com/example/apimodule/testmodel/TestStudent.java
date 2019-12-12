package com.example.apimodule.testmodel;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static com.example.apimodule.testmodel.Student.DISTINCTION;
import static com.example.apimodule.testmodel.Student.FAIL;
import static com.example.apimodule.testmodel.Student.FIRST_CLASS_PASS;
import static com.example.apimodule.testmodel.Student.PASS;
import static com.example.apimodule.testmodel.Student.SECOND_CLASS_PASS;
import static org.junit.Assert.assertEquals;

public class TestStudent {

    private static final String TAG = "TestStudent";
    private Student student1,student2,student3,student4,student5;

    @BeforeClass
    public static void setUpClass() {
        System.out.println("setUpClass: ");
    }

    @Before
    public void setUp() {
        System.out.println("setUp:.........................................................");
        student1=new Student("Sohan",100,35);
        student2=new Student("Mahesh",101,50);
        student3=new Student("Mahendra",102,60);
        student4=new Student("Neeraj",103,70);
        student5=new Student("Rajesh",99,20);
    }

    @Test
    public void TestPass() {
        System.out.println("TestPass Method");
        student1.getGrade();
        assertEquals(PASS, student1.getGrade());
        student1.display();
    }

    @Test
    public void TestPassSecond() {
        System.out.println("TestPassSecond Method");
        student2.getGrade();
        assertEquals(SECOND_CLASS_PASS, student2.getGrade());
        student2.display();
    }

    @Test
    public void TestPassFirst() {
        System.out.println("TestPassFirst Method");
        student3.getGrade();
        assertEquals(FIRST_CLASS_PASS, student3.getGrade());
        student3.display();
    }

    @Test
    public void TestPassDistinction() {
        System.out.println("TestPassDistinction Method");
        student4.getGrade();
        assertEquals(DISTINCTION, student4.getGrade());
        student4.display();
    }

    @Test
    public void TestFail() {
        System.out.println("TestFail Method");
        student5.getGrade();
        assertEquals(FAIL, student5.getGrade());
        student5.display();
    }


    @After
    public void tearDown() {
        System.out.println("tearDown: ");
    }

    @AfterClass
    public static void tearDownClass() {
        System.out.println("tearDownClass: ");
    }
}
