package cqu.studentgradesystem;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link GradeAnalyser} class.
 * <p>
 * This test suite validates the key functionalities of the {@code GradeAnalyser},
 * including:
 * <ul>
 *     <li>Searching for students using {@code find}</li>
 *     <li>Retrieving students within a mark range</li>
 *     <li>Validating input ranges</li>
 *     <li>Computing statistical data (max, min, average and median)</li>
 * </ul>
 * 
 * Test data includes three students with varying totals and grades to 
 * cover typical and edge cases.
 * </p>
 * 
 * @author Ayush Bhandari StudentID S12157470
 */
public class GradeAnalyserTest {

    private Student s1, s2, s3;
    private Student[] testStudents;
    private GradeAnalyser analyser;

    /**
     * Sets up a test dataset before each test method.
     * Initializes the analyser with three students of varying marks and grades.
     */
    @BeforeEach
    void setup() {
        s1 = new Student("S10", "Elizabeth", "Jenkins", 20, 28, 44, "HD");  // total: 92
        s2 = new Student("S35", "Bruce", "Donaldson", 5, 16, 25, "SA");     // total: 46
        s3 = new Student("S20", "David", "Brown", 12, 9, 24, "F");           // total: 45

        testStudents = new Student[] { s1, s2, s3 };
        analyser = new GradeAnalyser(testStudents);
    }

   
    /**
     * Start of {@code find()} method.
     * 
     * Tests that the correct student is returned when a valid ID is provided.
     */
    @Test
    void testFindReturnsCorrectStudent() {
        Student found = analyser.find("S10");
        assertNotNull(found);
        assertEquals("Elizabeth", found.firstName());
        assertEquals("Jenkins", found.lastName());
        assertEquals(92, found.total());
    }

    /**
     * Tests that {@code find} returns null when a student is not found.
     */
    @Test
    void testFindReturnsNullIfNotFound() {
        Student found = analyser.find("S15");
        assertNull(found);
    }

    /**
     * Tests that the ordered list of students is returned in expected order.
     */
    @Test
    void testGetOrderedListReturnsCorrectOrder() {
        List<Student> list = analyser.getOrderedList();
        assertEquals(3, list.size());
        assertEquals("S10", list.get(0).id());
        assertEquals("S35", list.get(1).id());
        assertEquals("S20", list.get(2).id());
    }

    /**
     * Tests that the internal hash map correctly stores and retrieves grades by ID.
     */
    @Test
    void testHashMapIsInitializedProperly() {
        assertEquals("HD", analyser.find("S10").grade());
        assertEquals("SA", analyser.find("S35").grade());
        assertEquals("F", analyser.find("S20").grade());
    }


    /**
     *Start of {@code getStudentsInRange()} method.
     * 
     * Tests that all students whose total marks fall within the given range are returned.
     */
    @Test
    public void testGetStudentsInRangeValidRange() {
        List<Student> result = analyser.getStudentsInRange(44, 93);
        assertEquals(3, result.size());
    }

    /**
     * Tests that an empty list is returned when no students match the given range.
     */
    @Test
    public void testGetStudentsInRangeNoMatches() {
        List<Student> result = analyser.getStudentsInRange(10, 20);
        assertTrue(result.isEmpty());
    }

    /**
     *Start of {@code validateRanges()} method.
     * 
     * Tests that valid input strings are correctly parsed and validated into a range.
     */
    @Test
    public void testValidateRangesValidInput() {
        var response = analyser.validateRanges("20", "80");
        assertTrue(response.result());
        assertEquals(20, response.range().lower());
        assertEquals(80, response.range().upper());
    }

    /**
     * Tests that missing inputs result in a failed validation with an appropriate message.
     */
    @Test
    public void testValidateRangesEmptyInput() {
        var response = analyser.validateRanges("", "80");
        assertFalse(response.result());
        assertEquals("Both bounds must be specified", response.message());
    }

    /**
     * Tests that out-of-bounds values are rejected with an error message.
     */
    @Test
    public void testValidateRangesOutOfBounds() {
        var response = analyser.validateRanges("-5", "120");
        assertFalse(response.result());
        assertEquals("Lower bound must be >=0 and upper bound must be <=100", response.message());
    }

    /**
     * Tests that non-numeric input results in a failed validation with a message.
     */
    @Test
    public void testValidateRangesNonNumeric() {
        var response = analyser.validateRanges("abc", "50");
        assertFalse(response.result());
        assertEquals("Range fields must be positive numbers (digits only)", response.message());
    }

    /**
     * Tests that a range where the lower bound is greater than the upper bound is invalid.
     */
    @Test
    public void testValidateRangesLowerGreaterThanUpper() {
        var response = analyser.validateRanges("80", "20");
        assertFalse(response.result());
        assertEquals("Upper bound must be greater than or equal to lower bound", response.message());
    }

    /**
     * Start of statistics method.
     * 
     * Tests that the maximum total mark is calculated correctly.
     */
    @Test
    public void testMaximum() throws EmptyListException {
        assertEquals(92, analyser.maximum());
    }

    /**
     * Tests that the minimum total mark is calculated correctly.
     */
    @Test
    public void testMinimum() throws EmptyListException {
        assertEquals(45, analyser.minimum());
    }

    /**
     * Tests that the average total mark is calculated correctly.
     */
    @Test
    public void testAverageMark() throws EmptyListException {
        double expectedAverage = (92 + 46 + 45) / 3.0;
        assertEquals(expectedAverage, analyser.averageMark());
    }

    /**
     * Tests that the median total mark is calculated correctly.
     */
    @Test
    public void testMedianMarkOddSize() throws EmptyListException {
        assertEquals(46, analyser.medianMark());
    }

    /**
     * Tests that all statistical methods throw {@link EmptyListException} for an empty list.
     */
    @Test
    public void testStatisticsWithEmptyList() {
        GradeAnalyser emptyAnalyser = new GradeAnalyser(new Student[] {});
        assertThrows(EmptyListException.class, emptyAnalyser::maximum);
        assertThrows(EmptyListException.class, emptyAnalyser::minimum);
        assertThrows(EmptyListException.class, emptyAnalyser::averageMark);
        assertThrows(EmptyListException.class, emptyAnalyser::medianMark);
    }
}
