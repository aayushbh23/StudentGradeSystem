package cqu.studentgradesystem;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller{

    // Primary Controller Code
    @FXML
    private TextArea outputTextArea;
    
    @FXML
    private TextField findStudentsIdTextArea;
    
    @FXML
    private TextField resultsInMarkRangeFromTextArea;
    
    @FXML
    private TextField resultsInMarkRangeToTextArea;
    
    
    @FXML
    private void displayAllGrades(ActionEvent event){
        this.outputTextArea.setText("Invalid Input");
    } 
    
    @FXML
    private void findStudentId(ActionEvent event){
        try{
            if (this.findStudentsIdTextArea.getText().isEmpty()){
                this.outputTextArea.setText("No Input Student ID");
            }else{
               int studentId = Integer.parseInt(this.findStudentsIdTextArea.getText()); 
               this.outputTextArea.setText("Student ID: "+studentId);
            }
        }
        catch(NumberFormatException ex){
           this.outputTextArea.setText("Invalid Input");
        }
    } 
    
    @FXML
    private void resultsInMarkRange(ActionEvent event){
        try{
            if (this.resultsInMarkRangeFromTextArea.getText().isEmpty() || this.resultsInMarkRangeToTextArea.getText().isEmpty()){
                this.outputTextArea.setText("Mark Range is Invalid");
            }else{
               int markRangeFrom = Integer.parseInt(this.resultsInMarkRangeFromTextArea.getText()); 
               int markRangeTo = Integer.parseInt(this.resultsInMarkRangeToTextArea.getText());
               this.outputTextArea.setText("Mark Range From: "+markRangeFrom +" To: "+markRangeTo);
            }
        }
        catch(NumberFormatException ex){
           this.outputTextArea.setText("Invalid Input");
        }         
    } 
    
    @FXML
    private void displayStatistics(ActionEvent event){
        this.outputTextArea.setText("Invalid Input");
    } 
    
    @FXML
    private void clearButton(ActionEvent event){
        this.outputTextArea.clear();
        this.findStudentsIdTextArea.clear();
        this.resultsInMarkRangeFromTextArea.clear();
        this.resultsInMarkRangeToTextArea.clear();    
    } 
    
    @FXML
    private void exitButtonClick(ActionEvent event){
        System.exit(0);
    }  
}
