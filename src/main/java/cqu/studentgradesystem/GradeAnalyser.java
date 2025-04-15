/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cqu.studentgradesystem;

import java.util.HashMap;
import java.util.List;
import java.util.Arrays;
/**
 *
 * @author 12157470
 */
public class GradeAnalyser {
    private final List<Student> orderedList;
    private final HashMap<String, Student> studentHashTable;

    public record Range(int lower, int upper) {}
    
    public record RangeValidationResponse(boolean result, Range range, String message) {}

    public GradeAnalyser(DataSet data) {
        this.orderedList = Arrays.asList(data.getData());
        this.studentHashTable = new HashMap<>();
        for (Student s : orderedList) {
            studentHashTable.put(s.id(), s);
        }
    }

    public GradeAnalyser(Student[] students) {
        this.orderedList = Arrays.asList(students);
        this.studentHashTable = new HashMap<>();
        for (Student s : orderedList) {
            studentHashTable.put(s.id(), s);
        }
    }

    public Student find(String id) {
        return studentHashTable.get(id);
    }
    
    public List<Student> getOrderedList() {
        return orderedList;
    }
}
