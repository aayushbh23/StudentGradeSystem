package cqu.studentgradesystem;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link GradeAllocator} class.
 * These tests verify the correctness of grade assignment based on assessment scores,
 * including edge cases such as perfect scores, minimum scores, and combinations that 
 * result in special outcomes like "SA" (Supplementary Assessment) or "AF" (Absent Fail).
 * 
 * @author Ayush Bhandari StudentID S12157470
 */
public class GradeAllocatorTest {

    /**
     * Tests the case where all assessments are missed (i.e., scored 0),
     * which should result in an "AF" (Absent Fail).
     */
    @Test
    public void absentFailDetermineGradeTest() {
        int a1 = 0, a2 = 0, a3 = 0;
        GradeAllocator ga = new GradeAllocator();
        assertEquals("AF", ga.determineGrade(a1, a2, a3));
    }

    /**
     * Tests that results in a "HD".
     */
    @Test
    public void highDistinctionDetermineGradeTest() {
        int a1 = 15, a2 = 25, a3 = 45;
        GradeAllocator ga = new GradeAllocator();
        assertEquals("HD", ga.determineGrade(a1, a2, a3));
    }

    /**
     * Tests that results in a "D".
     */
    @Test
    public void distinctionDetermineGradeTest() {
        int a1 = 13, a2 = 22, a3 = 40;
        GradeAllocator ga = new GradeAllocator();
        assertEquals("D", ga.determineGrade(a1, a2, a3));
    }

    /**
     * Tests that results in a "C".
     */
    @Test
    public void creditDetermineGradeTest() {
        int a1 = 11, a2 = 20, a3 = 40;
        GradeAllocator ga = new GradeAllocator();
        assertEquals("C", ga.determineGrade(a1, a2, a3));
    }

    /**
     * Tests that results in a "P".
     */
    @Test
    public void passDetermineGradeTest() {
        int a1 = 10, a2 = 15, a3 = 25;
        GradeAllocator ga = new GradeAllocator();
        assertEquals("P", ga.determineGrade(a1, a2, a3));
    }

    /**
     * Tests that results in a "SA".
     */
    @Test
    public void supplementaryAssessmentDetermineGradeTest() {
        int a1 = 4, a2 = 16, a3 = 30;
        GradeAllocator ga = new GradeAllocator();
        assertEquals("SA", ga.determineGrade(a1, a2, a3));
    }

    /**
     * Tests that results in a "F" (Fail), even if the total marks is relatively high.
     */
    @Test
    public void failDetermineGradeTestDueToMultipleFails() {
        int a1 = 5, a2 = 10, a3 = 30;
        GradeAllocator ga = new GradeAllocator();
        assertEquals("F", ga.determineGrade(a1, a2, a3));
    }

    /**
     * Tests the lower boundary of "P".
     */
    @Test
    public void boundaryPassGradeTest() {
        int a1 = 10, a2 = 15, a3 = 25;
        GradeAllocator ga = new GradeAllocator();
        assertEquals("P", ga.determineGrade(a1, a2, a3));
    }

    /**
     * Tests the upper boundary of "HD".
     */
    @Test
    public void perfectScoreGradeTest() {
        int a1 = 20, a2 = 30, a3 = 50;
        GradeAllocator ga = new GradeAllocator();
        assertEquals("HD", ga.determineGrade(a1, a2, a3));
    }
}
