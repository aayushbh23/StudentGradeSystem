package cqu.studentgradesystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Arrays;

/**
 * The {@code GradeAnalyser} class provides functionality to analyze a data set of student records.
 * <p>
 * It allows for:
 * <ul>
 *     <li>Searching for a student by ID</li>
 *     <li>Retrieving students within a specified total mark range</li>
 *     <li>Validating mark range inputs</li>
 *     <li>Computing class statistics like maximum, minimum, average, and median marks</li>
 * </ul>
 * </p>
 * The class uses both a list (for ordered access) and a hash table (for fast lookup).
 * It also defines two inner record types for encapsulating range and validation results.
 * 
 * @author Ayush Bhandari  
 * @studentID S12157470
 */
public class GradeAnalyser {

    /** A list of students sorted in descending order of total marks. */
    private final List<Student> orderedList;

    /** A hash table of students keyed by their student ID. */
    private final HashMap<String, Student> studentHashTable;

    /**
     * Represents a range with lower and upper bounds.
     *
     * @param lower the lower bound (inclusive)
     * @param upper the upper bound (inclusive)
     */
    public record Range(int lower, int upper) {}

    /**
     * Represents the result of validating a mark range input.
     *
     * @param result  whether the validation was successful
     * @param range   the validated range (if valid), or null if invalid
     * @param message the error message (if invalid), or empty string if valid
     */
    public record RangeValidationResponse(boolean result, Range range, String message) {}

    /**
     * Constructs a {@code GradeAnalyser} using a {@link DataSet} object.
     * Initializes both the ordered list and the student hash table.
     *
     * @param data the dataset containing student information
     */
    public GradeAnalyser(DataSet data) {
        this.orderedList = Arrays.asList(data.getData());
        this.studentHashTable = new HashMap<>();
        for (Student s : orderedList) {
            studentHashTable.put(s.id(), s);
        }
    }

    /**
     * Constructs a {@code GradeAnalyser} using a sorted array of {@link Student} records.
     * Intended primarily for unit testing.
     *
     * @param students array of students sorted by total mark
     */
    public GradeAnalyser(Student[] students) {
        this.orderedList = Arrays.asList(students);
        this.studentHashTable = new HashMap<>();
        for (Student s : orderedList) {
            studentHashTable.put(s.id(), s);
        }
    }

    /**
     * Finds a student record based on the given student ID.
     *
     * @param id the student ID to search for
     * @return the {@link Student} object if found, otherwise {@code null}
     */
    public Student find(String id) {
        return studentHashTable.get(id);
    }

    /**
     * Retrieves the ordered list of student records.
     *
     * @return list of students sorted by total marks (descending)
     */
    public List<Student> getOrderedList() {
        return orderedList;
    }

    /**
     * Returns a list of students whose total marks fall within the specified range.
     *
     * @param lower the lower bound (inclusive)
     * @param upper the upper bound (inclusive)
     * @return list of students within the specified mark range
     */
    public List<Student> getStudentsInRange(int lower, int upper) {
        List<Student> result = new ArrayList<>();
        for (Student student : orderedList) {
            int total = student.total();
            if (total >= lower && total <= upper) {
                result.add(student);
            }
        }
        return result;
    }

    /**
     * Validates user input strings representing mark bounds.
     * <p>
     * Ensures that both fields are numeric, within range (0–100),
     * and that the lower bound is not greater than the upper bound.
     * </p>
     *
     * @param lowerStr the input for the lower bound
     * @param upperStr the input for the upper bound
     * @return a {@link RangeValidationResponse} object indicating success or failure
     */
    public RangeValidationResponse validateRanges(String lowerStr, String upperStr) {
        if (lowerStr.isEmpty() || upperStr.isEmpty()) {
            return new RangeValidationResponse(false, null, "Both bounds must be specified");
        }

        try {
            int lower = Integer.parseInt(lowerStr);
            int upper = Integer.parseInt(upperStr);

            if (lower < 0 || upper > 100) {
                return new RangeValidationResponse(false, null,
                        "Lower bound must be >=0 and upper bound must be <=100");
            }

            if (lower > upper) {
                return new RangeValidationResponse(false, null,
                        "Upper bound must be greater than or equal to lower bound");
            }

            return new RangeValidationResponse(true, new Range(lower, upper), "");
        } catch (NumberFormatException e) {
            return new RangeValidationResponse(false, null,
                    "Range fields must be positive numbers (digits only)");
        }
    }

    /**
     * Returns the highest total mark among all students.
     *
     * @return maximum total mark
     * @throws EmptyListException if the student list is empty
     */
    public int maximum() throws EmptyListException {
        if (orderedList.isEmpty()) {
            throw new EmptyListException("No student records available");
        }
        return orderedList.get(0).total();
    }

    /**
     * Returns the lowest total mark among all students.
     *
     * @return minimum total mark
     * @throws EmptyListException if the student list is empty
     */
    public int minimum() throws EmptyListException {
        if (orderedList.isEmpty()) {
            throw new EmptyListException("No student records available");
        }
        return orderedList.get(orderedList.size() - 1).total();
    }

    /**
     * Calculates the average total mark for the class.
     *
     * @return the average mark
     * @throws EmptyListException if the student list is empty
     */
    public double averageMark() throws EmptyListException {
        if (orderedList.isEmpty()) {
            throw new EmptyListException("No student records available");
        }

        double sum = 0;
        for (Student student : orderedList) {
            sum += student.total();
        }
        return sum / orderedList.size();
    }

    /**
     * Calculates the median total mark for the class.
     *
     * @return the median mark
     * @throws EmptyListException if the student list is empty
     */
    public double medianMark() throws EmptyListException {
        if (orderedList.isEmpty()) {
            throw new EmptyListException("No student records available");
        }

        int size = orderedList.size();
        if (size % 2 == 1) {
            return orderedList.get(size / 2).total();
        } else {
            double lower = orderedList.get(size / 2 - 1).total();
            double upper = orderedList.get(size / 2).total();
            return (lower + upper) / 2;
        }
    }
}