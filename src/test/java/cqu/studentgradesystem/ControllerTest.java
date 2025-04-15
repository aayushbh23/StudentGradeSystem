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
public class ControllerTest {
    
    /**
     * Test of inject method, of class Controller.
     */
    @Test
    public void testInject() {
        System.out.println("inject");
        GradeAnalyser analyser = null;
        Controller instance = new Controller();
        instance.inject(analyser);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }
    
    
        @Test
        public void findWhenFoundTest() {
            //raw data
            Student[] data = {
                new Student("S10", "Elizabeth", "Jenkins", 20, 28, 44, "HD"),
                new Student("S35", "Bruce", "Donaldson", 5, 16, 25, "SA"),
                new Student("S20", "David", "Brown", 12, 9, 24, "F")
            };
            String id = "S10";
            GradeAnalyser ga = new GradeAnalyser(data);
            Student expResult = new Student("S10", "Elizabeth", "Jenkins", 20, 28, 44, "HD");
            Student result = ga.find(id);
            assertEquals(expResult, result);
            assertNotNull(result);
            String notInId = "S99";
            Student noResult = ga.find(notInId);
            assertNull(noResult);
        }
}
