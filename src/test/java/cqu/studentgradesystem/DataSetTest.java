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
public class DataSetTest {
    
    @Test
    public void getDataThreeElementsTest() {
        String[][] studentDetails = {
            {"S20", "David", "Brown"},
            {"S10", "Elizabeth", "Jenkins"},
            {"S35", "Bruce", "Donaldson"}
        };

        int[][] studentMarks = {
            {12, 9, 24},
            {20, 28, 44},
            {5, 16, 25}
        };

        Student[] expected = {
            new Student("S10", "Elizabeth", "Jenkins", 20, 28, 44, "HD"),
            new Student("S35", "Bruce", "Donaldson", 5, 16, 25, "SA"),
            new Student("S20", "David", "Brown", 12, 9, 24, "F")
        };

        DataSet ds = new DataSet(studentDetails, studentMarks);
        assertArrayEquals(expected, ds.getData());
    }
    
}
