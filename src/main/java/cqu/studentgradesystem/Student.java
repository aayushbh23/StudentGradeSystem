/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cqu.studentgradesystem;

/**
 *
 * @author 12157470
 */
public record Student (String id, String firstName, String lastName, int a1, int a2, int a3, String grade){
    public int total(){
        return a1+a2+a3;
    }
    
    @Override
    public String toString(){
        return String.format("%s: %-14s%-14s %2d %2d %2d total: %3d grade: %2s",id, firstName, lastName, a1, a2, a3, total(), grade);
    }
}
