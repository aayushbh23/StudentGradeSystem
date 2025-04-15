package cqu.studentgradesystem;

/**
 * Custom exception class used to signal that an operation was attempted on an empty list of students.
 * <p>
 * This exception is typically thrown when statistical analysis such as calculating the maximum, 
 * minimum, average, or median is requested but no student data is available in the list.
 * </p>
 * 
 * <p>
 * Extends {@link RuntimeException} so it does not require mandatory exception handling.
 * </p>
 * 
 * Example usage:
 * <pre>
 * if (students.isEmpty()) {
 *     throw new EmptyListException("Student list is empty. Cannot compute statistics.");
 * }
 * </pre>
 * 
 * @author Ayush Bhandari  
 * @studentID S12157470
 */
public class EmptyListException extends RuntimeException {

    /**
     * Constructs a new {@code EmptyListException} with the specified detail message.
     *
     * @param message the detail message explaining the reason for the exception
     */
    public EmptyListException(String message) {
        super(message);
    }
}