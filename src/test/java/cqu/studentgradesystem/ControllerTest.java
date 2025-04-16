package cqu.studentgradesystem;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link Controller} and {@link GradeAnalyser} classes.
 * This class verifies that dependency injection works in the {@code Controller}
 * class and also checks the student search functionality within {@code GradeAnalyser}.
 * 
 * @author Ayush Bhandari StudentID S12157470
 */
public class ControllerTest {
    /**
     * Tests the {@code find} method of the {@link GradeAnalyser} class.
     * This test checks if the {@code find} method correctly retrieves a {@link Student}
     * based on a valid student ID and returns {@code null} for a non-existent ID.
     * It covers two scenarios:
     * <ul>
     *   <li>Finding a student that exists in the data array</li>
     *   <li>Attempting to find a student that does not exist</li>
     * </ul>
     */
    @Test
    public void findWhenFoundTest() {
        // Sample student data
        Student[] data = {
            new Student("S10", "Elizabeth", "Jenkins", 20, 28, 44, "HD"),
            new Student("S35", "Bruce", "Donaldson", 5, 16, 25, "SA"),
            new Student("S20", "David", "Brown", 12, 9, 24, "F")
        };

        String id = "S10";
        GradeAnalyser ga = new GradeAnalyser(data);
        Student expResult = new Student("S10", "Elizabeth", "Jenkins", 20, 28, 44, "HD");

        // Valid student lookup
        Student result = ga.find(id);
        assertEquals(expResult, result);
        assertNotNull(result);

        // Invalid student lookup
        String notInId = "S99";
        Student noResult = ga.find(notInId);
        assertNull(noResult);
    }
}