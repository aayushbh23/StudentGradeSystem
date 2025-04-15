/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cqu.studentgradesystem;

/**
 *
 * @author 12157470
 */
public class DataSet {
    private final Student[] students;
    private final GradeAllocator grader = new GradeAllocator();
    private final String[][] studentDetails = {
        {"S20", "David", "Brown"},
        {"S10", "Elizabeth", "Jenkins"},
        {"S35", "Bruce", "Donaldson"}
    };
    private final int[][] studentMarks = {
        {12,9,24},
        {20,28,44},
        {5,16,25}
    }; 
    public DataSet() {
        this.students = loadFromTables(studentDetails, studentMarks);
        sortByTotalMark();
    }

    public DataSet(String[][] details, int[][] marks) {
        this.students = loadFromTables(details, marks);
        sortByTotalMark();
    }
    private Student[] loadFromTables(String[][]details, int[][] marks){
        int n = details.length; // number of students
        Student[] studentGrades = new Student[n];
        for (int i = 0; i < n ; i++ ) {
            int m1 = marks[i][0];
            int m2 = marks[i][1];
            int m3 = marks[i][2];
            String g = grader.determineGrade( m1, m2, m3 );
            Student r = new Student( details[i][0], details[i][1],
            details[i][2], m1, m2, m3, g);
            studentGrades[i] = r;
        }
        return studentGrades;
    }
    private void sortByTotalMark() {
        //Implementation of Selection Sort
        for (int i = 0; i < students.length - 1; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < students.length; j++) {
                if (students[j].total() > students[maxIndex].total()) {
                    maxIndex = j;
                }
            }
            Student temp = students[maxIndex];
            students[maxIndex] = students[i];
            students[i] = temp;
        }
    }

    public Student[] getData() {
        return students;
    }
}
