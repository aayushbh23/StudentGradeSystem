/**
 * The {@code cqu.studentgradesystem} module contains all the classes necessary
 * for managing, analyzing and displaying student grade information.
 * This module depends on JavaFX for GUI elements and exposes the 
 * {@code cqu.studentgradesystem} package for use with FXML.
 */
module cqu.studentgradesystem {
    requires javafx.controls;
    requires javafx.fxml;

    opens cqu.studentgradesystem to javafx.fxml;
    exports cqu.studentgradesystem;
}
