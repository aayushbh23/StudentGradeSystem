package cqu.studentgradesystem;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for the {@link DataSet} class.
 * This class tests the correct transformation of raw student details and marks
 * into a structured array of {@link Student} objects, as performed by the {@code getData} method.
 * 
 * @author Ayush Bhandari StudentID S12157470
 */
public class DataSetTest {

    /**
     * Tests the {@code getData()} method of the {@link DataSet} class with three student entries.
     * This test ensures that the method correctly constructs {@link Student} objects using
     * provided student detail and mark arrays. It also verifies that the generated students
     * match the expected results in terms of ID, name, marks, and grade.
     */
    @Test
    public void getDataThreeElementsTest() {
        // Sample input: student IDs, names
        String[][] studentDetails = {
            {"S20", "David", "Brown"},
            {"S10", "Elizabeth", "Jenkins"},
            {"S35", "Bruce", "Donaldson"}
        };

        // Sample input: assessment marks
        int[][] studentMarks = {
            {12, 9, 24},
            {20, 28, 44},
            {5, 16, 25}
        };

        // Expected output: fully initialized Student objects with grades
        Student[] expected = {
            new Student("S10", "Elizabeth", "Jenkins", 20, 28, 44, "HD"),
            new Student("S35", "Bruce", "Donaldson", 5, 16, 25, "SA"),
            new Student("S20", "David", "Brown", 12, 9, 24, "F")
        };

        // Run method and assert equality
        DataSet ds = new DataSet(studentDetails, studentMarks);
        assertArrayEquals(expected, ds.getData());
    }
}
