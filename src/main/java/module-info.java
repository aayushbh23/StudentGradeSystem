module cqu.studentgradesystem {
    requires javafx.controls;
    requires javafx.fxml;

    opens cqu.studentgradesystem to javafx.fxml;
    exports cqu.studentgradesystem;
}
