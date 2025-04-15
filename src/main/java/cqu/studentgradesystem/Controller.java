package cqu.studentgradesystem;

import java.util.List;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * Controller class for handling user interactions in the JavaFX Student Grade System GUI.
 * <p>
 * Provides handlers for button actions such as displaying all student grades,
 * searching for a student by ID, filtering results by mark range, and showing class statistics.
 * </p>
 * 
 * @author Ayush Bhandari StudentID S12157470
 */
public class Controller {

    /** The GradeAnalyser used for processing student data and statistics. */
    private GradeAnalyser GradeAnalyser;

    /** Text area used to display results and messages in the GUI. */
    @FXML
    private TextArea outputTextArea;

    /** Text field for user input to search for a student by ID. */
    @FXML
    private TextField findStudentsIdTextArea;

    /** Text field for specifying the lower bound of the mark range filter. */
    @FXML
    private TextField resultsInMarkRangeFromTextArea;

    /** Text field for specifying the upper bound of the mark range filter. */
    @FXML
    private TextField resultsInMarkRangeToTextArea;

    /**
     * Injects the {@link GradeAnalyser} into this controller.
     * 
     * @param analyser the GradeAnalyser object to be used for all analysis operations
     */
    public void inject(GradeAnalyser analyser) {
        this.GradeAnalyser = analyser;
    }

    /**
     * Displays all student grades in the output text area.
     * Triggered by the "Display All Grades" button.
     * 
     * @param event the button click event
     */
    @FXML
    private void displayAllGrades(ActionEvent event) {
        this.outputTextArea.clear();
        if (this.GradeAnalyser == null) {
            outputTextArea.setText("No data loaded.");
            return;
        }

        List<Student> students = this.GradeAnalyser.getOrderedList();
        if (students.isEmpty()) {
            outputTextArea.setText("No student records available.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (Student s : students) {
                sb.append(s.toString()).append("\n");
            }
            outputTextArea.setText(sb.toString());
        }
    }

    /**
     * Finds and displays a student's information based on the ID entered by the user.
     * Triggered by the "Find Student" button.
     * 
     * @param event the button click event
     */
    @FXML
    private void findStudentId(ActionEvent event) {
        this.outputTextArea.clear();
        try {
            if (this.findStudentsIdTextArea.getText().isEmpty()) {
                this.outputTextArea.setText("No Input Student ID");
            } else {
                String id = findStudentsIdTextArea.getText().trim();
                Student student = this.GradeAnalyser.find(id);
                if (student == null) {
                    this.outputTextArea.setText("Error: No Student found with ID " + id);
                } else {
                    this.outputTextArea.setText(student.toString());
                }
            }
        } catch (NumberFormatException ex) {
            this.outputTextArea.setText("Invalid Input");
        }
    }

    /**
     * Displays students whose total marks fall within the specified range.
     * Triggered by the "Results in Range" button.
     * 
     * @param event the button click event
     */
    @FXML
    private void resultsInMarkRange(ActionEvent event) {
        this.outputTextArea.clear();
        String lowerStr = this.resultsInMarkRangeFromTextArea.getText().trim();
        String upperStr = this.resultsInMarkRangeToTextArea.getText().trim();

        GradeAnalyser.RangeValidationResponse response = this.GradeAnalyser.validateRanges(lowerStr, upperStr);

        if (!response.result()) {
            this.outputTextArea.setText(response.message());
            return;
        }

        GradeAnalyser.Range range = response.range();
        List<Student> studentsInRange = this.GradeAnalyser.getStudentsInRange(range.lower(), range.upper());

        if (studentsInRange.isEmpty()) {
            this.outputTextArea.setText("No students found in the range " + range.lower() + " to " + range.upper());
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Students in range ").append(range.lower()).append(" to ").append(range.upper()).append(":\n");
        for (Student student : studentsInRange) {
            sb.append(student.toString()).append("\n");
        }
        this.outputTextArea.setText(sb.toString());
    }

    /**
     * Displays class statistics including max, min, average, and median mark.
     * Triggered by the "Display Statistics" button.
     * 
     * @param event the button click event
     */
    @FXML
    private void displayStatistics(ActionEvent event) {
        this.outputTextArea.clear();
        try {
            int max = this.GradeAnalyser.maximum();
            int min = this.GradeAnalyser.minimum();
            double avg = this.GradeAnalyser.averageMark();
            double median = this.GradeAnalyser.medianMark();

            String stats = String.format(
                "Class Statistics:\n" +
                "Maximum Mark: %d\n" +
                "Minimum Mark: %d\n" +
                "Average Mark: %.2f\n" +
                "Median Mark: %.2f",
                max, min, avg, median
            );
            this.outputTextArea.setText(stats);
        } catch (EmptyListException e) {
            this.outputTextArea.setText("Error: " + e.getMessage());
        }
    }

    /**
     * Clears all user inputs and output fields in the GUI.
     * Triggered by the "Clear" button.
     * 
     * @param event the button click event
     */
    @FXML
    private void clearButton(ActionEvent event) {
        this.outputTextArea.clear();
        this.findStudentsIdTextArea.clear();
        this.resultsInMarkRangeFromTextArea.clear();
        this.resultsInMarkRangeToTextArea.clear();
    }

    /**
     * Exits the application.
     * Triggered by the "Exit" button.
     * 
     * @param event the button click event
     */
    @FXML
    private void exitButtonClick(ActionEvent event) {
        System.exit(0);
    }
}