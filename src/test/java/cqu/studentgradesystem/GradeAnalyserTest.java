/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package cqu.studentgradesystem;

import java.util.List;
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
public class GradeAnalyserTest {
    
    private Student s1, s2, s3;
    private Student[] testStudents;
    private GradeAnalyser analyser;

    @BeforeEach
    void setup() {
        s1 = new Student("S10", "Elizabeth", "Jenkins", 20, 28, 44, "HD");
        s2 = new Student("S35", "Bruce", "Donaldson", 5, 16, 25, "SA");
        s3 = new Student("S20", "David", "Brown", 12, 9, 24, "F");

        testStudents = new Student[] { s1, s2, s3 };
        analyser = new GradeAnalyser(testStudents);
    }

    @Test
    void testFindReturnsCorrectStudent() {
        Student found = analyser.find("S10");
        assertNotNull(found);
        assertEquals("Elizabeth", found.firstName());
        assertEquals("Jenkins", found.lastName());
        assertEquals(92, found.total());
    }

    @Test
    void testFindReturnsNullIfNotFound() {
        Student found = analyser.find("S15");
        assertNull(found);
    }

    @Test
    void testGetOrderedListReturnsCorrectOrder() {
        List<Student> list = analyser.getOrderedList();
        assertEquals(3, list.size());
        assertEquals("S10", list.get(0).id());
        assertEquals("S35", list.get(1).id());
        assertEquals("S20", list.get(2).id());
    }

    @Test
    void testHashMapIsInitializedProperly() {
        assertEquals("HD", analyser.find("S10").grade());
        assertEquals("SA", analyser.find("S35").grade());
        assertEquals("F", analyser.find("S20").grade());
    }
    
}
