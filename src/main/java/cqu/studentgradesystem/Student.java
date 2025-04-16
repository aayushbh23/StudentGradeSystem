package cqu.studentgradesystem;

/**
 * Represents a student's data including ID, name,
 * assessment marks and final grade.
 * 
 * This is a record that includes:
 * <ul>
 *     <li>Student ID</li>
 *     <li>First and last name</li>
 *     <li>Marks for three assessments</li>
 *     <li>Final grade (HD, D, C, P, F, SA, AF)</li>
 * </ul>
 *
 * Also includes helper methods to calculate total marks and generate
 * a formatted string representation of the student's results.
 * 
 * @param id        the unique identifier for the student
 * @param firstName the student's first name
 * @param lastName  the student's last name
 * @param a1        the mark for assessment 1
 * @param a2        the mark for assessment 2
 * @param a3        the mark for assessment 3
 * @param grade     the final grade assigned to the student
 * 
 * @author Ayush Bhandari StudentID S12157470
 */
public record Student(
    String id,
    String firstName,
    String lastName,
    int a1,
    int a2,
    int a3,
    String grade
) {
    /**
     * Calculates the total mark by summing the three assessment marks.
     *
     * @return total of assessment marks
     */
    public int total() {
        return a1 + a2 + a3;
    }

    /**
     * Returns a formatted string representing the student's details
     * including ID, name, individual marks, total and grade.
     *
     * @return formatted student summary string
     */
    @Override
    public String toString() {
        return String.format("%s: %-14s%-14s %2d %2d %2d total: %3d grade: %2s",
                id, firstName, lastName, a1, a2, a3, total(), grade);
    }
}