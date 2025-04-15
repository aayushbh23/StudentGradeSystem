package cqu.studentgradesystem;

/**
 * The {@code DataSet} class is responsible for managing and preparing a list of
 * {@link Student} objects based on predefined or user-supplied student details and marks.
 * <p>
 * This class calculates total marks, determines grades using a {@link GradeAllocator},
 * and sorts students in descending order by total marks using selection sort.
 * It is used as the data source for further analysis in the application.
 * </p>
 * 
 * 
 * There are two constructors:
 * <ul>
 *   <li>A default constructor using hardcoded sample data</li>
 *   <li>A parameterized constructor that accepts external data</li>
 * </ul>
 * 
 * 
 * @author Ayush Bhandari StudentID S12157470
 */
public class DataSet {

    /** Array of processed Student objects. */
    private final Student[] students;

    /** Grade allocator used to determine grades based on marks. */
    private final GradeAllocator grader = new GradeAllocator();

    /** Default student details used when no external data is provided. */
    private final String[][] studentDetails = {
        {"S20", "David", "Brown"},
        {"S10", "Elizabeth", "Jenkins"},
        {"S35", "Bruce", "Donaldson"}
    };

    /** Default student marks corresponding to the default studentDetails. */
    private final int[][] studentMarks = {
        {12, 9, 24},
        {20, 28, 44},
        {5, 16, 25}
    };

    /**
     * Default constructor.
     * <p>
     * Loads student records using built-in sample data, calculates their grades,
     * and sorts them in descending order of total marks.
     * </p>
     */
    public DataSet() {
        this.students = loadFromTables(studentDetails, studentMarks);
        sortByTotalMark();
    }

    /**
     * Parameterized constructor.
     * <p>
     * Loads and processes student data from external arrays and sorts the list by total marks.
     * </p>
     *
     * @param details a 2D array of student details (ID, first name, last name)
     * @param marks a 2D array of marks (3 assessments per student)
     */
    public DataSet(String[][] details, int[][] marks) {
        this.students = loadFromTables(details, marks);
        sortByTotalMark();
    }

    /**
     * Converts raw data arrays into an array of {@link Student} objects and
     * assigns grades using the {@link GradeAllocator}.
     *
     * @param details student personal data
     * @param marks student marks data
     * @return an array of fully initialized Student objects
     */
    private Student[] loadFromTables(String[][] details, int[][] marks) {
        int n = details.length; // number of students
        Student[] studentGrades = new Student[n];
        for (int i = 0; i < n; i++) {
            int m1 = marks[i][0];
            int m2 = marks[i][1];
            int m3 = marks[i][2];
            String g = grader.determineGrade(m1, m2, m3);
            Student r = new Student(details[i][0], details[i][1],
                                    details[i][2], m1, m2, m3, g);
            studentGrades[i] = r;
        }
        return studentGrades;
    }

    /**
     * Sorts the array of students in descending order based on their total marks.
     * <p>
     * Uses the selection sort algorithm.
     * </p>
     */
    private void sortByTotalMark() {
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

    /**
     * Returns the array of processed and sorted {@link Student} records.
     *
     * @return an array of students sorted by total marks in descending order
     */
    public Student[] getData() {
        return students;
    }
}