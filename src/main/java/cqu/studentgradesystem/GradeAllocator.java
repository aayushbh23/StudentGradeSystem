/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cqu.studentgradesystem;

/**
 *
 * @author 12157470
 */
public class GradeAllocator {
    private final int maxAssess1 = 20;
    private final int maxAssess2 = 30;
    private final int maxAssess3 = 50;

    public String determineGrade(int a1Mark, int a2Mark, int a3Mark) {
        String grade ="";
        int totalMarks = a1Mark + a2Mark + a3Mark;
        if (totalMarks == 0) {
            grade = "AF";
        } else if (!meetsMinimumCriteria(a1Mark, a2Mark, a3Mark)) {
            if (supplementary(a1Mark, a2Mark, a3Mark)) {
                grade = "SA";
            } else {
                grade = "F";
            }
        } else if (totalMarks >= 85) {
            grade = "HD";
        } else if (totalMarks >= 75) {
            grade = "D";
        } else if (totalMarks >= 65) {
            grade = "C";
        } else if (totalMarks >= 50) {
            grade = "P";
        } else {
            grade = "F";
        }
        return grade;
    }

    private boolean meetsMinimumCriteria(int a1, int a2, int a3) {
        return a1 >= maxAssess1 / 2 && a1 <= maxAssess1 &&
               a2 >= maxAssess2 / 2 && a2 <= maxAssess2 &&
               a3 >= maxAssess3 / 2 && a3 <= maxAssess3;
    }

    private boolean supplementary(int a1, int a2, int a3) {
        int failures = 0;
        if (a1 < maxAssess1 / 2) failures++;
        if (a2 < maxAssess2 / 2) failures++;
        if (a3 < maxAssess3 / 2) failures++;
        return failures == 1;
    }
}
