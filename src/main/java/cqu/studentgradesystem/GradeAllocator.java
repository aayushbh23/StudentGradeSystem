package cqu.studentgradesystem;

/**
 * The {@code GradeAllocator} class is responsible for determining a student's final grade
 * based on marks from three assessments. It applies rules for grade boundaries,
 * minimum criteria, and supplementary eligibility.
 * <p>
 * The assessments are weighted as follows:
 * <ul>
 *     <li>Assessment 1: 20 marks</li>
 *     <li>Assessment 2: 30 marks</li>
 *     <li>Assessment 3: 50 marks</li>
 * </ul>
 * </p>
 * Grades are assigned as per CQUniversity rules:
 * <ul>
 *     <li>{@code HD} - High Distinction (85–100)</li>
 *     <li>{@code D}  - Distinction (75–84)</li>
 *     <li>{@code C}  - Credit (65–74)</li>
 *     <li>{@code P}  - Pass (50–64)</li>
 *     <li>{@code F}  - Fail (Below 50)</li>
 *     <li>{@code SA} - Supplementary Assessment (Fails minimum requirement on one component)</li>
 *     <li>{@code AF} - Absent Fail (Total mark = 0)</li>
 * </ul>
 * 
 * @author Ayush Bhandari  
 * @studentID S12157470
 */
public class GradeAllocator {

    /** Maximum mark for assessment 1 (out of 20). */
    private final int maxAssess1 = 20;

    /** Maximum mark for assessment 2 (out of 30). */
    private final int maxAssess2 = 30;

    /** Maximum mark for assessment 3 (out of 50). */
    private final int maxAssess3 = 50;

    /**
     * Determines the final grade based on three assessment marks.
     * <p>
     * It applies the following logic:
     * <ul>
     *   <li>If total is 0, returns {@code "AF"} (Absent Fail)</li>
     *   <li>If minimum criteria not met, returns {@code "SA"} or {@code "F"}</li>
     *   <li>Otherwise, calculates based on total mark</li>
     * </ul>
     * </p>
     *
     * @param a1Mark mark for assessment 1
     * @param a2Mark mark for assessment 2
     * @param a3Mark mark for assessment 3
     * @return grade as a {@code String} (e.g. "HD", "D", "C", "P", "F", "SA", "AF")
     */
    public String determineGrade(int a1Mark, int a2Mark, int a3Mark) {
        String grade = "";
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

    /**
     * Checks whether the student has met the minimum criteria for each assessment.
     * The minimum requirement is 50% of the maximum possible marks for each assessment.
     *
     * @param a1 mark for assessment 1
     * @param a2 mark for assessment 2
     * @param a3 mark for assessment 3
     * @return {@code true} if minimum criteria are met for all assessments; {@code false} otherwise
     */
    private boolean meetsMinimumCriteria(int a1, int a2, int a3) {
        return a1 >= maxAssess1 / 2 && a1 <= maxAssess1 &&
               a2 >= maxAssess2 / 2 && a2 <= maxAssess2 &&
               a3 >= maxAssess3 / 2 && a3 <= maxAssess3;
    }

    /**
     * Determines if a student qualifies for a supplementary assessment.
     * <p>
     * A student qualifies if they failed exactly one component (scored below 50% in one assessment),
     * and passed the others.
     * </p>
     *
     * @param a1 mark for assessment 1
     * @param a2 mark for assessment 2
     * @param a3 mark for assessment 3
     * @return {@code true} if the student qualifies for supplementary; {@code false} otherwise
     */
    private boolean supplementary(int a1, int a2, int a3) {
        int failures = 0;
        if (a1 < maxAssess1 / 2) failures++;
        if (a2 < maxAssess2 / 2) failures++;
        if (a3 < maxAssess3 / 2) failures++;
        return failures == 1;
    }
}