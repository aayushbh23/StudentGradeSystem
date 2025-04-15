/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package cqu.studentgradesystem;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author 12157470
 */
public class GradeAllocatorTest {

    @Test
    public void absentFailDetermineGradeTest() {
        int a1 = 0;
        int a2 = 0;
        int a3 = 0;
        // total is calculated in class 
        GradeAllocator ga = new GradeAllocator();
        assertEquals("AF", ga.determineGrade(a1, a2, a3));
    }

    @Test
    public void highDistinctionDetermineGradeTest() {
        int a1 = 15;
        int a2 = 25;
        int a3 = 45;
        GradeAllocator ga = new GradeAllocator();
        assertEquals("HD", ga.determineGrade(a1, a2, a3));
    }

    @Test
    public void distinctionDetermineGradeTest() {
        int a1 = 13;
        int a2 = 22;
        int a3 = 40;
        GradeAllocator ga = new GradeAllocator();
        assertEquals("D", ga.determineGrade(a1, a2, a3));
    }

    @Test
    public void creditDetermineGradeTest() {
        int a1 = 11;
        int a2 = 20;
        int a3 = 40;
        GradeAllocator ga = new GradeAllocator();
        assertEquals("C", ga.determineGrade(a1, a2, a3));
    }

    @Test
    public void passDetermineGradeTest() {
        int a1 = 10;
        int a2 = 15;
        int a3 = 25;
        GradeAllocator ga = new GradeAllocator();
        assertEquals("P", ga.determineGrade(a1, a2, a3));
    }

    @Test
    public void supplementaryAssessmentDetermineGradeTest() {
        int a1 = 4; // fail
        int a2 = 16;
        int a3 = 30;
        GradeAllocator ga = new GradeAllocator();
        assertEquals("SA", ga.determineGrade(a1, a2, a3));
    }

    @Test
    public void failDetermineGradeTestDueToMultipleFails() {
        int a1 = 5; // fail
        int a2 = 10; // fail
        int a3 = 30;
        GradeAllocator ga = new GradeAllocator();
        assertEquals("F", ga.determineGrade(a1, a2, a3));
    }

    @Test
    public void failDetermineGradeTestEvenWithIndividualPasses() {
        int a1 = 10;
        int a2 = 10;
        int a3 = 20;
        GradeAllocator ga = new GradeAllocator();
        assertEquals("F", ga.determineGrade(a1, a2, a3));
    }

    @Test
    public void boundaryPassGradeTest() {
        int a1 = 10;
        int a2 = 15;
        int a3 = 25;
        GradeAllocator ga = new GradeAllocator();
        assertEquals("P", ga.determineGrade(a1, a2, a3));
    }

    @Test
    public void perfectScoreGradeTest() {
        int a1 = 20;
        int a2 = 30;
        int a3 = 50;
        GradeAllocator ga = new GradeAllocator();
        assertEquals("HD", ga.determineGrade(a1, a2, a3));
    }
}
